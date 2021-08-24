package com.example.p4f_project.BackEnd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.example.p4f_project.*;


public class ClientAdapterHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println("[INFO] - " + currentChannel.remoteAddress() + " - " + msg);
        //Test function if msg contain "Login"
        if (msg.contains("Login")) {
            Log.d("SEND TO PROFILE", String.valueOf(msg.contains("Login")));
            Log.d("object of mMsg", msg);
            //Connect to "user_data" Shared Pref using Application Context
            SharedPreferences prefs = p4f_project.getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
            //Edit this shared pref
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString("Profile name", msg);
            System.out.println(p4f_project.getContext().getSharedPreferences("user_data",Context.MODE_PRIVATE).getString("Profile name",null));
            prefEditor.apply();
        }
    }
}