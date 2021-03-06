package com.prototype48.michael.howtocook;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.prototype48.michael.howtocook.Resources.RecipeIdlingResource;
import com.prototype48.michael.howtocook.model.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import androidx.test.core.app.ActivityScenario;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class RecipeListSingleTabLayoutTest {

    RecipeIdlingResource mIdlingResource;



    @Rule
    public ActivityTestRule<RecipeListActivity> mRecipeListTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    @Rule
    public ActivityTestRule<RecipeStepsActivity> mRecipeStepsRule   = new ActivityTestRule<>(RecipeStepsActivity.class);

    @Before
    public void waitforlist(){

        mIdlingResource = mRecipeListTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);

    }

    @Test
    public void clickRecipeCard_ViewSteps(){

//        mIdlingResource = mRecipeListTestRule.getActivity().getIdlingResource();
//        IdlingRegistry.getInstance().register(mIdlingResource);


        //Espresso.onView(ViewMatchers.withId(R.id.recipeListItemLayout)).perform(ViewActions.click());
        RecyclerView recycler = (RecyclerView) mRecipeListTestRule.getActivity().findViewById(R.id.rcv_recipe_list);

        // execute future steps  if the resource is iddle
        Espresso.onIdle();


            int countt = 0;

                // get count from adapter
                try {
                    countt = recycler.getAdapter().getItemCount();
                }catch (Exception e){
                    countt = 0;
                }


            // load data based on count
            if (countt > 0) {
                for (int i = 0; i < countt - 1; i++) {
                    // click a recipe
                    onView(ViewMatchers.withId(R.id.rcv_recipe_list))
                            .perform(RecyclerViewActions.actionOnItemAtPosition(i, ViewActions.click()));

                    recipeCard_ViewStepsDetails(i);

                    pressBack();

                    //onView(ViewMatchers.withId(R.id.rcv_recipe_list)).check(matches(isDisplayed()));

                    Log.d("ITEM ", String.valueOf(i) + "sucesso");


                }
            } else {
                throw new Error("unloaded list");
            }
       // }

    }


    public void recipeCard_ViewStepsDetails(int recipePosition){

        RecyclerView recycler = (RecyclerView) mRecipeStepsRule.getActivity().findViewById(R.id.rcvStepList);

        int count = 0;



            try {
                count = recycler.getAdapter().getItemCount();
            } catch (Exception e) {
                count = 0;
            }
            if (count > 0) {
                for (int i = 0; i < count - 1; i++) {

                    onView(ViewMatchers.withId(R.id.rcvStepList)).perform(RecyclerViewActions.actionOnItemAtPosition(i, ViewActions.click()));

                    onView(ViewMatchers.withId(R.id.stepVideoPlayer)).check(ViewAssertions.matches(isDisplayed()));

                    onView(ViewMatchers.withId(R.id.txvStepTitle)).check(ViewAssertions.matches(isDisplayed()));

                    onView(ViewMatchers.withId(R.id.txvStepDescription)).check(ViewAssertions.matches(isDisplayed()));

                    pressBack();

                }
            } else {
                throw new Error("unloaded list");
            }

    }

}
