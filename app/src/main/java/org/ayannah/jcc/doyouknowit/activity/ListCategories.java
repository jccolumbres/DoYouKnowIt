package org.ayannah.jcc.doyouknowit.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.adapter.CategoriesAdapter;
import org.ayannah.jcc.doyouknowit.api.CategoriesAPI;
import org.ayannah.jcc.doyouknowit.database.DataSource;
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
    DataSource mDataSourceOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        myRecyclerView = (RecyclerView) findViewById(R.id.rv_container);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        customAdapter = new CategoriesAdapter(dataSource, R.layout.item_layout_categories, getApplicationContext());
        myRecyclerView.setAdapter(customAdapter);
        mDataSourceOffline = new DataSource(this);
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSourceOffline.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSourceOffline.open();
    }

    public void loadData() {
        CategoriesAPI categoriesService =
                NetworkClient.buildConnection(CategoriesAPI.class);
        final Call<List<Categories>> call = categoriesService.getCategories(20);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if (response.isSuccessful()) {
                    dataSource.clear();
                    dataSource.addAll(response.body());
                    long count = mDataSourceOffline.getDBItems();
                    if (count == 0) {
                        for (Categories category : dataSource) {
                            mDataSourceOffline.createCategory(category);
                            Log.i("XHITE","Category inserted: " + category.getTitle());
                        }
                    }else{
                        Toast.makeText(ListCategories.this, "Data already saved", Toast.LENGTH_SHORT).show();
                    }
                    customAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ctx, "Failed to retrieve items", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(ctx, "Connection error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "Failed to retrieve items", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}

