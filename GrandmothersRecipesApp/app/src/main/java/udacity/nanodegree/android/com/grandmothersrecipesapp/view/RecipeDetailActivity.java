package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 31/05/18.
 */

@EActivity(R.layout.activity_recipe_detail)
public class RecipeDetailActivity extends AppCompatActivity implements ApiCallback {

    @Extra
    boolean isDetailFragment;

    @Extra
    Recipe recipe;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @AfterViews
    void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (isDetailFragment) {
            callRecipeDetailFragment();
        }
    }

    private void callRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment_.builder().recipe(recipe).build();
        fragmentTransaction.add(R.id.fragmentContainer, recipeDetailFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClicView(Object[] itemArray) {
        if(itemArray.length > 1){ // Chamar Activty de Steps
            List<Step> steps = (List<Step>)itemArray[0];
            int position = (int) itemArray[1];

            StepDetailActivity_.intent(this)
                    .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .position(position)
                    .steps(steps)
                    .start();
        }
    }
}
