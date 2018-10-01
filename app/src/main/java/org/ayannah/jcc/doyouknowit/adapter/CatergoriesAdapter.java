package org.ayannah.jcc.doyouknowit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.models.Categories;

import java.util.List;

public class CatergoriesAdapter extends RecyclerView.Adapter<CatergoriesAdapter.CategoriesViewHolder>{
    private List<Categories> categories;
    private int rowLayout;
    private Context ctx;

    public CatergoriesAdapter(List<Categories> categories, int rowLayout, Context ctx) {
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
        holder.categoryName.setText(categories.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView categoryName;

        public CategoriesViewHolder(View v){
            super(v);
            linearLayout = (LinearLayout) v.findViewById(R.id.ll_main);
            categoryName = (TextView)v.findViewById(R.id.tvCategoryName);
        }

    }
}


