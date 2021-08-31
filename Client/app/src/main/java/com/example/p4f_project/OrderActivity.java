package com.example.p4f_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<Product> foodList;
    ImageView backbtt;
    Button confirmPurchase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        retrieveCartList();
        findID();
        backbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent= new Intent(OrderActivity.this,cart.class);
                backIntent.putParcelableArrayListExtra("food_list", foodList);
                startActivity(backIntent);
            }
        });
        confirmPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nhay qua man hinh ket qua thanh cong / that bai
            }
        });
    }
    private void findID(){
         backbtt=(ImageView) findViewById(R.id.back2cart);
         confirmPurchase= (Button) findViewById(R.id.Confirm);
    }

    private void retrieveCartList() {
        Intent intent = getIntent();
        ArrayList<Product> newList = intent.getParcelableArrayListExtra("cart_list");
        if (foodList == null) {
            foodList = newList;
            newList = null;
        }
        if(foodList!=null){
            TextView totalbox;
            final ListView listView = (ListView) findViewById(R.id.orderListView);
            listView.setAdapter(new OrderListAdapter(this,foodList));
            // Iterate list view to calculate total price
            long total = 0;
            for (int i = 0; i < listView.getCount(); ++i) {
                View v = listView.getAdapter().getView(i, null, null);
                if (v != null) {
                    totalbox = (TextView) v.findViewById(R.id.Orderedtotal);
                    total += Integer.parseInt(totalbox.getText().toString().substring(7, totalbox.getText().length()-4));
                }
            }
            TextView detailBox=(TextView) findViewById(R.id.Orderdetails);
            detailBox.setText("Total: "+String.valueOf(total)+" VND");
        }
    }
}
