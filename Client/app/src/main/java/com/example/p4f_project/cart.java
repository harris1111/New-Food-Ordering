package com.example.p4f_project;

import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    ImageView back;
    public static Handler cartScreenHandler = null;
    ArrayList<Product> foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        back=(ImageView) findViewById(R.id.back2main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(cart.this,FoodMenu.class);
                myIntent.putParcelableArrayListExtra("cart_list", foodList);
                startActivity(myIntent);
            }
        });
        // Restore old cart
//        if (savedInstanceState != null) {
//            foodList = savedInstanceState.getParcelableArrayList("cart_list");
//            Log.d("CHECK: ", "RESTORING FOOD CART ON CREATE");
//            if (foodList != null)
//                Log.d("CHECK: ", "FOOD CART IS NOT NULL");
//            else Log.d("CHECK: ", "FOOD CART IS NULL");
//        }
        Intent intent = getIntent();
        ArrayList<Product> newList = intent.getParcelableArrayListExtra("food_list");
        if (foodList == null) {
            foodList = newList;
            newList = null;
        }
//        else {
//            for (Product item : newList) {
//                boolean exist = false;
//                for (int i = 0; i < foodList.size(); ++i) {
//                    if (foodList.get(i).getID().equals(item.getID())) {
//                        exist = true;
//                        foodList.get(i).increaseAmount(item.getAmount());
//                        break;
//                    }
//                }
//                if (!exist) {
//                    foodList.add(item);
//                }
//            }
//        }
        if(foodList!=null){
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(this,foodList));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("cart_list", foodList);
        Log.d("CHECK: ", "SAVING FOOD CART");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        foodList = savedInstanceState.getParcelableArrayList("cart_list");
        Log.d("CHECK: ", "RESTORING FOOD CART ON RESTORE");
    }
}