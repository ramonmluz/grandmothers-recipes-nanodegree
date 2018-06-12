package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 12/06/18.
 */

@EActivity(R.layout.activity_ingredient_detail)
public class IngredientDetailActivity extends AppCompatActivity {

    @Extra
    List<Ingredient> ingredients;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @AfterViews
    void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        callIngredientDetailFragment();
    }

    private void callIngredientDetailFragment() {
//        Step step = null;
//        if(ingredients != null && ingredients.size() > 0) {
//            step = ingredients.get(position);
//        }
//        StepDetailFragment stepDetailFragment = StepDetailFragment_.builder().step(step).steps(ingredients).build();
//        fragmentTransaction.add(R.id.stepFragmentContainer, stepDetailFragment);
//        fragmentTransaction.commit();
    }

}
