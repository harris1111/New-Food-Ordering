package com.example.p4f_project;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView register_link;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //find register_link id
        register_link=(TextView) findViewById(R.id.register_link);
        login_button=(Button) findViewById(R.id.login_button);
        //set click listener on register link
        register_link.setOnClickListener(this);
        login_button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_link:
                Intent myIntnent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(myIntnent);
                break;
            case R.id.login_button:
                Intent myIntent= new Intent(LoginActivity.this,MainPage.class);
                startActivity(myIntent);
                break;
        }
    }
}