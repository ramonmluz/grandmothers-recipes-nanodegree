package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.adapter.IngradientAdapter;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Ingredient;

/**
 * Created by ramon on 12/06/18.
 */

@EFragment(R.layout.fragment_ingrdient_detail)
public class IngredientDetailFragment extends Fragment {

   @ViewById
   RecyclerView ingredientRecyclerView;

   @FragmentArg
   List<Ingredient> ingredients;

   @Bean
   IngradientAdapter ingradientAdapter;

   @AfterViews
   void init() {
      initRecyclerView();
      if (ingredients != null && ingredients.size() > 0) {
         showStepList();
      }
   }

   private void initRecyclerView() {
      LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
      ingredientRecyclerView.setLayoutManager(mLayoutManager);
      ingredientRecyclerView.setAdapter(ingradientAdapter);
   }

   private void showStepList() {
      ingradientAdapter.setItems(ingredients);
      ingradientAdapter.notifyDataSetChanged();
   }

}
