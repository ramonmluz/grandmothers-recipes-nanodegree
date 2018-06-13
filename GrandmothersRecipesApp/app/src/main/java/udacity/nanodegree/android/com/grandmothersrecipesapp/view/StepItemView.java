package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 31/05/18.
 */

@EViewGroup(R.layout.view_step_item)
public class StepItemView extends FrameLayout {

    @ViewById
    View cardView;

    @ViewById
    TextView description;

    ApiCallback apiCallBack;

    public StepItemView(Context context) {
        super(context);
    }

    public StepItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StepItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void init() {
    }

    public void bind(List<Step> items, int position, ApiCallback apiCallBack) {
        this.apiCallBack = apiCallBack;

        if (items != null && items.size() > 0) {
            Step step = items.get(position);
            Object[] stepArray = {items, position};
            description.setText(step.getShortDescription());
            description.setTag(stepArray);
        }
    }

    @Click(R.id.cardView)
    void showStepVideosOrImages() {
          apiCallBack.onItemClicView((Object[]) description.getTag());
    }

}
