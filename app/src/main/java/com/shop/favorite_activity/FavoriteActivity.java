package com.shop.favorite_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import com.shop.R;
import com.shop.model.Model;


public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private ArrayList<Model> list;
    private FavoriteAdapter adapter;
    private FavoriteSharedPreference sharedPreference;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        setToolbar();
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPreference = new FavoriteSharedPreference();
        try {
            list = sharedPreference.loadFavorites(context);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        initViews();
    }


    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclearvier);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);




        if (list != null) {

            if(list.isEmpty()){
                Toast.makeText(context,"Your Favorite Item is Empty!!",Toast.LENGTH_SHORT).show();
            }
            try {

                adapter = new FavoriteAdapter(context, list);
                recyclerView.setAdapter(adapter);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(context,"Your Favorite Item is Empty!!",Toast.LENGTH_SHORT).show();

    }


    /*@Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "onResume Called");
        if (list != null) {
            try {
                adapter = new FavoriteAdapter(context, list);
                recyclerView.setAdapter(adapter);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }*/

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Favorite Activity");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}