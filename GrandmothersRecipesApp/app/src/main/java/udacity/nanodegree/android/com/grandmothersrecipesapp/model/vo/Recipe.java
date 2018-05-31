package udacity.nanodegree.android.com.grandmothersrecipesapp.model.vo;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by ramon on 19/05/18.
 */

@Parcel
public class Recipe {
    protected int id;
    protected int servings;
    protected String name;
    protected String image;
    protected List<Ingredient> ingredients;
    protected List<Step> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
