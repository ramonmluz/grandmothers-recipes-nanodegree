package udacity.nanodegree.android.com.grandmothersrecipesapp.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.provider.IngredientContract.IngredientEntry;
import udacity.nanodegree.android.com.grandmothersrecipesapp.util.Constants;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.IngredientDetailActivity;

public class IngredientsRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsRemoteViewsFactory(this.getApplication(), intent);
    }

    /**
     * Classe funciona como Adapter.
     */
    class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context mContext;
        private Cursor mCursor;
        private int recipeId;
        private boolean isNewInstance;

        public IngredientsRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            recipeId = intent.getIntExtra(Constants.EXTRA_RECIPE_ID, 0);
            isNewInstance = true;
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            if(!isNewInstance){
                //Obtem as preferências armazenadas do.
                SharedPreferences prefs = IngredientDetailActivity.ingredientPreference.getSharedPreferences();
                // Obtem a clissificação selecionada
                recipeId = prefs.getInt("recipeId",0);
            }
            isNewInstance = false;

            // Obtem os ingredientes a partir do id da receita
            mCursor = getContentResolver().query(IngredientEntry.CONTENT_URI,
                    new String[]{IngredientEntry.COLUMN_ID_RECIPE, IngredientEntry.COLUMN_INGREDIENT_NAME},
                    IngredientEntry.COLUMN_ID_RECIPE + " = ?",
                    new String[]{String.valueOf(recipeId)},
                    null);
        }



        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (mCursor == null) {
                return 0;
            }
            return mCursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            if (mCursor == null || mCursor.getCount() == 0) {
                return null;
            }
            mCursor.moveToPosition(i);
            int idIndexNameIngredient = mCursor.getColumnIndex(IngredientEntry.COLUMN_INGREDIENT_NAME);
            int idIndexRecipe = mCursor.getColumnIndex(IngredientEntry.COLUMN_ID_RECIPE);

            String nameIgredient = mCursor.getString(idIndexNameIngredient);
            int idRecipe = mCursor.getInt(idIndexRecipe);

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.list_widget_ingredient_item);
            remoteViews.setTextViewText(R.id.widgetIngredientItemTxt, nameIgredient);

            Bundle extras = new Bundle();
            extras.putInt(Constants.EXTRA_RECIPE_ID, idRecipe);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            remoteViews.setOnClickFillInIntent(R.id.widgetIngredientItemTxt, fillInIntent);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

    }
}
