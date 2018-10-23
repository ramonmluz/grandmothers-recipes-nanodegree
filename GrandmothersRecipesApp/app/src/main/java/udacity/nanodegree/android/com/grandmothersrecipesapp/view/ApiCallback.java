package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;


public interface ApiCallback {
     void onItemClickStepView(List<Step> steps, int position);
}
