package seedu.address.model.recipe;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents a Recipe in the Wishful Shrinking.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Identity fields
    private final Name name;
    // Data fields
    private final ArrayList<Ingredient> ingredients;

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, ArrayList<Ingredient> ingredients) {
        requireAllNonNull(name, ingredients);
        this.name = name;
        this.ingredients = ingredients;
    }

    public Name getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredients;
    }

    /**
     * Returns true if both recipes of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName())
                && (otherRecipe.getIngredient().equals(getIngredient()));
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

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getIngredient().equals(getIngredient());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, ingredients);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Ingredient: ")
                .append(ingredients.stream()
                        .map(item -> item.value)
                        .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a));
        return builder.toString();
    }

}
