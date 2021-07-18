package com.example.p4f_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button register_button,back_link;
    EditText et_Username,et_Password,et_Name,et_Address,et_Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // find the view by the given ID, in this case is finding the username
        // and password
        et_Username=(EditText) findViewById(R.id.et_Username);
        et_Password=(EditText)findViewById(R.id.et_Password);
        et_Name=(EditText)findViewById(R.id.et_Name);
        et_Phone=(EditText)findViewById(R.id.et_Phone);
        et_Address=(EditText)findViewById(R.id.et_Address);
        ////
        register_button=(Button) findViewById(R.id.register_button);
        back_link=(Button)findViewById(R.id.back_link);
        //handle clicks event
        register_button.setOnClickListener(this);
        back_link.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                finish();
                break;
            case R.id.back_link:
                finish();
                break;

        }
    }
}