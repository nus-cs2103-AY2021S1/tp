package chopchop.testutil;

import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Step;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

import java.util.List;
import java.util.Set;

public class RecipeBuilder {

    public static final String DEFAULT_NAME = "Egg";
    public static final Set<Ingredient> DEFAULT_INGREDIENTS = "";
    public static final List<Step> DEFAULT_STEPS = ;

    private Name name;
    private Set<Ingredient> ingredients;
    private List<Step> steps;


    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        ingredients = new HashSet<>(DEFAULT_QTY);
        steps = new ArrayList<>(DEFAULT_EXPIRY);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recToCopy}.
     */
    public RecipeBuilder(Recipe recToCopy) {
        name = recToCopy.getName();
        ingredients = recToCopy.getIngredients();
        steps = recToCopy.getSteps();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public RecipeBuilder with(int qty) {
        this. = new Quantity(qty);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public RecipeBuilder withDate(String date) {
        this.expDate = new ExpiryDate(date);
        return this;
    }

    public Recipe build() {
        return new Ingredient(name, ingredients, steps);
    }

}
