package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
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

    @ViewById
    View progress;

    @ViewById
    View areaErro;

    @ViewById
    TextView textMsgErroView;

    private  List<Recipe> recipes;



    @AfterViews
     void init(){
        textMsgErroView.setText(getString(R.string.erro_listar_receitas));
        updateRecipes();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recipeAdapter);
    }

    private void updateRecipes() {
         showView(progress);
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

    void showView(View view){
        recyclerView.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        areaErro.setVisibility(View.GONE);

        if(view != null){
            view.setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    protected void onResume() {
//        updateRecipes();
//        super.onResume();
//    }

    @UiThread
    protected void showList(List<Recipe> recipes){
        showView(recyclerView);
        this.recipes = recipes;
        recipeAdapter.setItems(recipes);
        recipeAdapter.notifyDataSetChanged();
    }

    @UiThread
   protected void showError(String error){
        showView(areaErro);
    }

  @Click(R.id.areaErro)
  protected void reloadRecipes(){
      updateRecipes();
  }
}
