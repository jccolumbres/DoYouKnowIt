package org.ayannah.jcc.doyouknowit.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ayannah.jcc.doyouknowit.R;
import org.ayannah.jcc.doyouknowit.models.Clues;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>{
    private List<Clues> questions;
    private int rowLayout;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context ctx;


    public QuestionsAdapter(List<Clues> questions, int rowLayout, Context ctx) {
        this.questions = questions;
        this.rowLayout = rowLayout;
        this.ctx = ctx;

        for (int i = 0 ; i < questions.size(); i++){
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx)
                .inflate(rowLayout,viewGroup,false );

        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionsViewHolder holder, final int i) {
        holder.setIsRecyclable(false);
        holder.question.setText(questions.get(i).getQuestion());
        holder.answer.setText(questions.get(i).getAnswer());

        final boolean isExpanded = expandState.get(i);
        holder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        holder.buttonLayout.setRotation(expandState.get(i)? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableLayout, holder.buttonLayout, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView question, answer;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        public QuestionsViewHolder(View view){
            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view_2);
            question = (TextView) view.findViewById(R.id.tvQuestion);
            answer = (TextView)view.findViewById(R.id.tvAnswer);

            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);

        }
    }


    private void onClickButton(final LinearLayout expandableLayout,
                               final RelativeLayout buttonLayout , final int i){
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    private ObjectAnimator createRotateAnimator(final View target, final float from,
                                                final float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(target,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}
