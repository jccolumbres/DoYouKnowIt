package org.ayannah.jcc.doyouknowit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.activity.ListQuestions;
import org.ayannah.jcc.doyouknowit.models.Categories;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{
    private List<Categories> categories;
    private int rowLayout;
    private Context ctx;
    private int lastPosition = -1;
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

        setAnimation(holder.cardView, i );
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
            cardView = (CardView) v.findViewById(R.id.card_view_1);
            cardView.setOnClickListener(this);
            categoryName = (TextView)v.findViewById(R.id.tvCategoryName);
            categoryId = (TextView)v.findViewById(R.id.tvCategoryId);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), categoryId.getText().toString(),Toast.LENGTH_SHORT ).show();
            Intent i = new Intent(v.getContext(),ListQuestions.class);
            i.putExtra("id",categoryId.getText().toString());
            v.getContext().startActivity(i);
        }
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(ctx, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}


