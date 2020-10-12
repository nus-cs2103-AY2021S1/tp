package chopchop.model.recipe;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import chopchop.model.FoodEntry;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.ingredient.IngredientReference;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

public class Recipe extends FoodEntry {

    // Identity fields
    private final List<IngredientReference> ingredients = new ArrayList<>();
    private final List<Step> steps = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, List<IngredientReference> ingredients, List<Step> steps) {
        super(name);
        requireAllNonNull(name, ingredients, steps);
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, List<IngredientReference> ingredients, List<Step> steps, Set<Tag> tags) {
        super(name);
        requireAllNonNull(name, ingredients, steps);
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
        this.tags.addAll(tags);
    }

    /**
     * Returns an immutable ingredient set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<IngredientReference> getIngredients() {
        return Collections.unmodifiableList(ingredients);
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
        return otherRecipe.getName().equals(getName());
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
        getIngredients().forEach(ingredient -> builder.append(ingredient).append(" "));
        builder.append(" Steps:");
        AtomicInteger counter = new AtomicInteger(1);
        getSteps().forEach(step -> {
            builder.append(" ").append(counter.getAndIncrement()).append(". ");
            builder.append(step);
        });
        return builder.toString();
    }

}

