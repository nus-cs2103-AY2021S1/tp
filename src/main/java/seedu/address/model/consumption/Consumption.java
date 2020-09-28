package seedu.address.model.consumption;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.recipe.Recipe;

public class Consumption {
    // Identity fields
    private final Recipe recipe;

    /**
     * Every field must be present and not null.
     */
    public Consumption(Recipe recipe) {
        requireAllNonNull(recipe);
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consumption)) {
            return false;
        }

        Consumption otherConsumption = (Consumption) other;
        return otherConsumption.getRecipe().equals(getRecipe());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(recipe);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(recipe.getName())
                .append(" Ingredient: ")
                .append(recipe.getIngredientString());
        return builder.toString();
    }
}
