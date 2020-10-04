package chopchop.testutil;

import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientBook;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;

public class IngredientBookBuilder {

    private IngredientBook ingredientBook;

    public IngredientBookBuilder() {
        ingredientBook = new IngredientBook();
    }

    public IngredientBookBuilder(IngredientBook ingredientBook) {
        this.ingredientBook = ingredientBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public IngredientBookBuilder withIngredient(Ingredient ind) {
        ingredientBook.addIngredient(ind);
        return this;
    }

    public IngredientBook build() {
        return ingredientBook;
    }
}
