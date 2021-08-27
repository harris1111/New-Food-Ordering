package com.example.p4f_project;

import android.content.Intent;
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
import com.example.p4f_project.BackEnd.ContainerClient;
import com.example.p4f_project.protocols.LoginInfo;
import com.example.p4f_project.protocols.RegisterInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    EditText reg_username;
    EditText reg_password;
    EditText phone;
    EditText address;
    EditText fullName;
    Button regButton;
    public static Handler registerFragmentHandler;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);

        reg_username = root.findViewById(R.id.reg_username);
        reg_password = root.findViewById(R.id.reg_password);
        fullName = root.findViewById(R.id.fullname);
        phone = root.findViewById(R.id.phone);
        address = root.findViewById(R.id.address);
        regButton = root.findViewById(R.id.regButton);


        reg_username.setTranslationX(0);
        reg_password.setTranslationX(0);
        fullName.setTranslationX(0);
        phone.setTranslationX(0);
        address.setTranslationX(0);
        regButton.setTranslationX(0);


        reg_username.setAlpha(v);
        reg_password.setAlpha(v);
        fullName.setAlpha(v);
        phone.setAlpha(v);
        address.setAlpha(v);
        regButton.setAlpha(v);


        reg_username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        reg_password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        fullName.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        phone.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(900).start();
        address.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1100).start();
        regButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();

        registerFragmentHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1 ) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                    //resetRegisterActivity();
                }
                else if (msg.what == -1) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                    //resetRegisterActivity();
                }
            }
        };
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterInfo registerInfo = RegisterInfo.newBuilder()
                        .setUsername(reg_username.getText().toString())
                        .setPassword(reg_password.getText().toString())
                        .setFullname(fullName.getText().toString())
                        .setPhone(phone.getText().toString())
                        .setAddress(address.getText().toString()).build();
                Log.d("Set Register Infor ", "Thanh cong");
                //Create Message to send to Client
                Message msg = Message.obtain(ContainerClient.handler);
                msg.what = 2;
                msg.obj = registerInfo;
                //send to client
                msg.sendToTarget();
                Log.d("sendtoTarget() ", "Thanh cong");
            }
        });
        return root;

    }

    public boolean checkString(String userName) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]]");
        Matcher matcher = pattern.matcher(userName);
        boolean isSpecial = matcher.find();
        return isSpecial;
    }
    public void resetRegisterActivity() {
        Intent intent = new Intent();
        intent.setClass(p4f_project.getContext(), p4f_project.getContext().getClass());
        getActivity().finish();
        p4f_project.getContext().startActivity(intent);

    }
}