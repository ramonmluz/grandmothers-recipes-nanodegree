package udacity.nanodegree.android.com.grandmothersrecipesapp.service;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.provider.GrandmothesWidgetProvider;

import static udacity.nanodegree.android.com.grandmothersrecipesapp.util.Constants.EXTRA_RECIPE_ID;

public class IngredientIntentService extends IntentService {

    public IngredientIntentService() {
        super("IngrdientIntentService");
    }

    public static void startActiontWidgetIngredients(Context context, int recipeId) {
        Intent intent = new Intent(context, IngredientIntentService.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        context.startService(intent);
    }

    public static void startActiontWidget(Context context) {
        Intent intent = new Intent(context, IngredientIntentService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
                int recipeId = intent.getIntExtra(EXTRA_RECIPE_ID, 0);
                handleActionWidgetIngredients(recipeId);
        }
    }

    private void handleActionWidgetIngredients(int recipeId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, GrandmothesWidgetProvider.class));
        // Trigger data update to handle the ListView widgets and force a data refresh
          appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ingredientWidgetList);
        //Now update all widgets
        GrandmothesWidgetProvider.updateWidgetList(this, appWidgetManager, recipeId, appWidgetIds);
    }


}
