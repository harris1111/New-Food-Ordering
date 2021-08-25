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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    EditText username,password;
    Button Login;
    int ID;
    Context context;
    String uName,uPass;
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
                if ((boolean) msg.obj == true) {
                    toNextActivity();
                }
            }
        };
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username + password
                uName=username.getText().toString();
                uPass=password.getText().toString();
                //Create Message to send to Client
                Message msg = Message.obtain();
                msg.obj = "Login " +  (uName+uPass);
                //send to client
                ContainerClient.handler.sendMessage(msg);
            }
        });
        return root;
    }

    public boolean checkString(String userName){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]]");
        Matcher matcher= pattern.matcher(userName);
        boolean isSpecial=matcher.find();
        return isSpecial;
    }

    public void toNextActivity() {
        //Start new activity
        Intent myIntent = new Intent(LoginFragment.this.getActivity(), MainPage.class);
        startActivity(myIntent);
        getActivity().finish();
    }

    public int validate(String userName, String password)
    {
        String result="";
        if(userName.equals("user") && password.equals("1234")) {
            result = "Login was successful";
            return -1;
        }
        // Case username has special characters
        if(checkString(userName)){
            result= "Username contains special character(s)";
            return -1;
        }
        // case username != user
        if(userName!=("user")){
            result="Username not found!";
            return -1;
        }
        // case username or password is blank
        if(userName.equals("") || password.equals((""))){
            result= "Username invalid!";
            return -1;
        }
        // case Wrong password
        if(userName.equals("user") && password!=("1234")){
            result=("Wrong Pass!");
            return -1;
        }
        // case password length <8 characters
        if(password.length()<8){
            result=("Password must be at least 8 characters");
            return -1;
        }
        return 1;
    }

}

