package udacity.nanodegree.android.com.grandmothersrecipesapp.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class IngredientContract {

    public static final String AUTHORITY = "udacity.nanodegree.android.com.grandmothersrecipesapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_INGREDIENT = "ingredient";

    public static final class IngredientEntry implements BaseColumns {
        public static final Uri CONTENT_URI =  BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_INGREDIENT).build();

        // Nomes das colunas
        public static final String COLUMN_ID_RECIPE = "ingredientRecipe";
        public static final String  TABLE_NAME = "ingredient";
        public static final String  COLUMN_INGREDIENT_NAME = "ingredientName";
    }
}
