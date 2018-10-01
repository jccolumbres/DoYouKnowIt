package org.ayannah.jcc.doyouknowit.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.adapter.CategoriesAdapter;
import org.ayannah.jcc.doyouknowit.api.CategoriesAPI;
import org.ayannah.jcc.doyouknowit.models.Categories;
import org.ayannah.jcc.doyouknowit.network.NetworkClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCategories extends AppCompatActivity {

    final Context ctx = this;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter customAdapter;
    List<Categories> dataSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        myRecyclerView = (RecyclerView) findViewById(R.id.rv_container);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        customAdapter = new CategoriesAdapter(dataSource, R.layout.item_layout, getApplicationContext());
        myRecyclerView.setAdapter(customAdapter);
        loadData();
    }

    public void loadData(){
        CategoriesAPI categoriesService =
                NetworkClient.buildConnection(CategoriesAPI.class);
        Call<List<Categories>> call = categoriesService.getCategories(10);
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if (response.isSuccessful()){
                    dataSource.clear();
                    dataSource.addAll(response.body());
                    customAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(ctx,"Failed to retrieve items",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(ctx,"Connection error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ctx,"Failed to retrieve items",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

