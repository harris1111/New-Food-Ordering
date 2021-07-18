package com.p2p.p4f.server;

import com.p2p.p4f.protocols.ClientMessage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerP4F {
	private static Logger logger = LogManager.getLogger(ServerP4F.class.getName());
	private Channel serverChannel = null;
	private int port;
	private String ipAddr = "";
	private Thread serverThread = null;
	
	public ServerP4F(String ip, int port) {
		if (!ip.isEmpty())
			ipAddr = ip;
		this.port = port;
		System.out.println(logger.isInfoEnabled());
	}
	
	public void start() throws Exception {
		//Run the server on another thread
		serverThread = new Thread(() -> {
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				//Setup the server
				ServerBootstrap serverBs = new ServerBootstrap();
				serverBs.group(bossGroup, workerGroup)
						.channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<NioSocketChannel>() {
							@Override
							protected void initChannel(NioSocketChannel ch) throws Exception {
								//TODO: Add server handlers here
								ChannelPipeline pipeline = ch.pipeline();
								pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
								pipeline.addLast(new ProtobufEncoder());
								pipeline.addLast(new ProtobufVarint32FrameDecoder());
								pipeline.addLast(new ProtobufDecoder(ClientMessage.getDefaultInstance()));
							}
						})
						.option(ChannelOption.SO_BACKLOG, 128)
						.childOption(ChannelOption.SO_KEEPALIVE, true)
						.childOption(ChannelOption.SO_RCVBUF, 8 * 1024)
						.childOption(ChannelOption.SO_SNDBUF, 8 * 1024);
				//Bind the server to an address + port
				ChannelFuture chFuture = serverBs.bind(ipAddr, port).sync();
				logger.info("P4F Server starts successfully on port " + port + (ipAddr == "" ? " of all network interfaces" : " of address " + ipAddr));
				//Keep the server channel for manual closing
				serverChannel = chFuture.channel();
				//Block this thread until all groups finish
				serverChannel.closeFuture().sync();
			} catch (Exception e) {
				logger.error("P4F Server - Netty error", e);
			} finally {
				//Close the event loop groups and remove the current server channel
				workerGroup.shutdownGracefully();
				bossGroup.shutdownGracefully();
				serverChannel = null;
			}
		});
		
		//Start the server thread
		serverThread.setName("ServerP4F-Thread-" + (ipAddr == "" ? ":" : ipAddr + ":") + port);
		serverThread.start();
	}
	
	public void closeServer() {
		try {
			if (serverChannel != null || serverThread != null) {
				logger.info("Close ServerP4F on " + (ipAddr == "" ? ":" : ipAddr + ":") + port);
				serverChannel.close();
				serverChannel = null;
				serverThread.join();
				serverThread = null;
			}
		}
		catch (Exception e) {
			logger.error("P4F Server - Netty error", e);
		}
	}
}
