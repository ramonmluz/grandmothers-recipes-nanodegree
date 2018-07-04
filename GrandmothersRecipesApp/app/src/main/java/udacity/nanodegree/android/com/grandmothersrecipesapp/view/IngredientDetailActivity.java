package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;

/**
 * Created by ramon on 12/06/18.
 */

@EActivity(R.layout.activity_ingredient_detail)
public class IngredientDetailActivity extends AppCompatActivity {

    @Extra
    List<Ingredient> ingredients;

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
    }

    private void callIngredientDetailFragment() {
        IngredientDetailFragment ingredientDetailFragment = IngredientDetailFragment_.builder().ingredients(ingredients).build();
        fragmentTransaction.add(R.id.ingredientFragmentContainer, ingredientDetailFragment);
        fragmentTransaction.commit();
    }

}
