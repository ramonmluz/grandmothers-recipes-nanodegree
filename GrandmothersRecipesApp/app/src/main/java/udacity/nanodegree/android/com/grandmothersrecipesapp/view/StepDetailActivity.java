package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentArg;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 05/06/18.
 */

@EActivity(R.layout.activity_step_detail)
public class StepDetailActivity extends AppCompatActivity{

    @Extra
    List<Step> steps;

    @Extra
    int position;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @AfterViews
    void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        callStepDetailFragment();
    }

    private void callStepDetailFragment() {
        Step step = null;
        if(steps != null && steps.size() > 0) {
            step = steps.get(position);
        }
        StepDetailFragment stepDetailFragment = StepDetailFragment_.builder().step(step).steps(steps).build();
        fragmentTransaction.replace(R.id.stepFragmentContainer, stepDetailFragment);
        fragmentTransaction.commit();
    }

}
