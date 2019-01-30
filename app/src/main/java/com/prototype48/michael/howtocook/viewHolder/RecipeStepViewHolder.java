package com.prototype48.michael.howtocook.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.fragment.StepDetailsFragment;
import com.prototype48.michael.howtocook.fragment.StepListFragment;
import com.prototype48.michael.howtocook.model.Step;
import com.prototype48.michael.howtocook.utils.GlobalTagUtils;

public class RecipeStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    GlobalTagUtils tagUtils;
    Step stepClicked;
    Context mContext;

    public RecipeStepViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        tagUtils = new GlobalTagUtils();
        this.mContext = context;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        Boolean tabletPanel = false;

        bundle.putParcelable(tagUtils.STEP_DETAILS_TAG,stepClicked);

        FragmentManager fragmentManager = (((FragmentActivity) mContext).getSupportFragmentManager());

        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();

        stepDetailsFragment.setArguments(bundle);

        if (fragmentManager.findFragmentById(R.id.fragmentLayout_Single) != null)
            tabletPanel = false;

        if (fragmentManager.findFragmentById(R.id.step_details_fragment) != null)
            tabletPanel = true;

        // if the present layout is a single tab fragment
        if (!tabletPanel){
            if (fragmentManager.findFragmentById(R.id.fragmentLayout_Single) != null){
                fragmentManager.beginTransaction().replace(R.id.fragmentLayout_Single,stepDetailsFragment).addToBackStack(null).commit();
            }else{
                fragmentManager.beginTransaction().add(R.id.fragmentLayout_Single,stepDetailsFragment).addToBackStack(null).commit();
            }
        // if the present layout is multi tab fragments
        }else {
            if (fragmentManager.findFragmentById(R.id.step_details_fragment) != null) {
                fragmentManager.beginTransaction().replace(R.id.step_details_fragment, stepDetailsFragment).commit();
            } else {
                fragmentManager.beginTransaction().add(R.id.step_details_fragment, stepDetailsFragment).commit();
            }
        }


    }

    public Step getStepClicked() {
        return stepClicked;
    }

    public void setStepClicked(Step stepClicked) {
        this.stepClicked = stepClicked;
    }
}
