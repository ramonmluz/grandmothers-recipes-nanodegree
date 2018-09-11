package udacity.nanodegree.android.com.grandmothersrecipesapp.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.RecipeItemView;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.RecipeItemView_;

/**
 * Created by ramon on 23/05/18.
 */

@EBean
public class RecipeAdapter extends RecyclerViewAdapterBase<Recipe, RecipeItemView> {

    @RootContext
    Context context;

    @Override
    protected RecipeItemView onCreateItemView(ViewGroup parent, int viewType) {
        return RecipeItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<RecipeItemView> holder, int position) {
          RecipeItemView view = holder.getView();
          Recipe recipe = items.get(position);
          view.bind(recipe);
    }

    @Override
    public void setItems(List<Recipe> items) {
        super.setItems(items);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
