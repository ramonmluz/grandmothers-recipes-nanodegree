package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.adapter.StepDatailPageAdapter;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 05/06/18.
 */

@EActivity(R.layout.activity_step_detail)
public class StepDetailActivity extends FragmentActivity{

    @ViewById
    ViewPager viewPager;

    @ViewById
    PagerSlidingTabStrip tabs;

    @Extra
    List<Step> steps;

    @Extra
    int position; // TODO pssar position para o adapter

    private PagerAdapter pagerAdapter;

    @AfterViews
    void init() {
        pagerAdapter = new StepDatailPageAdapter(getSupportFragmentManager(), steps);
        viewPager.setAdapter(pagerAdapter);
        tabs.setViewPager(viewPager);
    }

}
