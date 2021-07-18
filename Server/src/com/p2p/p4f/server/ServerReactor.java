package com.p2p.p4f.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.*;

/**
 * The core class of the server, one main thread should only have 1 server reactor.
 * This server reactor uses TCP Connection and IPv4 addresses.
 */
public class ServerReactor {
	/**
	 * Port number should be higher than 1023
	 */
	private int port;
	/**
	 * If IP Address is null, then server will listen on a random ip available to the machine
	 */
	private InetAddress addr = null;
	/**
	 * For selecting socket operations
	 */
	private Selector selector;
	/**
	 * Is the thread calling start() method
	 */
	private Thread selectorThread;
	/**
	 * For queuing next selectable operations
	 */
	private final ConcurrentLinkedQueue<Runnable> selectorTasks = new ConcurrentLinkedQueue<>();
	/**
	 * Logs server exceptions to an output file
	 */
	public static Logger log = null;
	//TODO: Missing components: packetHandlerFactory, taskProcessorFactory, WorkerThreadPool, DatabaseHandlerPool
	
	//TODO: Add missing components to the constructor
	/**
	 * Construct the core of the server, if you want to add another core, initialize that second core in another thread
	 * @param numOfThreads number of worker threads the server can use
	 * @param port         a port number, should be >1023 if not using a predefined protocol
	 * @param bindAddr     a readable string of the listening IP address
	 * @param loggingPath  a string path to the logging file of the server
	 * @throws Exception   The bind address or logging path is invalid
	 */
	public ServerReactor(int numOfThreads, int port, String bindAddr, String loggingPath) throws Exception {
		this.port = port;
		if (bindAddr != null) {
			addr = InetAddress.getByName(bindAddr);
		}
		if (log == null) {
			log = Logger.getLogger(ServerReactor.class.getName());
			FileHandler fHandler = new FileHandler(loggingPath, true);
			fHandler.setFormatter(new SimpleFormatter());
			log.addHandler(fHandler);
		}
	}
	
	/**
	 * Change the address the server listens on. Can only work when the server is closed.
	 * @param bindAddr a readable string of the new IP address or hostname
	 * @throws Exception the bind address is not null but is invalid
	 */
	public void setAddr(String bindAddr) throws Exception {
		if (selector.isOpen())
			System.out.println("Can't change bind address while the server is running!");
		else {
			if (bindAddr != null) {
				addr = InetAddress.getByName(bindAddr);
			}
			else addr = null;
		}
	}
	
	/**
	 * Change the listening port of the server. Can only work when the server is closed.
	 * @param port new listening port number, should be >1023 if not using a predefined protocol
	 */
	public void setPort(int port) {
		if (selector.isOpen())
			System.out.println("Can't change port while the server is running!");
		else {
			this.port = port;
		}
	}
	
	/**
	 * Call this method in the thread you want to run the server to start serving clients
	 */
	public void start() {
		selectorThread = Thread.currentThread();
		try (   Selector newSelector = Selector.open();
		        ServerSocketChannel acceptor = ServerSocketChannel.open()) {
			/*Use a new selector whenever we want to resume the server after closing it.
			**Because the selector is initialized within try-catch, the old selector will be picked up by the garbage collector
			*/
			this.selector = newSelector;
			acceptor.bind(new InetSocketAddress(addr, port));
			acceptor.configureBlocking(false);
			acceptor.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("Server begins listening on " + acceptor.getLocalAddress().toString());
			
			//Selection loop, runs until shutting down the program or calling close() method
			while (!selectorThread.isInterrupted()) {
				selector.select(180000); //Timeout after 3 minutes
				runNextSelectorTasks();
				
				//Loop through the set of selected keys (socket channels that have an event)
				for (SelectionKey key : selector.selectedKeys()) {
					if (!key.isValid()) {
						continue; //Skip if the client has a connection error after activating an event
					}
					else if (key.isAcceptable()) {
						acceptConnection(acceptor, selector);
					}
					else {
						if (key.isReadable()) {
							//TODO: Handle receive data from client
						}
						if (key.isWritable()) {
							//TODO: Handle send data to client
						}
					}
				}
				
				//Clear processed keys to resume the event tracking
				selector.selectedKeys().clear();
			}
		}
		catch (ClosedSelectorException e) {
			//Just catch this to stop the server when calling close() method
		}
		catch (IOException ex) {
			log.log(Level.INFO, "IOException - An error occured while running the server reactor!", ex);
		}
		finally {
			LogManager.getLogManager().reset();
		}
		
		System.out.println("The server listening on " + addr.toString() + ':' + Integer.toString(port) + " is closed!");
		//TODO: Shutdown the worker thread pool here
		
	}
	
	/**
	 * Accept a new client connection from the listening socket channel. This method is called when the selector detects an Accept operation
	 * @param acceptor The server socket channel that the server reactor is listening on
	 * @param selector The selector that the acceptor is registered to
	 * @throws IOException If an I/O error occurs while accepting the new client
	 */
	private void acceptConnection(ServerSocketChannel acceptor, Selector selector) throws IOException {
		SocketChannel newClient = acceptor.accept();
		newClient.configureBlocking(false);
		//TODO: Attach a ConnectionHandler to the client channel when registering it
		newClient.register(selector, SelectionKey.OP_READ);
		System.out.println("New client connected from address " + newClient.getRemoteAddress());
		System.out.println("Current number of clients: " + (selector.keys().size() - 1));
	}
	
	/**
	 * Update the socket channel's tracked operations. This is used in worker threads to switch the client channel mode between receiving and sending.
	 * @param sChan the socket channel registered in the selector
	 * @param opcode new operation code of SelectionKey (Accept, Read, Write)
	 */
	public void setTrackedOperations(SocketChannel sChan, int opcode) {
		//Declare the key as final so it can be modified in lambda expression
		final SelectionKey key = sChan.keyFor(selector);
		//See if the thread calling this method is the worker thread or reactor thread
		//Only update the selector opcode in reactor thread, not worker thread
		if (Thread.currentThread() == selectorThread) {
			key.interestOps(opcode);
		}
		else {
			//Push to the task queue for the reactor thread to update later
			//The task is small so use lambda expression for efficiency
			selectorTasks.add(
				() -> {
					if (key.isValid())
						key.interestOps(opcode);
				}
			);
			//Wakeup the selector to cancel the current event tracking cycle in reactor thread and update new tasks
			selector.wakeup();
		}
	}
	
	/**
	 * Calling this method will start all the selector tasks in the task queue
	 */
	private void runNextSelectorTasks() {
		while (!selectorTasks.isEmpty()) {
			selectorTasks.remove().run();
		}
	}
	
	/**
	 * This method will stop the server from working.
	 * To resume, call {@link #start()} again.
	 * @throws IOException if the server is closed while doing a send/receive event
	 */
	public void close() throws IOException {
		selector.close();
	}
}
