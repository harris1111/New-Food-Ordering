package com.example.p4f_project.BackEnd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import com.example.p4f_project.ChangePassword;
import com.example.p4f_project.LoginFragment;
import com.example.p4f_project.RegisterFragment;
import com.example.p4f_project.p4f_project;
import com.example.p4f_project.protocols.InfoResponse;
import com.example.p4f_project.protocols.LoginInfo;
import com.example.p4f_project.protocols.ServerMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Connect to server at " + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("Disconnect from the server");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        ServerMessage serverMessage = (ServerMessage) msg;
        if (serverMessage.getOpcode() == 1) {
            Message response = Message.obtain(LoginFragment.loginFragmentHandler);
            response.what = 1;
            InfoResponse infoResponse = serverMessage.getInfoResponse();
            if (infoResponse.getReCode() == 0 || infoResponse.getReCode() == 3) {
                response.arg1 = 0;
                response.obj = "Login success";
                // If login success, save user info to a shared preference file
                SharedPreferences prefs = p4f_project.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putString("Username", infoResponse.getUserInfo().getUsername());
                prefEditor.putInt("UserType", infoResponse.getUserInfo().getType());
                prefEditor.putString("UserEmail", infoResponse.getUserInfo().getEmail());
                prefEditor.putString("UserPhone", infoResponse.getUserInfo().getPhone());
                prefEditor.putString("UserAddress", infoResponse.getUserInfo().getAddress());
                prefEditor.apply();
            }
            else if (infoResponse.getReCode() == 1) {
                response.arg1 = -1;
                response.obj = "Username does not exist";
            }
            else if (infoResponse.getReCode() == 2) {
                response.arg1 = -1;
                response.obj = "Wrong password";
            }
            response.sendToTarget();
        }
        if (serverMessage.getOpcode() == 2) {
            Message response = Message.obtain(RegisterFragment.registerFragmentHandler);
            response.what = 2;
            System.out.println("responseCode: " + serverMessage.getResponseCode());
            if (serverMessage.getResponseCode() == 0) {
                response.arg1 = 0;
                response.obj = "Register success";
            }
            else if (serverMessage.getResponseCode() == 1) {
                response.arg1 = -1;
                response.obj = "User exists";
            }
            else if (serverMessage.getResponseCode() == 2) {
                response.arg1 = -1;
                response.obj = "Email fault";
            }
            else if (serverMessage.getResponseCode() == 3) {
                response.arg1 = -1;
                response.obj = "Phone fault";
            }
            else if (serverMessage.getResponseCode() == 5) {
                response.arg1 = -1;
                response.obj = "Unexpected error";
            }
            response.sendToTarget();
        }
        if (serverMessage.getOpcode() == 3) {
            Message response = Message.obtain(ChangePassword.changePasswordhandler);
            response.what = 3;
            if (serverMessage.getResponseCode() == 1) {
                response.arg1 = 0;
                response.obj = "Change Password success";
            }
            else if (serverMessage.getResponseCode() == -1) {
                response.arg1 = -1;
                response.obj = "Change Password Fail";
            }
            response.sendToTarget();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("AN EXCEPTION HAS OCCURED!");
        cause.printStackTrace();
        ContainerClient.looper.quitSafely();
        ctx.channel().close();
    }
}
