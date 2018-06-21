package udacity.nanodegree.android.com.grandmothersrecipesapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepDetailFragment;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepDetailFragment_;

/**
 * Created by ramon on 17/06/18.
 */

public class StepDatailPageAdapter extends FragmentStatePagerAdapter {

    private List<Step> steps;

    public StepDatailPageAdapter(FragmentManager fm,List<Step> steps) {
        super(fm);
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Step step = null;
        if(steps != null && steps.size() > 0) {
            step = steps.get(position);
        }

        StepDetailFragment stepDetailFragment = StepDetailFragment_.builder().step(step).build();

        return stepDetailFragment;
    }

    @Override
    public int getCount() {
        return steps!=null & steps.size()>0? steps.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int stepPosition = position + 1;
        return  "Step " + stepPosition;
    }
}
