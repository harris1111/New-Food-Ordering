package com.example.p4f_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.example.p4f_project.protocols.LoginInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    EditText username,password;
    Button Login;
    int ID;
    Context context;
    FloatingActionButton fb,gg,phone;
    float v=0;
    public static Handler loginFragmentHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        context=this.getContext();
        username=(EditText) root.findViewById(R.id.username);
        password=(EditText) root.findViewById(R.id.password);
        Login=(Button)root.findViewById(R.id.loginButton);
        ID=R.id.login_fragment;
        fb=root.findViewById(R.id.fab_fb);
        gg=root.findViewById(R.id.fab_gg);
        phone=root.findViewById(R.id.fab_phone);
        Login= root.findViewById(R.id.loginButton);
        ////////////////////////////////////////////////////////////////////////
        SharedPreferences prefs= context.getSharedPreferences("user_data",MODE_PRIVATE);
        // Animations
        fb.setTranslationY(0);
        gg.setTranslationY(0);
        phone.setTranslationY(0);
        username.setTranslationX(0);
        password.setTranslationX(0);
        Login.setTranslationX(0);

        fb.setAlpha(v);
        gg.setAlpha(v);
        phone.setAlpha(v);
        username.setAlpha(v);
        password.setAlpha(v);
        Login.setAlpha(v);


        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        gg.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        phone.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        Login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();

        loginFragmentHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                    if (msg.arg1 == 0) {
                        toMainPageActivity();
                    }
                }
                else if (msg.what == -1) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                }
            }
        };
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username + password
                LoginInfo loginInfo = LoginInfo.newBuilder()
                        .setUsername(username.getText().toString())
                        .setPassword(password.getText().toString()).build();
                //Create Message to send to Client
                Message msg = Message.obtain(ContainerClient.handler);
                msg.what = 1;
                msg.obj = loginInfo;
                //send to client
                msg.sendToTarget();
            }
        });
        return root;
    }

    public void toMainPageActivity() {
        //Start new activity
        Intent myIntent = new Intent(LoginFragment.this.getActivity(), MainPage.class);
        startActivity(myIntent);
        getActivity().finish();
    }
}

