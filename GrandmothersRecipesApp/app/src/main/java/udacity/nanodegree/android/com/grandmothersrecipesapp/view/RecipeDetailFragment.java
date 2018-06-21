package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.adapter.StepAdapter;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

/**
 * Created by ramon on 31/05/18.
 */

@EFragment(R.layout.fragment_recipe_detail)
public class RecipeDetailFragment extends Fragment {

    ApiCallback apiCallback;

    @ViewById
    AppBarLayout appBar;

    @ViewById
    TextView titleToolbar;

    @ViewById
    NestedScrollView nestedScrollView;

    @FragmentArg
    Recipe recipe;

    @ViewById
    RecyclerView stepRecyclerView;

    @ViewById
    TextView ingredientsText;

    @Bean
    StepAdapter stepAdapter;

    @AfterViews
    void init() {
        nestedScrollView.setNestedScrollingEnabled(false);
        ingredientsText.setText("Ingredients");
        ingredientsText.setTag(recipe);
        titleToolbar.setText(R.string.recipes_detail);


        initRecyclerView();
        if (recipe != null && recipe.getSteps() != null && recipe.getSteps().size() > 0) {
            showStepList();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            apiCallback = (ApiCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnClickListener");
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        stepRecyclerView.setLayoutManager(mLayoutManager);
        stepRecyclerView.setAdapter(stepAdapter);

        stepAdapter.bindApiCallBack(new ApiCallback() {
            @Override
            public void onItemClicView(Object[] itemArray) {
                apiCallback.onItemClicView(itemArray);
            }
        });
    }

    private void showStepList() {
        stepAdapter.setItems(recipe.getSteps());
        stepAdapter.notifyDataSetChanged();
    }

    @Click(R.id.ingredientCardView)
    void showIngredientsList() {
        Object[] itemArray = {recipe.getIngredients()};
        apiCallback.onItemClicView(itemArray);
    }

    @Click
    void goBack(){
        getActivity().finish();
    }


}
