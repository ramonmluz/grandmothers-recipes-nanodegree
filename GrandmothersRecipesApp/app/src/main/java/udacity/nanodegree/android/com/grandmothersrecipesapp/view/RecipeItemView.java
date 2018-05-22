package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.EViewGroup;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;

/**
 * Created by ramon on 20/05/18.
 */
@EViewGroup(R.layout.recipe_item_view)
public class RecipeItemView extends RelativeLayout {

    public RecipeItemView(Context context) {
        super(context);
    }

    public RecipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecipeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
