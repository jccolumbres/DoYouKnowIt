package org.ayannah.jcc.doyouknowit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.models.Categories;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{
    private List<Categories> categories;
    private int rowLayout;
    private Context ctx;

    public CategoriesAdapter(List<Categories> categories, int rowLayout, Context ctx) {
        this.setCategories(categories);
        this.setRowLayout(rowLayout);
        this.setCtx(ctx);
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(rowLayout,viewGroup,false );

        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int i) {
        holder.categoryId.setText(categories.get(i).getId());
        holder.categoryName.setText(categories.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView categoryName,categoryId;

        public CategoriesViewHolder(View v){
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
            categoryName = (TextView)v.findViewById(R.id.tvCategoryName);
            categoryId = (TextView)v.findViewById(R.id.tvCategoryId);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), categoryId.getText().toString(),Toast.LENGTH_SHORT ).show();
        }
    }
}


