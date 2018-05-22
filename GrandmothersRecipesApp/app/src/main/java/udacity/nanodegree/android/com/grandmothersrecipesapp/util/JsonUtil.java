package udacity.nanodegree.android.com.grandmothersrecipesapp.util;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

/**
 * Created by ramon on 19/05/18.
 */

public class JsonUtil {
    private static Gson gson;

    public static Gson getGson() {

        if( gson == null){
            gson = new GsonBuilder()
                    .setDateFormat(Resources.getSystem().getString(R.string.date_format))
                    .create();
        }
        return gson;

    }

    public static HashMap<String, Object> parseJson(Object src) {
        String jsonString = JsonUtil.getGson().toJson(src);

        return new Gson().fromJson(jsonString, new TypeToken<HashMap<String,Object>>() {}.getType());
    }

    public static List<Recipe> parseJsonX(Object src) {
        String jsonString = JsonUtil.getGson().toJson(src);
        return new Gson().fromJson(jsonString, new TypeToken <List<Recipe>>() {}.getType());
    }
}
