package seedu.address.model.recipe;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;
import seedu.address.testutil.TypicalIngredients;
import seedu.address.testutil.TypicalItems;

public class PrintableRecipeTest {

    /**
     * Tests both the constructor and getPrintableIngredients method since the method only retrieves the attribute.
     */
    @Test
    public void getPrintableIngredientsTest() {
        PrintableRecipe printableRecipe = new PrintableRecipe(1, TypicalIngredients.getTypicalIngredientList(),
                1,"pr", new ProductQuantity("1"), "pr", false,
                FXCollections.observableList(TypicalItems.getTypicalItems()));
        System.out.println(printableRecipe.getPrintableIngredients());
    }

}
