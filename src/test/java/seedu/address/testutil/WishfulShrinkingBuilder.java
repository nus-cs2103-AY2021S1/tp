package seedu.address.testutil;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code WishfulShrinking ab = new WishfulShrinkingBuilder().withRecipe("John", "Doe").build();}
 */
public class WishfulShrinkingBuilder {

    private WishfulShrinking addressBook;

    public WishfulShrinkingBuilder() {
        addressBook = new WishfulShrinking();
    }

    public WishfulShrinkingBuilder(WishfulShrinking addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Recipe} to the {@code WishfulShrinking} that we are building.
     */
    public WishfulShrinkingBuilder withRecipe(Recipe recipe) {
        addressBook.addRecipe(recipe);
        return this;
    }

    public WishfulShrinking build() {
        return addressBook;
    }
}
