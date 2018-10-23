package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

/**
 * Created by ramon on 20/05/18.
 */
@EViewGroup(R.layout.view_recipe_item)
public class RecipeItemView extends FrameLayout {

     @ViewById
    protected CardView cardViewContainer;

    @ViewById
    protected TextView nameRecipeTxt;

    public RecipeItemView(Context context) {
        super(context);
    }

    public RecipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecipeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void init() {

    }

    public void bind(Recipe recipe) {
        if (recipe != null) {
            nameRecipeTxt.setText(recipe.getName());
            nameRecipeTxt.setTag(recipe);
        }
    }

    @Click(R.id.cardViewContainer)
    void showRecipeDetail() {
        RecipeDetailActivity_.intent(getContext())
                .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .recipe((Recipe) nameRecipeTxt.getTag())
                .isDetailFragment(true)
                .start();
    }

}
