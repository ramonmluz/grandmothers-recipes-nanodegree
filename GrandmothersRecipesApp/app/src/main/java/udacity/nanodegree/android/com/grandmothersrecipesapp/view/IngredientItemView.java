package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 13/06/18.
 */

@EViewGroup(R.layout.view_ingredient_item)
public class IngredientItemView extends FrameLayout {

    @ViewById
    View cardView;

    @ViewById
    TextView description;

    public IngredientItemView(@NonNull Context context) {
        super(context);
    }

    public IngredientItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IngredientItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Ingredient item) {
        if (item != null) {
            description.setText(item.getIngredient());
        }
    }
}
