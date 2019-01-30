package com.prototype48.michael.howtocook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.utils.JsonUtils;
import com.prototype48.michael.howtocook.viewHolder.RecipeListViewHolder;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListViewHolder>{

    Context mContext;
    ArrayList<Recipe> mRecipes;
    // list views
    private TextView  mRecipeTitle;
    private TextView  mRecipeIngredients;
    private ImageView mRecipeImage;

    public RecipeListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.recipe_list_item,viewGroup,false);

        mRecipeTitle = view.findViewById(R.id.txv_recipe_title);

        mRecipeIngredients = view.findViewById(R.id.txv_recipe_ingredients);

        mRecipeImage = view.findViewById(R.id.imv_recipe_photo);

        return new RecipeListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder recipeListViewHolder, int position) {

        Recipe recipe = mRecipes.get(position);

        recipeListViewHolder.setmRecipe(recipe);

        mRecipeTitle.setText(recipe.getName());

        mRecipeIngredients.setText(JsonUtils.ingredientsAsText(recipe.getIngredientList()));

        Glide.with(mContext)
                .load(recipe.getImage())
                .apply(new RequestOptions().centerCrop().placeholder(R.drawable.recipe_image_not_found))
                .into(mRecipeImage);

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public ArrayList<Recipe> getmRecipes() {
        return mRecipes;
    }

    public void setmRecipes(ArrayList<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
    }
}
