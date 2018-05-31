package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

/**
 * Created by ramon on 31/05/18.
 */

@EActivity(R.layout.activity_recipe_detail)
public class RecipeDetailActivity extends AppCompatActivity {
    @Extra
    boolean isDetailFragment;

    @Extra
    Recipe recipe;

    private FragmentManager fragmentManager;

    @AfterViews
    void init() {
        fragmentManager = getSupportFragmentManager();

        if (isDetailFragment) {
            callRecipeDetailFragment();
        }
    }

    private void callRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment_.builder().recipe(recipe).build();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, recipeDetailFragment);
        fragmentTransaction.commit();
    }
}
