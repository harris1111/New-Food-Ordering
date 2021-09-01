package com.example.p4f_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

public class SuccessScreen extends AppCompatActivity {
    Button returnMain;
    TextView textDescription;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_screen);
        returnMain=(Button) findViewById(R.id.returnMain);
        textDescription=(TextView) findViewById(R.id.textDes);
        Intent thisIntent= this.getIntent();
        String temp=thisIntent.getStringExtra("successString");
        textDescription.setText(temp);

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(SuccessScreen.this,MainPage.class);
                startActivity(returnIntent);
                finish();
            }
        });
    }
}
