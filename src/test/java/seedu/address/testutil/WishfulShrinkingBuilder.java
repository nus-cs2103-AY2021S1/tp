package seedu.address.testutil;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class to help with building WishfulShrinking objects.
 * Example usage: <br>
 *     {@code WishfulShrinking ab = new WishfulShrinkingBuilder().withRecipe("John", "Doe").build();}
 */
public class WishfulShrinkingBuilder {

    private WishfulShrinking wishfulShrinking;

    public WishfulShrinkingBuilder() {
        wishfulShrinking = new WishfulShrinking();
    }

    public WishfulShrinkingBuilder(WishfulShrinking wishfulShrinking) {
        this.wishfulShrinking = wishfulShrinking;
    }

    /**
     * Adds a new {@code Recipe} to the {@code WishfulShrinking} that we are building.
     */
    public WishfulShrinkingBuilder withRecipe(Recipe recipe) {
        wishfulShrinking.addRecipe(recipe);
        return this;
    }

    public WishfulShrinking build() {
        return wishfulShrinking;
    }
}
