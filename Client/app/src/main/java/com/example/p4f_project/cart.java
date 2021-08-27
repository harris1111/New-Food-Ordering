package com.example.p4f_project;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    ImageView back;
    public static Handler cartScreenHandler = null;
    ArrayList<Product> foodList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        back=(ImageView) findViewById(R.id.back2main);
        cartScreenHandler= new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 15) {
                    System.out.println("Receive message");
                    if (foodList == null) {
                        foodList = (ArrayList<Product>) msg.obj;
                    }
                    else {
                        ArrayList<Product> newList = (ArrayList<Product>) msg.obj;
                        for (Product item : newList) {
                            boolean exist = false;
                            for (int i = 0; i < foodList.size(); ++i) {
                                if (foodList.get(i).getID().equals(item.getID())) {
                                    exist = true;
                                    foodList.get(i).increaseAmount(item.getAmount());
                                    break;
                                }
                            }
                            if (!exist) {
                                foodList.add(item);
                            }
                        }
                    }
                }
            }
        };
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(cart.this,MainPage.class);
                startActivity(myIntent);
                finish();
            }
        });

        if(foodList!=null){
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(this,foodList));
        }
    }

}