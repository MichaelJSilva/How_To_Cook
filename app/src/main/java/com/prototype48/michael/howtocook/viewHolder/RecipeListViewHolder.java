package com.prototype48.michael.howtocook.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prototype48.michael.howtocook.RecipeStepsActivity;
import com.prototype48.michael.howtocook.customInterface.ViewHolderInterface;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.utils.GlobalTagUtils;

public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Recipe mRecipe;
    GlobalTagUtils tagUtils;

    public RecipeListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tagUtils = new GlobalTagUtils();
        itemView.setOnClickListener(this);

    }

    public Recipe getmRecipe() {
        return mRecipe;
    }

    public void setmRecipe(Recipe mRecipe) {
        this.mRecipe = mRecipe;
    }

    @Override
    public void onClick(View v) {

        Context mContext = v.getContext();

        Intent intent = new Intent(mContext,RecipeStepsActivity.class);

        intent.putExtra(tagUtils.RECIPE__TAG,mRecipe);
        intent.putParcelableArrayListExtra(tagUtils.RECIPE_STEPS_TAG,mRecipe.getRecipeSteps());

        mContext.startActivity(intent);

    }
}
