package udacity.nanodegree.android.com.grandmothersrecipesapp.model.bo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONArray;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;
import udacity.nanodegree.android.com.grandmothersrecipesapp.util.JsonUtil;

/**
 * Created by ramon on 23/05/18.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RecipeBO {

    private static final String TAG  = RecipeBO.class.getSimpleName();

    @RootContext
    Context context;

    @Background
    public void RequestMovieVolley(final ApiCallBack callBack) {
        Uri uri = Uri.parse(context.getString(R.string.recipes_url)).buildUpon().build();
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, uri.toString(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                                List<Recipe> recipes = JsonUtil.parseJsonList(response.toString());
                                callBack.onSuccess(recipes);
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error.getMessage());
            }
        });

        VolleyRequest.getInstance().addToRequestQueue(request, context);
    }
}
