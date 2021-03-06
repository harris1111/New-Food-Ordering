package com.example.p4f_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.example.p4f_project.protocols.ClientMessage;
import org.w3c.dom.Text;

import static com.example.p4f_project.LoginActivity.worker;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    ImageView profile_backtomain;
    TextView profile_name, profile_address, profile_phone, profile_email;
    Button logout,changePass;
    SharedPreferences myPreferences;
    private Context mContext;
    public static Handler profileHandler;
    SharedPreferences prefGet;
    SharedPreferences.Editor prefGetEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        findID();
        profile_backtomain.setOnClickListener(this);
        prefGet = getApplicationContext().getSharedPreferences("user_info", MODE_PRIVATE);
        prefGetEdit = prefGet.edit();
        profile_name.setText(prefGet.getString("Username", null));
        profile_address.setText(prefGet.getString("UserAddress", null));
        profile_phone.setText(prefGet.getString("UserPhone", null));
        profile_email.setText(prefGet.getString("UserEmail", null));
        //use Handler to receive Message
        Profile.profileHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                profile_name.setText((String) msg.obj);
            }
        };
        logout.setOnClickListener(this);
        changePass.setOnClickListener(this);
    }
    public void findID(){
        profile_backtomain=(ImageView) findViewById(R.id.profile_backtomain);
        profile_name=(TextView) findViewById(R.id.profile_name);
        logout=(Button) findViewById(R.id.logout_button);
        changePass=(Button) findViewById(R.id.btn_changePass);
        profile_address = findViewById(R.id.profile_address);
        profile_email = findViewById(R.id.profile_email);
        profile_phone = findViewById(R.id.profile_phone);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profile_backtomain:
                Intent newIntent= new Intent(Profile.this,MainPage.class);
                finish();
                break;
            case R.id.logout_button:
                Intent outIntent=new Intent(Profile.this,LoginActivity.class);
                startActivity(outIntent);
                prefGetEdit.clear().apply();
                Message msg = Message.obtain(ContainerClient.handler);
                msg.what = 0; // Opcode for logout
                msg.sendToTarget();
                finish();
                break;
            case R.id.btn_changePass:
                Intent changePassIntent=new Intent(Profile.this, ChangePassword.class);
                startActivity(changePassIntent);
                finish();
        }
    }

}