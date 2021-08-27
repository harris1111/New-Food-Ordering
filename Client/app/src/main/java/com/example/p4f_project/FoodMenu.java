package com.example.p4f_project;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FoodMenu extends AppCompatActivity implements View.OnClickListener{
    ImageView add1,add2,add3,add4,backBtt;
    Button cartButton;
    ArrayList<Boolean> tempArr=new ArrayList<Boolean>(Collections.nCopies(4,false));
    ArrayList<Product> foodList=new ArrayList<Product>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        findID();
        add1.setOnClickListener(this);
        add2.setOnClickListener(this);
        add3.setOnClickListener(this);
        add4.setOnClickListener(this);
        backBtt.setOnClickListener(this);
        cartButton.setOnClickListener(this);

    }
    public void findID(){
        add1=(ImageView) findViewById(R.id.addToCart1);
        add2=(ImageView) findViewById(R.id.addToCart2);
        add3=(ImageView) findViewById(R.id.addToCart3);
        add4=(ImageView) findViewById(R.id.addToCart4);
        backBtt=(ImageView) findViewById(R.id.back2main);
        cartButton=(Button) findViewById(R.id.CartButton);
    }

    @Override
    public void onClick(View v) {
        BufferedReader reader = null;
        AssetManager assetManager = p4f_project.getContext().getResources().getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("Food.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(is!=null){
            reader=new BufferedReader(new InputStreamReader(is));

        }
        switch(v.getId()){
            case R.id.addToCart1:
                if(tempArr.get(0) ==false){
                    readFood(reader,0);
                    tempArr.set(0,true);
                }
                else{
                    for(int j =0;j<foodList.size();j++){
                        if(foodList.get(j).getID()=="Item1") {
                            foodList.get(j).increaseAmount(1);
                            break;
                        }
                    }
                }
                break;
            case R.id.addToCart2:
                if(tempArr.get(1) ==false){
                    readFood(reader,1);
                    tempArr.set(1,true);
                }
                else{
                    for(int j =0;j<foodList.size();j++){
                        if(foodList.get(j).getID()=="Item2") {
                            foodList.get(j).increaseAmount(1);
                            break;
                        }
                    }
                }
                break;
            case R.id.addToCart3:
                if(tempArr.get(2) ==false){
                    readFood(reader,2);
                    tempArr.set(2,true);
                }
                else{
                    for(int j =0;j<foodList.size();j++){
                        if(foodList.get(j).getID()=="Item3") {
                            foodList.get(j).increaseAmount(1);
                            break;
                        }
                    }
                }
                break;
            case R.id.addToCart4:
                if(tempArr.get(3) ==false){
                    readFood(reader,3);
                    tempArr.set(3,true);
                }
                else{
                    for(int j =0;j<foodList.size();j++){
                        if(foodList.get(j).getID()=="Item4") {
                            foodList.get(j).increaseAmount(1);
                            break;
                        }
                    }
                }
                break;
            case R.id.back2main:
                Intent backI=new Intent(FoodMenu.this,MainPage.class);
                startActivity(backI);
                finish();
                break;
            case R.id.CartButton:
                Intent cartIntent= new Intent(FoodMenu.this,cart.class);
                startActivity(cartIntent);
//                while (cart.cartScreenHandler == null) {
//                    continue;
//                }
                Message msg=Message.obtain(cart.cartScreenHandler);
                msg.what = 15;
                msg.obj = foodList;
                foodList = null;
                msg.sendToTarget();
                break;
        }
    }

    private void readFood(BufferedReader reader,int ind){
        String temp;
        int i=0;
        try{
            while((temp=reader.readLine())!=null){
                if(i<ind){
                    for(int j=0;j<3;j++){
                        reader.readLine();
                    }
                    continue;
                }
                String id=temp;
                String name=reader.readLine();
                String Des=reader.readLine();
                int price=Integer.parseInt(reader.readLine());
                foodList.add(new Product(temp,name,Des,price));
                break;
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
}

