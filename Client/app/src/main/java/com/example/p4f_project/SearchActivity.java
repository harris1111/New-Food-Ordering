package com.example.p4f_project;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recRes;
    private restaurantAdapter resAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recRes = findViewById(R.id.recycleView_res);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        recRes.setLayoutManager(llm);

        resAdapter=new restaurantAdapter(getListRes());
        recRes.setAdapter(resAdapter);
        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recRes.addItemDecoration(itemDecoration);
    }

    private List<Restaurant> getListRes() {
        List<Restaurant> list= new ArrayList<>();
        list.add(new Restaurant(R.drawable.tcf1,"Pray For Food 1","192 Hai Bà Trưng, Quận 1, TP.HCM"));
        list.add(new Restaurant(R.drawable.tcf2,"Pray For Food 2","65 Nam Kì Khởi Nghĩa, Quận 3, TP.HCM"));
        list.add(new Restaurant(R.drawable.tcf3,"Pray For Food 3","274 Phố Hàng Buồm, Hà Nội"));
        list.add(new Restaurant(R.drawable.tcf4,"Pray For Food 4","284 Hai Bà Trưng, Hà Nội"));
        return list;
    }

}
