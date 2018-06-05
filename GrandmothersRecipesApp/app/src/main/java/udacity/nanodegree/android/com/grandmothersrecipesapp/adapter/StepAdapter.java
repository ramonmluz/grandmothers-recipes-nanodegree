package udacity.nanodegree.android.com.grandmothersrecipesapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.bo.ApiCallBack;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.ApiCallback;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.RecipeItemView;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepItemView;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.StepItemView_;

/**
 * Created by ramon on 31/05/18.
 */
@EBean
public class StepAdapter extends RecyclerViewAdapterBase<Step,StepItemView> {

    @RootContext
    Context context;

    ApiCallback apiCallback;

    @Override
    protected StepItemView onCreateItemView(ViewGroup parent, int viewType) {
        return StepItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<StepItemView> holder, int position) {
        StepItemView view = holder.getView();
        Step step = items.get(position);
        view.bind(items, position, new ApiCallback() {
            @Override
            public void onItemClicView(Object[] itemArray) {
                apiCallback.onItemClicView(itemArray);
            }
        });
    }

    @Override
    public void setItems(List<Step> items) {
        super.setItems(items);
    }

    public void bindApiCallBack(ApiCallback apiCallBack){
        this.apiCallback = apiCallBack;
    }

}
