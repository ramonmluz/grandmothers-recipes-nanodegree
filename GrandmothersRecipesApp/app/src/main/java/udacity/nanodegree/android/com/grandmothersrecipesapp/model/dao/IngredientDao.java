package udacity.nanodegree.android.com.grandmothersrecipesapp.model.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.provider.IngredientContract;
import udacity.nanodegree.android.com.grandmothersrecipesapp.provider.IngredientContract.IngredientEntry;
import udacity.nanodegree.android.com.grandmothersrecipesapp.util.Constants;

import static udacity.nanodegree.android.com.grandmothersrecipesapp.provider.IngredientContract.IngredientEntry.TABLE_NAME;

public class IngredientDao {
    private ContentResolver contentResolver;

    public IngredientDao(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Cursor countIngredientSaved(int recipeId) {
        return contentResolver.query(IngredientEntry.CONTENT_URI, new String[]{IngredientEntry.COLUMN_INGREDIENT_NAME},
                IngredientEntry.COLUMN_ID_RECIPE + " = ?", new String[]{String.valueOf(recipeId)}, null);
    }

    public void insertIngredients(List<Ingredient> ingredients, int idRecipe) {
        int sizeList = ingredients.size();
        ContentValues[] contentValuesArray = new ContentValues[sizeList];
        for (int i = 0; i < sizeList; i++) {
            Ingredient ingredient = ingredients.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(IngredientEntry.COLUMN_ID_RECIPE, idRecipe);
            contentValues.put(IngredientEntry.COLUMN_QUANTITY, ingredient.getQuantity());
            contentValues.put(IngredientEntry.COLUMN_INGREDIENT_NAME, ingredient.getIngredient());
            contentValuesArray[i] = contentValues;
        }

        try {
            int result = contentResolver.bulkInsert(IngredientEntry.CONTENT_URI, contentValuesArray);

            if (result != 0) {
                Log.d(TABLE_NAME, "Quantidade de linhas inseridas : " + result);
            }
        } catch (Exception e) {
            Log.e(TABLE_NAME, e.getMessage());
        }
    }

    public List<Ingredient> getIngredients(int recipeId) {
        List<Ingredient> ingredients = null;

        // Obtem os ingredientes a partir do id da receita
        Cursor mCursor = contentResolver.query(IngredientEntry.CONTENT_URI,
                new String[]{IngredientEntry.COLUMN_ID_RECIPE, IngredientEntry.COLUMN_QUANTITY, IngredientEntry.COLUMN_INGREDIENT_NAME},
                IngredientEntry.COLUMN_ID_RECIPE + " = ?",
                new String[]{String.valueOf(recipeId)},
                null);

        if (mCursor == null || mCursor.getCount() == 0) {
            return ingredients;
        }
        ingredients = new ArrayList<Ingredient>();

       int sizeList = mCursor.getCount();
        for (int i= 0; i < sizeList; i++) {
            Ingredient ingredient = new Ingredient();

            mCursor.moveToPosition(i);
            int recipeIndex = mCursor.getColumnIndex(IngredientEntry.COLUMN_ID_RECIPE);
            int quantityIndex = mCursor.getColumnIndex(IngredientEntry.COLUMN_QUANTITY);
            int NameIngredientIndex = mCursor.getColumnIndex(IngredientEntry.COLUMN_INGREDIENT_NAME);

            Double quantity = mCursor.getDouble(quantityIndex);
            String nameIgredient = mCursor.getString(NameIngredientIndex);

            ingredient.setQuantity(quantity);
            ingredient.setIngredient(nameIgredient);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

}
