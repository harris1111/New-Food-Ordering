package com.example.p4f_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.example.p4f_project.protocols.LoginInfo;
import com.example.p4f_project.protocols.changePassInfo;
import org.w3c.dom.Text;

import static com.example.p4f_project.p4f_project.getContext;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
    Button confirmChangePass;
    EditText oldPass,newPass,newPassConfirm;
    TextView profile_name;
    ImageView backToProfile;
    SharedPreferences myPreferences;
    private Context mContext;
    public static Handler changePasswordhandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findID();
        SharedPreferences prefs = getSharedPreferences("user_info", MODE_PRIVATE);
        String name = prefs.getString("Username", null);
        profile_name.setText(name);
        confirmChangePass.setOnClickListener(this);
        changePasswordhandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                }
                else if (msg.what == -1) {
                    Toast announce = Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_SHORT);
                    announce.show();
                }
            }
        };

    }
    public void findID(){
        profile_name=(TextView) findViewById(R.id.profile_name);
        backToProfile=(ImageView) findViewById(R.id.backtoprofile);
        confirmChangePass=(Button) findViewById(R.id.btn_confirmChangePass);
        oldPass=(EditText) findViewById(R.id.et_oldPass);
        newPass=(EditText) findViewById(R.id.et_newPass);
        newPassConfirm=(EditText) findViewById(R.id.et_newPassConfirm);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_confirmChangePass:
                while(newPass.getText().toString()!=newPassConfirm.getText().toString()){
                    Toast announce = Toast.makeText(getContext(), "New password does not match !", Toast.LENGTH_SHORT);
                    announce.show();
                }
                changePassInfo changeInfo = changePassInfo.newBuilder()
                        .setUsername(profile_name.getText().toString())
                        .setOldPass(oldPass.getText().toString())
                        .setNewPass(newPass.getText().toString())
                        .setNewPass(newPassConfirm.getText().toString()).build();
                Message msg = Message.obtain(ContainerClient.handler);
                msg.what = 3;
                msg.obj = changeInfo;
                msg.sendToTarget();
            case R.id.backtoprofile:
                Intent confirmIntent=new Intent(ChangePassword.this,Profile.class);
                startActivity(confirmIntent);
                finish();
                break;
        }
    }
}
