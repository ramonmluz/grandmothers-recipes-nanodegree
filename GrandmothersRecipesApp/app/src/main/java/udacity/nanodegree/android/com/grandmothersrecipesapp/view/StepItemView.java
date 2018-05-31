package udacity.nanodegree.android.com.grandmothersrecipesapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import udacity.nanodegree.android.com.grandmothersrecipesapp.R;
import udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo.Step;

/**
 * Created by ramon on 31/05/18.
 */

@EViewGroup(R.layout.view_step_item)
public class StepItemView extends FrameLayout {

    @ViewById
    View stapCardView;

    @ViewById
    TextView stepDescription;

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

    public void bind(Step step) {
        if (step != null) {
            stepDescription.setText(step.getShortDescription());
            stepDescription.setTag(step);
        }
    }


    @Click(R.id.stapCardView)
    void showStepVideosOrImages() {
        // TODO Chamar o fragment dos vídeos
    }

}
