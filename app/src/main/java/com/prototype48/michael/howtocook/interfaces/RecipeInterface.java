package com.prototype48.michael.howtocook.interfaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.prototype48.michael.howtocook.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Recipe>> getRecipes();


}
