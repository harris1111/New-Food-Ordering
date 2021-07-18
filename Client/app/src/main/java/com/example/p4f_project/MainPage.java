package com.example.p4f_project;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.p4f_project.MenuFunctions.BackToMainPage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity  {
    private DrawerLayout mainpage_layout;
    private NavigationView navigationView;
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
                return true;
            }
        });

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


}