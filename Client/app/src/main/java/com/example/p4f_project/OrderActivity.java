package com.example.p4f_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p4f_project.protocols.Order;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<Product> foodList;
    ImageView backbtt;
    SharedPreferences prefGet;
    SharedPreferences.Editor prefGetEdit;
    Button confirmPurchase;
    String userName,userAddress,userPhone;
    public static Handler orderActivityHandler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // nhay qua man hinh ket qua thanh cong / that bai
                prefGet = getApplicationContext().getSharedPreferences("user_info", MODE_PRIVATE);
                Order order = Order.newBuilder()
                        .setUsername(prefGet.getString("Username", null))
                        .setBuyDate((java.time.LocalDate.now().toString()))
                        .setResID(String.valueOf(1))
                        .setFoodList(1,)

)

            }
        });
        // Get SharedPrereferences
        prefGet = getApplicationContext().getSharedPreferences("user_info", MODE_PRIVATE);
        prefGetEdit = prefGet.edit();
        getUserInform();
        retrieveCartList();
    }
    private void findID(){
         backbtt=(ImageView) findViewById(R.id.back2cart);
         confirmPurchase= (Button) findViewById(R.id.Confirm);
    }
    private void getUserInform(){
         userName=prefGet.getString("Username", null);
         userAddress=prefGet.getString("UserAddress", null);
         userPhone=prefGet.getString("UserPhone", null);
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
            detailBox.setText("Total: "+String.valueOf(total)+" VND\nName: "+userName+"\nAddress: "+userAddress+"\nPhone: "+userPhone);
        }
    }
}
