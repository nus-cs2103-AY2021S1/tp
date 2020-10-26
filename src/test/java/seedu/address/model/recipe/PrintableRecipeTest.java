package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.testutil.TypicalIngredients;
import seedu.address.testutil.TypicalItems;

public class PrintableRecipeTest {

    /**
     * Tests both the constructor and getPrintableIngredients method since the method only retrieves the attribute.
     */
    @Test
    public void getPrintableIngredientsTest() {
        PrintableRecipe printableRecipe = new PrintableRecipe(1, TypicalIngredients.getTypicalIngredientList(),
                1, "pr", new ProductQuantity("1"), "pr",
                FXCollections.observableList(TypicalItems.getTypicalItems()));

        assertNotNull(printableRecipe.getPrintableIngredients());
    }
}
