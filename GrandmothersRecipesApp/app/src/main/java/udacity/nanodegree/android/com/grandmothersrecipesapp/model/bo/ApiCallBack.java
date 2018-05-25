package udacity.nanodegree.android.com.grandmothersrecipesapp.model.bo;

/**
 * Created by ramon on 23/05/18.
 */

public interface ApiCallBack<T> {

    void onSuccess(T response);

    void onError(String error);
}
