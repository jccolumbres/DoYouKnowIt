package org.ayannah.jcc.doyouknowit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.adapter.QuestionsAdapter;
import org.ayannah.jcc.doyouknowit.api.CategoryAPI;
import org.ayannah.jcc.doyouknowit.models.Category;
import org.ayannah.jcc.doyouknowit.models.Clues;
import org.ayannah.jcc.doyouknowit.network.NetworkClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListQuestions extends AppCompatActivity {

    TextView categoryName;
    String category;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter customAdapter;
    List<Clues> dataSource = new ArrayList<>();
    int intId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        categoryName = (TextView) findViewById(R.id.tvLabel);
        Bundle extras = getIntent().getExtras();
        intId = Integer.parseInt(extras.getString("id"));
        category = extras.getString("CategoryName");
        categoryName.setText(category);
        myRecyclerView = (RecyclerView) findViewById(R.id.rv_container_clues);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new QuestionsAdapter(dataSource,R.layout.item_layout_questions,getApplicationContext());
        myRecyclerView.setAdapter(customAdapter);
        loadData();

    }

    public void loadData(){
        CategoryAPI categoryService = NetworkClient.buildConnection(CategoryAPI.class);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_container);
        Call<Category> call = categoryService.getCategory(intId);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()) {
                    dataSource.clear();
                    dataSource.addAll(response.body().getClues());
                    customAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed to retrieve items",Toast.LENGTH_SHORT).show();
                }
                relativeLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(getApplicationContext(),"Connection error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed to retrieve items",Toast.LENGTH_SHORT).show();
                }
                relativeLayout.setVisibility(View.GONE);
            }
        });
    }
}
