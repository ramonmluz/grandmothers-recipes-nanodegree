package udacity.nanodegree.android.com.grandmothersrecipesapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.nanodegree.android.com.grandmothersrecipesapp.view.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NavigationActivitiesTest {
    // Activity a ser lançada
    @Rule
    public ActivityTestRule<MainActivity_> mActivityTestRule = new ActivityTestRule<>(MainActivity_.class);

    @Test
    public void clickReciclerViewItem_openStepDetailFragment() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.nestedScrollView)).check(matches(isDisplayed()));

        onView(withId(R.id.stepRecyclerView))
                .perform(CustomScrollActions.nestedScrollTo(), click());

        onView(withId(R.id.viewPager)).check(matches(isDisplayed()));
    }
}
