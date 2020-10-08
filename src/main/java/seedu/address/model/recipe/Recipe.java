package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.commons.Calories;


/**
 * Represents a Recipe in the Wishful Shrinking.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Identity fields
    private final Name name;
    private final String instruction;
    private final String recipeImage;
    // Data fields
    private final ArrayList<Ingredient> ingredients;
    private final Calories calories;

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, String instruction, String recipeImage,
                  ArrayList<Ingredient> ingredients, Calories calories) {
        requireAllNonNull(name, ingredients, calories);
        this.name = name;
        this.instruction = instruction;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
        this.calories = calories;
    }

    public Name getName() {
        return name;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredients;
    }

    public Calories getCalories() {
        return calories;
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
                && otherRecipe.getInstruction().equals(getInstruction())
                && otherRecipe.getRecipeImage().equals(getRecipeImage())
                && (otherRecipe.getIngredient().equals(getIngredient()))
                && otherRecipe.getCalories().equals(getCalories());
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
                && otherRecipe.getInstruction().equals(getInstruction())
                && otherRecipe.getRecipeImage().equals(getRecipeImage())
                && otherRecipe.getIngredient().equals(getIngredient())
                && otherRecipe.getCalories().equals(getCalories());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, instruction, recipeImage, ingredients, calories);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Ingredient: ")
                .append(ingredients.stream()
                        .map(item -> item.getValue())
                        .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a))
                .append(" Calories: ")
                .append(getCalories() + " cal")
                .append(" Instructions: ")
                .append(getInstruction());
        return builder.toString();
    }

}
