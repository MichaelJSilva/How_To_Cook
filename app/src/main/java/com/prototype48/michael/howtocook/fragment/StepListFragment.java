package com.prototype48.michael.howtocook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.adapter.RecipeStepsAdapter;
import com.prototype48.michael.howtocook.model.Step;
import com.prototype48.michael.howtocook.utils.GlobalTagUtils;

import java.util.ArrayList;

public class StepListFragment extends Fragment {

    //var
    ArrayList<Step> mArraySteps;

    //views
    RecyclerView mStepList;

    //adapter
    RecipeStepsAdapter recipeStepsAdapter;

    // misc
    GlobalTagUtils tagUtils = new GlobalTagUtils();

    public StepListFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        recipeStepsAdapter = new RecipeStepsAdapter(getContext());
        if (bundle != null) {
            this.mArraySteps = bundle.getParcelableArrayList(tagUtils.RECIPE_STEPS_TAG);
        }else{
            this.mArraySteps = null;
        }

        recipeStepsAdapter.setmArraySteps(mArraySteps);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mStepList = view.findViewById(R.id.rcvStepList);

        mStepList.setLayoutManager(layoutManager);

        mStepList.setAdapter(recipeStepsAdapter);

        recipeStepsAdapter.notifyDataSetChanged();

        return view;

    }
}
