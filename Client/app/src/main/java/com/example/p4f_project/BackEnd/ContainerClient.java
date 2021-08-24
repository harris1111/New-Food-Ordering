package com.example.p4f_project.BackEnd;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.example.p4f_project.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Timer;
import java.util.TimerTask;

public class ContainerClient implements Runnable {
    public static Looper looper;
    public static Handler handler;
    String[] server;
    int port;
    private Channel channel;
    Bootstrap bootstrap = new Bootstrap();
    private Timer timer_ = new Timer();
    NioEventLoopGroup group = new NioEventLoopGroup();
    boolean isConnected = false;
    int i = 0;
    public ContainerClient(String[] server, int port) {
        this.server = server;
        this.port = port;
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientAdapterInitializer());
        scheduleConnect(10);
    }
    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        try {
            handler = new Handler(looper) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
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
            ChannelFuture f = bootstrap.connect( server[i], port );
            f.addListener( new ChannelFutureListener() {
                @Override public void operationComplete(ChannelFuture future) throws Exception {
                    if( !future.isSuccess() ) {//if is not successful, reconnect
                        System.out.println("CONNECT FAIL");
                        future.channel().close();
                        bootstrap.connect(  server[++i], port ).addListener(this).sync();
                    }
                    else {//good, the connection is ok
                        System.out.println("CONNECT SUCCESSFULLY");
                        isConnected = true;
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
                            connectionLost();
                        }

                    });

                }
            });
        }catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
    public void connectionLost() {
        System.out.println("connectionLost()" );
    }
}
