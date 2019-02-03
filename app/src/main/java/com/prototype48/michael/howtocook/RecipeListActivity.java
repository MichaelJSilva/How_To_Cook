package com.prototype48.michael.howtocook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.prototype48.michael.howtocook.Resources.RecipeIdlingResource;
import com.prototype48.michael.howtocook.adapter.RecipeListAdapter;
import com.prototype48.michael.howtocook.interfaces.RecipeInterface;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.utils.JsonUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;



public class RecipeListActivity extends AppCompatActivity  {

    // const
    private final String RECIPE_LIST_TAG = "recipes";
    private static final String RECIPE_LIST_URL = "https://d17h27t6h515a5.cloudfront.net/";

    // var
    String mRecipeJson;
    ArrayList<Recipe> mRecipes = new ArrayList<>();

    // view
    RecyclerView mRecipeList;
    RecipeListAdapter mRecipeListAdapter;

    //test
    @Nullable RecipeIdlingResource recipeIdlingResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipeIdlingResource = new RecipeIdlingResource();



        // view with recyclerview
        mRecipeList = findViewById(R.id.rcv_recipe_list);

        // create new adapter
        mRecipeListAdapter = new RecipeListAdapter(this);

        this.getRecipes();





    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE_LIST_TAG,mRecipes);

    }

    private void loadRecipeList(RecyclerView list,RecipeListAdapter adapter){

        list.setAdapter(null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        list.setLayoutManager(layoutManager);

        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    // get recipes method
    public void getRecipes(){
        recipeIdlingResource.setIdleState(false);

        // get recipes from endpoint
      //  countingIdlingResource.increment();

        // create retrofit service
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RECIPE_LIST_URL)
                .build();

        RecipeInterface service = retrofit.create(RecipeInterface.class);


        // call data from endpoint
        Call<ArrayList<Recipe>> recipeCall = service.getRecipes();

        // execute asynctask
        recipeCall.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                Log.d("SUCESSO",call.request().toString());

                List<Recipe> responseList = response.body();

                responseList.size();

                mRecipes.addAll(responseList);

                if (mRecipes.size() > 0){
                    mRecipeListAdapter.setmRecipes(mRecipes);
                    loadRecipeList(mRecipeList,mRecipeListAdapter);
                }
                recipeIdlingResource.setIdleState(true);


            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.d("ERRO",call.request().toString());

            }
        });
    };

    @VisibleForTesting
    @NonNull
    public RecipeIdlingResource getIdlingResource() {


        return recipeIdlingResource;
    }


}
