package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.provider.IngredientContract.IngredientEntry;
import udacity.nanodegree.android.com.grandmothersrecipesapp.service.IngredientIntentService;

@EActivity(R.layout.activity_ingredient_detail)
public class IngredientDetailActivity extends AppCompatActivity {

    public static final String TABLE_NAME = "ingredient";

    @Pref
    public  static  IngredientPreference_ ingredientPreference;

    @Extra
    @InstanceState
    List<Ingredient> ingredients;


    @Extra
    @InstanceState
    int idRecipe;

    @Extra
    boolean isCellphone;

    @InstanceState
    boolean isChangePosition;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @ViewById
    Toolbar toolbarIngrdient;

    @AfterViews
    void init() {

        setSupportActionBar(toolbarIngrdient);
        getSupportActionBar().setTitle(R.string.recipes_ingredients);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        callIngredientDetailFragment();
        insertIngredientsDontSaved();
    }

    private void insertIngredientsDontSaved() {
        Cursor cursor = getCountIngredientsSaved();
        int count = cursor.getCount();
        if (count == 0) {
            insertIngredients();
        }
        ingredientPreference.recipeId().put(idRecipe);
        IngredientIntentService.startActiontWidgetIngredients(this, idRecipe);
    }

    private Cursor getCountIngredientsSaved() {
        return getContentResolver().query(IngredientEntry.CONTENT_URI, new String[]{IngredientEntry.COLUMN_INGREDIENT_NAME},
                IngredientEntry.COLUMN_ID_RECIPE + " = ?", new String[]{String.valueOf(idRecipe)}, null);

    }


    private void insertIngredients() {
        int sizeList = ingredients.size();
        ContentValues[] contentValuesArray = new ContentValues[sizeList];
        for (int i = 0; i < sizeList; i++) {
            Ingredient ingredient = ingredients.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(IngredientEntry.COLUMN_ID_RECIPE, idRecipe);
            contentValues.put(IngredientEntry.COLUMN_INGREDIENT_NAME, ingredient.getConcatQuantityIngradient());
            contentValuesArray[i] = contentValues;
        }

        try {
            int result = getContentResolver().bulkInsert(IngredientEntry.CONTENT_URI, contentValuesArray);

            if (result != 0) {
                Log.d(TABLE_NAME, "Quantidade de linhas inseridas : " + result);
            }
        } catch (Exception e) {
            Log.e(TABLE_NAME, e.getMessage());
        }
    }


    private void callIngredientDetailFragment() {
        IngredientDetailFragment ingredientDetailFragment = IngredientDetailFragment_.builder()
                .isCellphone(isCellphone)
                .ingredients(ingredients)
                .build();

        if (!isChangePosition) {
            fragmentTransaction.add(R.id.ingredientFragmentContainer, ingredientDetailFragment);
            fragmentTransaction.commit();
            isChangePosition = true;
        } else {
            fragmentTransaction.replace(R.id.ingredientFragmentContainer, ingredientDetailFragment);
        }
    }

}
