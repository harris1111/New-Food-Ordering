package com.example.p4f_project.BackEnd;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ContainerClient implements Runnable {
    //Initialization
    public static Looper looper;
    public static Handler handler;
    ArrayList<String> iplist = new ArrayList<String>();
    int port;
    private Channel channel = null;
    Bootstrap bootstrap = new Bootstrap();
    private Timer timer_ = new Timer();
    NioEventLoopGroup group = new NioEventLoopGroup();
    boolean isFailed = false;
    InputStream is = null;
    ListIterator<String> it = null;
    public ContainerClient(InputStream is, int port) {
        this.port = port;
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientAdapterInitializer());
        this.is = is;
    }
    @Override
    public void run() {
        // Read the ip file
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                iplist.add(line);
                line = reader.readLine();
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String ip : iplist) {
            System.out.println(ip);
        }
        //start Looper
        Looper.prepare();
        looper = Looper.myLooper();
        try {
            handler = new Handler(looper) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    if (channel == null) {
                        it = iplist.listIterator();
                        doConnect();
                    }
                    while (channel == null) {
                        if (isFailed) {
                            System.out.println("CONNECT FAILED FOR ALL IPs");
                            return;
                        }
                    }
                    String line = msg.obj.toString();
                    ChannelFuture lastWrite;
                    lastWrite = channel.writeAndFlush(line);
                    try {
                        lastWrite.sync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();
            channel.closeFuture().sync();
            System.out.println("Close Client now!!! \n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
    private void scheduleConnect( long millis ) {
        timer_.schedule( new TimerTask() {
            @Override
            public void run() {
                doConnect();
            }
        }, millis );
    }
    private void doConnect() {
        try {
            String ip = it.next();
            System.out.println("Connect to: " + ip);
            ChannelFuture f = bootstrap.connect(ip, port);
            f.addListener( new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess() ) {//if is not successful, reconnect
                        System.out.println("CONNECT FAILED");
                        future.channel().close();
                        if (it.hasNext()) {
                            String ip = it.next();
                            System.out.println("Connect to: " + ip);
                            bootstrap.connect(ip, port).addListener(this);
                        }
                        else isFailed = true;
                    }
                    else {//good, the connection is ok
                        System.out.println("CONNECT SUCCESSFULLY");
                        it.previous();
                        isFailed = false;
                        channel = future.channel();
                        //add a listener to detect the connection lost
                        addCloseDetectListener(channel);
                        timer_.cancel();
                        timer_.purge();
                        return;
                    }
                }
                private void addCloseDetectListener(Channel channel) {
                    //if the channel connection is lost, the ChannelFutureListener.operationComplete() will be called
                    channel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future )
                                throws Exception {
                            System.out.println("CONNECTION LOST");
                            scheduleConnect(10);
                        }
                    });
                }
            });
        }catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
}
