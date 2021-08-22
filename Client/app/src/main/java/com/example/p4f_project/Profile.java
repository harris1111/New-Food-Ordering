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
import org.w3c.dom.Text;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    ImageView profile_backtomain;
    TextView profile_name;
    SharedPreferences myPreferences;
    private Context mContext;
    Button logout;
    public static Handler profileHandler;
    SharedPreferences prefGet;
    SharedPreferences.Editor prefGetEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_backtomain=(ImageView) findViewById(R.id.profile_backtomain);
        profile_backtomain.setOnClickListener(this);
        profile_name=(TextView) findViewById(R.id.profile_name);
        prefGet = getApplicationContext().getSharedPreferences("user_data", MODE_PRIVATE);
        prefGetEdit = prefGet.edit();
        profile_name.setText(prefGet.getString("Profile name", null));
        Profile.profileHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                profile_name.setText((String) msg.obj);
            }
        };
        logout=(Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(this);
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
                finish();
                break;
        }
    }

}