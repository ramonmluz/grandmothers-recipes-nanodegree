package udacity.nanodegree.android.com.grandmothersrecipesapp.provider;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.service.IngredientIntentService;
import udacity.nanodegree.android.com.grandmothersrecipesapp.service.IngredientsRemoteViewsService;
import udacity.nanodegree.android.com.grandmothersrecipesapp.util.Constants;
import udacity.nanodegree.android.com.grandmothersrecipesapp.view.MainActivity_;

public class GrandmothesWidgetProvider extends AppWidgetProvider{

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int recipeId,
                                int appWidgetId) {
        RemoteViews views;

        if (recipeId  == 0) {
            views = getSampleRemoteViews(context);
        } else {
            views = getListRemoteViews(context, recipeId);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @NonNull
    private static RemoteViews getListRemoteViews(Context context, int recipeId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.list_widget_ingredient);

        // Set the GridWidgetService intent to act as the adapter for the GridViewo
        Intent intent = new Intent(context, IngredientsRemoteViewsService.class);
        intent.putExtra(Constants.EXTRA_RECIPE_ID, recipeId);
        views.setRemoteAdapter(R.id.ingredientWidgetList, intent);

        return views;
    }

    @NonNull
    private static RemoteViews getSampleRemoteViews(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.grandmothes_widget_provider);

        // Criando a itent para chamar a classe principal
        Intent intent = new Intent(context, MainActivity_.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.appWidgetImage, pendingIntent);
        return views;
    }

    public static void updateWidgetList(Context context, AppWidgetManager appWidgetManager, int recipeId, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipeId, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        IngredientIntentService.startActiontWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

