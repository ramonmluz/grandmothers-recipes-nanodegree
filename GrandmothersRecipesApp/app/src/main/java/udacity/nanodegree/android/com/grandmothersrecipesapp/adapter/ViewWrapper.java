package udacity.nanodegree.android.com.grandmothersrecipesapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ramon on 23/05/18.
 */

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
