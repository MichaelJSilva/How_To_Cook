package com.prototype48.michael.howtocook.utils;

import com.prototype48.michael.howtocook.model.Ingredient;
import com.prototype48.michael.howtocook.model.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonUtils {

    public static String jsonToString(InputStream inputStream){
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,bytes.length);
            String json = new String(bytes);
            return json;
        }catch(IOException e){
            return null;
        }
    }

    public static String ingredientsAsText(ArrayList<Ingredient> array){

        String text = "";

        for(int i = 0; i < array.size();i++){

            Ingredient currentItem = array.get(i);

            float quantity = currentItem.getQuantity();

            String measure = currentItem.getMeasure();

            String ingredient = currentItem.getIngredient();



            text = text + String.format("%.2f %s  of %s  \n " + System.lineSeparator(),quantity,measure,ingredient);
        }

        return text;
    }

}
