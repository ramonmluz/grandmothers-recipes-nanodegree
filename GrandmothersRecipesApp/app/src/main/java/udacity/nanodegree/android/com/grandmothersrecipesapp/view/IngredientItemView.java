package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;


@EViewGroup(R.layout.view_ingredient_item)
public class IngredientItemView extends LinearLayout {

    @ViewById
    TextView description;

    public IngredientItemView(Context context) {
        super(context);
    }


    public void bind(Ingredient item) {
        if (item != null) {
            description.setText(item.getConcatQuantityIngradient());
        }
    }
}
