package udacity.nanodegree.android.com.grandmothersrecipesapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.ApiCallback;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.IngredientItemView;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.IngredientItemView_;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepItemView;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepItemView_;

/**
 * Created by ramon on 31/05/18.
 */
@EBean
public class IngradientAdapter extends RecyclerViewAdapterBase<Ingredient,IngredientItemView> {

    @RootContext
    Context context;

    ApiCallback apiCallback;

    @Override
    protected IngredientItemView onCreateItemView(ViewGroup parent, int viewType) {
        return IngredientItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<IngredientItemView> holder, int position) {
        IngredientItemView view = holder.getView();
        Ingredient ingredient = items.get(position);
        view.bind(ingredient);
    }

    @Override
    public void setItems(List<Ingredient> items) {
        super.setItems(items);
    }

}
