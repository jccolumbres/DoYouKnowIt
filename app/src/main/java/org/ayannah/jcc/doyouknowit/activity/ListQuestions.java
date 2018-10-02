package org.ayannah.jcc.doyouknowit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.adapter.QuestionsAdapter;
import org.ayannah.jcc.doyouknowit.api.CategoryAPI;
import org.ayannah.jcc.doyouknowit.models.Category;
import org.ayannah.jcc.doyouknowit.models.Clues;
import org.ayannah.jcc.doyouknowit.network.NetworkClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListQuestions extends AppCompatActivity {

    RecyclerView myRecyclerView;
    RecyclerView.Adapter customAdapter;
    List<Clues> dataSource = new ArrayList<>();
    int intId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        Bundle extras = getIntent().getExtras();
        intId = Integer.parseInt(extras.getString("id"));
//        Toast.makeText(this, stringId.toString(), Toast.LENGTH_SHORT).show();
        myRecyclerView = (RecyclerView) findViewById(R.id.rv_container_clues);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new QuestionsAdapter(dataSource,R.layout.item_layout_questions,getApplicationContext());
        myRecyclerView.setAdapter(customAdapter);
        loadData();

    }

    public void loadData(){
        CategoryAPI categoryService = NetworkClient.buildConnection(CategoryAPI.class);
        Call<Category> call = categoryService.getCategory(intId);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                dataSource.clear();
                dataSource.addAll(response.body().getClues());
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
