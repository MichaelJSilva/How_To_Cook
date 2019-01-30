package com.prototype48.michael.howtocook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.model.Step;
import com.prototype48.michael.howtocook.viewHolder.RecipeStepViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepViewHolder> {

    //var
    Context mContext;

    ArrayList<Step> mArraySteps;
    TextView txvStepName;

    public RecipeStepsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Step> getmArraySteps() {
        return mArraySteps;
    }

    public void setmArraySteps(ArrayList<Step> mArraySteps) {
        this.mArraySteps = mArraySteps;
    }

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.step_list_item,viewGroup,false);

        txvStepName = view.findViewById(R.id.txvStepName);

        return new RecipeStepViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder recipeStepViewHolder, int position) {

        Step actualStep = mArraySteps.get(position);

        recipeStepViewHolder.setStepClicked(actualStep);

        txvStepName.setText(actualStep.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mArraySteps != null){
            return mArraySteps.size();
        }else{
            return 0;
        }

    }
}
