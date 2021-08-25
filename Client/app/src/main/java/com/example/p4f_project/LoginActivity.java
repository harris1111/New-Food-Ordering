package com.example.p4f_project;

import android.content.res.AssetManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.p4f_project.BackEnd.ContainerClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    float v=0;
    public static Thread worker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AssetManager assetManager = p4f_project.getContext().getResources().getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("server.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is != null) {
            worker = new Thread(new ContainerClient(is, 9999));
            worker.setDaemon(true);
            worker.start();
        }
        // find id of these things
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(loginAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTranslationY(0);

        tabLayout.setAlpha(v);

        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(2);
    }


}