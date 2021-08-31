package com.example.p4f_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mainpage_layout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigation;
    private ImageView imageView1,imageView2,imageView3,imageView4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbarAction();
        mainpage_layout=findViewById(R.id.mainpage_layout);


        // set clickable for navigation
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            /**
             *
             * @param item
             * declare id as the id item in the menu
             * when we click an item, the id will get item's id through item.getItemId()
             * then we will add the activity for an item.
             * @return
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();       // vd bam home --> get id nut home
                if(id == R.id.n_profile){
                    Intent profileIntent=new Intent(MainPage.this,Profile.class);
                    startActivity(profileIntent);
                }
                if(id==R.id.n_home){
                    Intent homeIntent= new Intent(MainPage.this, MainPage.class);
                    startActivity(homeIntent);
                    finish();
                }
                if(id==R.id.n_search){
                    Intent searchIntent= new Intent(MainPage.this, SearchActivity.class);
                    startActivity(searchIntent);
                    finish();
                }
                return true;
            }
        });
        // find and set click for bottom navigation view

        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_history){
                    Intent historyIntent=new Intent(MainPage.this, history.class);
                    startActivity(historyIntent);
                }
                if(id==R.id.action_cart){
                    Intent cartIntent=new Intent(MainPage.this, cart.class);
                    startActivity(cartIntent);
                }
                if(id==R.id.action_orders){
                    Intent orderIntent=new Intent(MainPage.this, OrderActivity.class);
                    startActivity(orderIntent);
                }
                return true;
            }
        });

        imageView1=(ImageView) findViewById(R.id.imageView1);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView3=(ImageView) findViewById(R.id.imageView3);
        imageView4=(ImageView) findViewById(R.id.imageView4);
        imageView1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView1:
                Intent newIntent= new Intent(MainPage.this,FoodMenu.class);
                startActivity(newIntent);
                finish();
        }
    }

    private void toolbarAction(){
        setSupportActionBar(toolbar);   // cung cap chuc nang cho toolbar

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true); // hien cai nut display
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_layout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_search:
                Intent searchIntent=new Intent(MainPage.this,SearchActivity.class);
                startActivity(searchIntent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}