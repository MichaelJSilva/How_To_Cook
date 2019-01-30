package com.prototype48.michael.howtocook;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.prototype48.michael.howtocook.fragment.StepDetailsFragment;
import com.prototype48.michael.howtocook.fragment.StepListFragment;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.model.Step;
import com.prototype48.michael.howtocook.utils.GlobalTagUtils;

import java.util.ArrayList;

public class RecipeStepsActivity extends AppCompatActivity {

    // views
    PlayerView mPlayerView;
    TextView   mStepTitle;
    TextView   mStepDescription;
    // Recipe
    Recipe mPositionedRecipe;
    ArrayList<Step> stepList;
    GlobalTagUtils tagUtils = new GlobalTagUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        if (savedInstanceState != null){
            mPositionedRecipe = savedInstanceState.getParcelable(tagUtils.RECIPE__TAG);
            stepList = savedInstanceState.getParcelableArrayList(tagUtils.RECIPE_STEPS_TAG);
        }else{
            Intent intent = getIntent();
            mPositionedRecipe = intent.getParcelableExtra(tagUtils.RECIPE__TAG);
            stepList          = intent.getParcelableArrayListExtra(tagUtils.RECIPE_STEPS_TAG);
        }


        if (findViewById(R.id.fragment_steps_tablet_layout) != null){

            loadStepsFragment(stepList,false);
            loadStepDetailsFragment(stepList.get(0),false);

        }else{
            loadStepsFragment(stepList,true);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(tagUtils.RECIPE__TAG,this.mPositionedRecipe);
        outState.putParcelableArrayList(tagUtils.RECIPE_STEPS_TAG,this.stepList);
    }

    private void loadStepsFragment(ArrayList<Step> steps,Boolean singlePanel){
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList(tagUtils.RECIPE_STEPS_TAG,steps);

        FragmentManager fragmentManager = getSupportFragmentManager();

        StepListFragment stepListFragment = new StepListFragment();

        stepListFragment.setArguments(bundle);

        if (singlePanel){
            fragmentManager.beginTransaction().add(R.id.fragmentLayout_Single,stepListFragment).commit();
        }else{
            fragmentManager.beginTransaction().replace(R.id.step_list_fragment,stepListFragment).commit();
        }

    }

    private void loadStepDetailsFragment(Step step,Boolean singlePanel){

        Bundle bundle = new Bundle();

        bundle.putParcelable(tagUtils.STEP_DETAILS_TAG,step);

        FragmentManager fragmentManager = getSupportFragmentManager();

        StepDetailsFragment stepDetails = new StepDetailsFragment();

        stepDetails.setArguments(bundle);

        if (singlePanel){
            fragmentManager.beginTransaction().add(R.id.fragmentLayout_Single,stepDetails).addToBackStack(null).commit();
        }else{
            fragmentManager.beginTransaction().replace(R.id.step_details_fragment,stepDetails).commit();
        }

    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
