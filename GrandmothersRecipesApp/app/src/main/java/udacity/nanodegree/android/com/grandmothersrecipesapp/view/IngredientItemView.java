package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;


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
