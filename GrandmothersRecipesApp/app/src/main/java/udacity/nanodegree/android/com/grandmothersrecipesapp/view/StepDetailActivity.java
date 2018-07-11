package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
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
public class StepDetailActivity extends AppCompatActivity {

    @Extra
    List<Step> steps;

    @ViewById
    ViewPager viewPager;

    @ViewById
    PagerSlidingTabStrip tabs;

    @ViewById
    Toolbar toolbarStepDetail;

    @Extra
    int position;

    @Extra
    boolean isCellphone;

    private PagerAdapter pagerAdapter;

    @AfterViews
    void init() {
        if (isCellphone) {
            setupAcionBar();
        }

        pagerAdapter = new StepDatailPageAdapter(getSupportFragmentManager(), steps);
        viewPager.setAdapter(pagerAdapter);
        // Informa a posição do item da lista a ViewPager para ser apresentado selecionado juntamente com a tab
        viewPager.setCurrentItem(position);
        tabs.setViewPager(viewPager);
    }

    private void setupAcionBar() {
        setSupportActionBar(toolbarStepDetail);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.recipes_steps);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
