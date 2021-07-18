package com.example.p4f_project;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    ImageView profile_backtomain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_backtomain=(ImageView) findViewById(R.id.profile_backtomain);
        profile_backtomain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profile_backtomain:
                Intent newIntent= new Intent(Profile.this,MainPage.class);
                finish();
                break;
        }
    }
}