package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.adapter.StepDatailPageAdapter;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
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

    @ViewById
    Toolbar toolbarRecipeDetail;

    @ViewById
    View containerRecipeDatailTablet;

    @ViewById
    View containerSecondColumn;

    @ViewById
    View secondColumnStepDetail;

    @ViewById
    View ingredientFragmentContainer;

    @ViewById
    ViewPager viewPager;

    @ViewById
    PagerSlidingTabStrip tabs;


    @InstanceState
    List<Step> steps;

    @InstanceState
    List<Ingredient> ingredients;

    @InstanceState
    boolean isChangePosition;

    @InstanceState
    boolean isStepDetailView;

    @InstanceState
    int positionClickedStep;


    private PagerAdapter pagerAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @AfterViews
    void init() {
        if (containerRecipeDatailTablet == null) {
            setSupportActionBar(toolbarRecipeDetail);
            getSupportActionBar().setTitle(R.string.recipes_detail);
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (isDetailFragment) {
            callRecipeDetailFragment();
        }
    }

    private void callRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment_.builder().recipe(recipe).build();

        if (!isChangePosition) {
            fragmentTransaction.add(R.id.fragmentContainer, recipeDetailFragment);
            fragmentTransaction.commit();
            isChangePosition = true;
        } else {
            // Posição foi modificada
            fragmentTransaction.replace(R.id.fragmentContainer, recipeDetailFragment);

            // Varifica se os passos ou ingredientes foram clicados no modo tablet, e se a view a ser
            // apresentada é e de ingredients pu passos (isStepDetailView)
            if (containerRecipeDatailTablet != null) {
                if (steps != null && steps.size() > 0 && isStepDetailView) {
                    onItemClickStepView(steps, positionClickedStep);
                } else if (ingredients != null && ingredients.size() > 0 && !isStepDetailView){
                    onItemClickIngrendientView(ingredients);
                }
            }
        }

    }

    @Override
    public void onItemClickStepView(List<Step> steps, int position) {
        // Seta o parâmetro passado para iniciar a chamada
        isStepDetailView = true;
        this.steps = steps;
        this.positionClickedStep = position;

        boolean isCellphone = checkIsCellphone();

        initStepDetail(isCellphone);

    }

    private boolean checkIsCellphone() {
        boolean isCellphone = true;
        if (containerRecipeDatailTablet != null) {
            isCellphone = false;
        }
        return isCellphone;
    }

    @Override
    public void onItemClickIngrendientView(List<Ingredient> ingredients) {
        isStepDetailView = false;
        this.ingredients = ingredients;
        boolean isCellphone = checkIsCellphone();
        initIngredientsDetail(isCellphone);
    }


    /**
     * Incia a chamada da activity ou configuração para tablet do Step Detail
     *
     * @param isCellphone
     */
    private void initStepDetail(boolean isCellphone) {

        if (isCellphone) {
            // Chamar Activty de Steps
            StepDetailActivity_.intent(this)
                    .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .position(positionClickedStep)
                    .steps(steps)
                    .isCellphone(true)
                    .start();
        } else {

            //  Apresenta os  Steps para tablet
            setTabletsColumnsVisibility(View.VISIBLE, View.GONE, View.VISIBLE);
            pagerAdapter = new StepDatailPageAdapter(getSupportFragmentManager(), steps);
            viewPager.setAdapter(pagerAdapter);
            // Informa a posição do item da lista a ViewPager para ser apresentado selecionado juntamente com a tab
            viewPager.setCurrentItem(positionClickedStep);
            tabs.setViewPager(viewPager);
        }
    }

    /**
     * Incia a chamada da activity ou configuração para tablet doa Ingredient Detail
     *
     * @param isCellphone
     */
    private void initIngredientsDetail(boolean isCellphone) {

        if (isCellphone) { // Dipositivo é um tablet
            // Chama a activity de ingredientes
            IngredientDetailActivity_.intent(this)
                    .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .ingredients(ingredients)
                    .isCellphone(isCellphone)
                    .start();
        } else {
            setTabletsColumnsVisibility(View.VISIBLE, View.VISIBLE, View.GONE);
            callIngredientDetailFragment(ingredients, isCellphone);
        }
    }

    private void setTabletsColumnsVisibility(
            int containerSecondColumnVisivlility,
            int ingredientVisibility,
            int secondColumnVisibility) {

        containerSecondColumn.setVisibility(containerSecondColumnVisivlility);
        ingredientFragmentContainer.setVisibility(ingredientVisibility);
        secondColumnStepDetail.setVisibility(secondColumnVisibility);
    }

    /**
     * Apresenta o fragment de Ingredients para tablet
     *
     * @param ingredients
     * @param isCellphone
     */
    private void callIngredientDetailFragment(List<Ingredient> ingredients, boolean isCellphone) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        IngredientDetailFragment ingredientDetailFragment = IngredientDetailFragment_.builder()
                .ingredients(ingredients)
                .isCellphone(isCellphone)
                .build();

        fragmentTransaction.add(R.id.ingredientFragmentContainer, ingredientDetailFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
