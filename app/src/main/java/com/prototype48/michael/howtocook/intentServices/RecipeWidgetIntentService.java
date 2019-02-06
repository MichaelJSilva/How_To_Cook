package com.prototype48.michael.howtocook.intentServices;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.prototype48.michael.howtocook.R;
import com.prototype48.michael.howtocook.RecipeWidget;
import com.prototype48.michael.howtocook.model.Recipe;
import com.prototype48.michael.howtocook.utils.JsonUtils;

import androidx.annotation.Nullable;

public class RecipeWidgetIntentService extends IntentService {

    private static Recipe mRecipe;

    public RecipeWidgetIntentService(){
        super("Recipe_Service");
    }


    public static final String ACTION_UPDATE_RECIPE = "com.prototype48.michael.howtocook.action.update_recipe";
    public static final String RECIPE_SELECT = "Select a recipe";

    public static void updateRecipeData(Context context) {
        Intent intent = new Intent(context, RecipeWidgetIntentService.class);
        if (mRecipe != null) {
            passDataToWidget(mRecipe, context);
        }
        intent.setAction(ACTION_UPDATE_RECIPE);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE.equals(action)) {
                updateRecipeData(this);
            }
        }
    }


    private static void passDataToWidget(Recipe recipe,Context context){

        String recipeTitle = RECIPE_SELECT;
        String recipeIngredients = RECIPE_SELECT;

        if (recipe != null){

            recipeTitle = recipe.getName();
            recipeIngredients = JsonUtils.ingredientsAsText(recipe.getIngredientList());

        }

        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);

        int[] widgetIds = widgetManager.getAppWidgetIds(new ComponentName(context,RecipeWidget.class));

        RecipeWidget.updateRecipeWidget(context,widgetManager,widgetIds,recipeTitle,recipeIngredients);



    }

    public Recipe getmRecipe() {
        return mRecipe;
    }

    public void setmRecipe(Recipe mRecipe) {
        this.mRecipe = mRecipe;
    }
}
