package com.example.p4f_project.BackEnd;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.p4f_project.*;
import com.example.p4f_project.protocols.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainerClient implements Runnable {
    //Initialization
    public static Looper looper;
    public static Handler handler;
    ArrayList<String> iplist = new ArrayList<String>();
    int port;
    private Channel channel = null;
    Bootstrap bootstrap = new Bootstrap();
    NioEventLoopGroup group = new NioEventLoopGroup();
    boolean isFailed = false;
    InputStream is = null;
    ListIterator<String> it = null;

    public ContainerClient(InputStream is, int port) {
        this.port = port;
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientAdapterInitializer());
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 200);
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
        //start Looper
        Looper.prepare();
        looper = Looper.myLooper();
        try {
            handler = new Handler(looper) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    // If the connection isn't active, try to connect to server from the address list
                    if (channel == null) {
                        it = iplist.listIterator();
                        doConnect();
                    }
                    while (channel == null) {
                        if (isFailed) {
                            System.out.println("CONNECT FAILED FOR ALL IPs");
                            Message response = null;
                            if (msg.what == 1)
                                response = Message.obtain(LoginFragment.loginFragmentHandler);
                            if (msg.what == 2)
                                response = Message.obtain(RegisterFragment.registerFragmentHandler);
                            if (msg.what == 3)
                                response = Message.obtain(Profile.profileHandler);
                            if (msg.what == 4)
                                response = Message.obtain(OrderActivity.orderActivityHandler);
                            response.what = -1; // Opcode for connection error
                            response.obj = "Unable to connect to server";
                            response.sendToTarget();
                            isFailed = false;
                            it = null;
                            return;
                        }
                    }
                    ClientMessage.Builder clientMessage = ClientMessage.newBuilder();
                    // If the opcode is logout, close the channel
                    if (msg.what == 0) {
                        channel.close();
                        looper.quitSafely();
                        return;
                    }
                    // If the opcode is login, validate the info before sending to server
                    if (msg.what == 1) {
                        int result = validateLoginInfo((LoginInfo) msg.obj);
                        // If the login info is invalid
                        if (result > 0) {
                            Message response = Message.obtain(LoginFragment.loginFragmentHandler);
                            response.what = 1;
                            response.arg1 = -1;
                            switch (result) {
                                case 1:
                                    response.obj = "Username contains special character(s)";
                                    break;
                                case 2:
                                    response.obj = "Username must be at least 5 characters!";
                                    break;
                                case 3:
                                    response.obj = "Username invalid!";
                                    break;
                                case 4:
                                    response.obj = "Password invalid!";
                                    break;
                                case 5:
                                    response.obj = "Password must be at least 8 characters";
                            }
                            response.sendToTarget();
                            return;
                        }
                        // If login info is valid, set it to the protobuf
                        clientMessage.setOpcode(1);
                        clientMessage.setAccount((LoginInfo) msg.obj);
                    }
                    if (msg.what == 2 ) {
                        Log.d("Vao msg.what == 2", " Thanh cong");
                        int result = validateRegister((RegisterInfo) msg.obj);
                        if (result > 0) {
                            Message response = Message.obtain(RegisterFragment.registerFragmentHandler);
                            response.what = 2;
                            response.arg1 = -1;
                            switch (result) {
                                case 1:
                                    response.obj = "Username contains special character(s)";
                                    break;
                                case 2:
                                    response.obj = "There are some fields missing";
                                    break;
                                case 3:
                                    response.obj = "Phone number contains character";
                                    break;
                                case 4:
                                    response.obj = "Username must be at least 5 characters";
                                    break;
                                case 5:
                                    response.obj = "Password must be at least 8 characters";
                            }
                            response.sendToTarget();
                            return;
                        }
                        clientMessage.setOpcode(2);
                        clientMessage.setRegAcc((RegisterInfo) msg.obj);
                    }
                    if (msg.what == 3) {
                        Message response = Message.obtain(ChangePassword.changePasswordhandler);
                        int result = checkChangePassword((changePassInfo) msg.obj);
                        if (result > 0) {
                            response.what = 3;
                            response.arg1 = -1;
                            switch (result) {
                                case 1:
                                    response.obj = "New password must be different from old password ";
                                    break;
                                case 2:
                                    response.obj = "Password must be at least 8 characters";
                                    break;
                                case 3:
                                    response.obj = "New password and confirm password must be the same";
                                    break;
                                case 4:
                                    response.obj = "New password contains username";
                                    break;
                            }
                            response.sendToTarget();
                            return;
                        }
                        clientMessage.setOpcode(3);
                        clientMessage.setChangeRes((changePassInfo) msg.obj);
                    }
                    if (msg.what == 4) {
                        Order clientOrder = (Order) msg.obj;
                        Log.d("Info order" , clientOrder.getUsername() + " " + clientOrder.getBuyDate() +  " " + clientOrder.getResID() + " " + clientOrder.getFoodListCount());
                        clientMessage.setOpcode(4);
                        clientMessage.setOrder((Order) msg.obj);
                    }
                    ChannelFuture lastWrite;
                    lastWrite = channel.writeAndFlush(clientMessage.build());
                    try {
                        lastWrite.sync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            channel = null;
            handler = null;
            looper = null;
        }
    }

    private void doConnect() {
        try {
            String ip = it.next();
            ChannelFuture f = bootstrap.connect(ip, port);
            f.addListener( new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess() ) {//if is not successful, reconnect
                        future.channel().close();
                        if (it.hasNext()) {
                            String ip = it.next();
                            bootstrap.connect(ip, port).addListener(this);
                        }
                        else isFailed = true;
                    }
                    else {//good, the connection is ok
                        it.previous();
                        isFailed = false;
                        channel = future.channel();
                        return;
                    }
                }
            });
        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }

    private boolean checkString(String userName){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher= pattern.matcher(userName);
        boolean isSpecial=matcher.find();
        return isSpecial;
    }

    private boolean checkEmpty(String input){
        if(input.length()==0){
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(String phone){
        if(phone.matches("[0-9]+") &&phone.length()==10){
            return true;
        }
        return false;
    }

    private int validateLoginInfo(LoginInfo loginInfo) {
        // Case username has special characters
        if(checkString(loginInfo.getUsername())){
            return 1;
        }
        // Username is shorter than 5 characters
        if(loginInfo.getUsername().length()<5 && loginInfo.getUsername().length()!=0){
            return 2;
        }
        // case username or password is blank
        if(loginInfo.getUsername().length()==0){
            return 3;
        }
        if(loginInfo.getPassword().length()==0){
            return 4;
        }
        // case password length <8 characters
        if(loginInfo.getPassword().length()<8){
            return 5;
        }
        return 0;
    }

    private int validateRegister(RegisterInfo registerInfo){
        // check if Username contains special characters
        if(checkString(registerInfo.getUsername())){
            return 1;
        }

        // check if any field is missed
        if(checkEmpty(registerInfo.getUsername())==false || checkEmpty(registerInfo.getPassword())==false ||
                checkEmpty(registerInfo.getEmail())==false|| checkEmpty(registerInfo.getAddress())==false || checkEmpty(registerInfo.getPhone())==false){
            return 2;
        }

        // check if a phone number is validate
        if(validatePhoneNumber(registerInfo.getPhone())==false){
            return 3;
        }

        // check if Username length is valid (>=5)
        if((registerInfo.getUsername()).length()<5){
            return 4;
        }

        // check if password length is valid (>=8)
        if((registerInfo.getPassword()).length()<8){
            return 5;
        }
        return 0;
    }


    private int checkChangePassword(changePassInfo changePassword) {
        boolean flag=true; // used to check change pass successfully

        // check if new password is same with old password (old password)
        if(changePassword.getOldPass().equals(changePassword.getNewPass())){
            flag=false;
            return 1;
        }

        // check if new password length is valid (>=8)
        if(changePassword.getNewPass().length()<8){
            flag=false;
            return 2;
        }

        // check if newPassword different from newPasswordConfirm
        if (!changePassword.getNewPass().equals(changePassword.getNewPassConfirm())) {
            return 3;
        }

        // check if new password contains username or same with username
        if(changePassword.getNewPass().contains(changePassword.getUsername())) {
            flag=false;
            return 4;
        }

        // check if all cases above passed and if new password != old password then change password successfully
        if(!(changePassword.getOldPass().equals(changePassword.getNewPass()))&& flag){
            return 0;
        }
        //Unexpected error
        return -1;

    }
    private int validateLoginInfo(String username, String password) {
        // Case username has special characters
        if(checkString(username)){
            return 1;
        }
        // Username is shorter than 5 characters
        if(username.length()<5 && username.length()!=0){
            return 2;
        }
        // case username or password is blank
        if(username.length()==0){
            return 3;
        }
        if(password.length()==0){
            return 4;
        }
        // case password length <8 characters
        if(password.length()<8){
            return 5;
        }
        return 0;
    }
}
