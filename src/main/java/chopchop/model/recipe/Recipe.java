package chopchop.model.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import chopchop.model.attributes.Name;
import chopchop.model.attributes.Step;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.FoodEntry;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

public class Recipe extends FoodEntry {

    // Data fields
    private final Set<Ingredient> ingredients = new HashSet<>();
    private final List<Step> steps = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, Set<Ingredient> ingredients, List<Step> steps) {
        super(name);
        requireAllNonNull(name, ingredients, steps);
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
    }

    /**
     * Returns an immutable ingredient set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Ingredient> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    /**
     * Returns an immutable Step List, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Step> getSteps() {
        return Collections.unmodifiableList(steps);
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
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getSteps().equals(getSteps());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, ingredients, steps);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Ingredients: ");
        getIngredients().forEach((Ingredient ingredient) -> {
            builder.append(ingredient.getName());
            builder.append("(" + ingredient.getQuantity() + ") ");
        });
        builder.append(" Steps:");
        AtomicInteger counter = new AtomicInteger(1);
        getSteps().forEach((Step step) -> {
            builder.append(" " + counter + ". ");
            builder.append(step.toString());
            counter.getAndIncrement();
        });
        return builder.toString();
    }

}

