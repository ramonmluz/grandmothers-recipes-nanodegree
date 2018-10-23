package udacity.nanodegree.android.com.grandmothersrecipesapp.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Recipe;

/**
 * Created by ramon on 19/05/18.
 */

public class JsonUtil {
    private static Gson gson;

    public static Gson getGson() {

        if( gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static HashMap<String, Object> parseJson(Object src) {
        String jsonString = getGson().toJson(src);

        return  getGson().fromJson(jsonString, new TypeToken<HashMap<String,Object>>() {}.getType());
    }

    public static List<Recipe> parseJsonList(String jsonString) {
        return getGson().fromJson(jsonString, new TypeToken <List<Recipe>>() {}.getType());
    }
}
