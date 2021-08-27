package com.example.p4f_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.example.p4f_project.protocols.LoginInfo;
import org.w3c.dom.Text;

import static com.example.p4f_project.p4f_project.getContext;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
    Button confirmChangePass;
    EditText oldPass,newPass,newPassConfirm;
    TextView profile_name;
    ImageView backToProfile;
    SharedPreferences myPreferences;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findID();
        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        String name = prefs.getString("username", null);
        profile_name.setText(name);
        confirmChangePass.setOnClickListener(this);

    }
    public void findID(){
        profile_name=(TextView) findViewById(R.id.profile_name);
        backToProfile=(ImageView) findViewById(R.id.backtoprofile);
        confirmChangePass=(Button) findViewById(R.id.btn_confirmChangePass);
        oldPass=(EditText) findViewById(R.id.et_oldPass);
        newPass=(EditText) findViewById(R.id.et_newPass);
        newPassConfirm=(EditText) findViewById(R.id.et_newPassConfirm);
    }
    private boolean checkNewPass() {
        String newPassString = newPass.getText().toString();
        String newPassConfirmString = newPassConfirm.getText().toString();
        if (newPassString == newPassConfirmString) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_confirmChangePass:
                if (!checkNewPass()) {
                    Toast announce = Toast.makeText(getContext(), "Password confirmation failed", Toast.LENGTH_SHORT);
                    announce.show();
                    break;
                }
                LoginInfo loginInfo = LoginInfo.newBuilder()
                        .setUsername(profile_name.getText().toString())
                        .setPassword(oldPass.getText().toString()).build();
                Message msg = Message.obtain(ContainerClient.handler);
                msg.what = 2;

            case R.id.backtoprofile:
                Intent confirmIntent=new Intent(ChangePassword.this,Profile.class);
                startActivity(confirmIntent);
                finish();
                break;
        }
    }
}
