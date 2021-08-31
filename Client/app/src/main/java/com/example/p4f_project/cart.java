package com.example.p4f_project;

import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    ImageView back;
    Button orderButton;
    public static Handler cartScreenHandler = null;
    ArrayList<Product> foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        findID();

        // back to food menu click
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(cart.this,FoodMenu.class);
                myIntent.putParcelableArrayListExtra("cart_list", foodList);
                startActivity(myIntent);
            }
        });
        // foward to Order screen
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent=new Intent(cart.this,OrderActivity.class);
                orderIntent.putParcelableArrayListExtra("cart_list", foodList);
                startActivity(orderIntent);
            }
        });
        Intent intent = getIntent();
        ArrayList<Product> newList = intent.getParcelableArrayListExtra("food_list");
        if (foodList == null) {
            foodList = newList;
            newList = null;
        }

        if(foodList!=null){
            TextView totalbox;
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(this,foodList));
            // Iterate list view to calculate total price
            long total = 0;
            for (int i = 0; i < listView.getCount(); ++i) {
                View v = listView.getAdapter().getView(i, null, null);
                if (v != null) {
                    totalbox = (TextView) v.findViewById(R.id.total);
                    total += Integer.parseInt(totalbox.getText().toString().substring(7, totalbox.getText().length()-4));
                }
            }
            TextView totalpriceBox=(TextView) findViewById(R.id.totalPrice);
            totalpriceBox.setText("Total: "+String.valueOf(total)+" VND");
        }
    }
    private void findID(){
        back=(ImageView) findViewById(R.id.back2main);
        orderButton=(Button) findViewById(R.id.orderButton);
    }
}