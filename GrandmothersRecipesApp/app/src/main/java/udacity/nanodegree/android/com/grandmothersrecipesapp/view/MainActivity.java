package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.adapter.RecipeAdapter;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.bo.ApiCallBack;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.bo.RecipeBO;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Bean
    RecipeBO recipeBO;

    @Bean
    RecipeAdapter recipeAdapter;

    @ViewById
    RecyclerView recyclerView;
    private  List<Recipe> recipes;

    @AfterViews
     void init(){
        updateRecipes();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recipeAdapter);
    }

    private void updateRecipes() {
        recipeBO.RequestMovieVolley(new ApiCallBack() {
            @Override
            public void onSuccess(Object response) {
                showList((List<Recipe>) response);
            }

            @Override
            public void onError(String error) {
                showError(error);
            }
        });
    }

    @Override
    protected void onResume() {
        updateRecipes();
        super.onResume();
    }

    @UiThread
    void showList(List<Recipe> recipes){
        this.recipes = recipes;
        recipeAdapter.setItems(recipes);
        recipeAdapter.notifyDataSetChanged();
    }

    @UiThread
    void showError(String error){

    }

}
