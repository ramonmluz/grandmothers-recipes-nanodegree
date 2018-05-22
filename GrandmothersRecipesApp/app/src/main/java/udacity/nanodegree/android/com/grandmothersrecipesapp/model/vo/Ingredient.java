package udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo;

import org.parceler.Parcel;

/**
 * Created by ramon on 19/05/18.
 */

@Parcel
public class Ingredient {
    protected int quantity;
    protected String measure;
    protected String ingredient;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
