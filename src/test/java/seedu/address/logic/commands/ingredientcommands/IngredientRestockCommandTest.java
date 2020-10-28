package seedu.address.logic.commands.ingredientcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

public class IngredientRestockCommandTest {

    @Test
    public void execute_viewIngredientsInShortage_success() {
        Model model = new ModelManager();
        Model expectedModel = model;
        Amount amount = new Amount ("4");

        IngredientBook defaultBook = new IngredientBook();
        defaultBook.addIngredient(new Ingredient(new IngredientName("Milk"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Pearl"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Boba"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Black Tea"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Green Tea"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Brown Sugar"), amount));
        ReadOnlyIngredientBook readOnlyIngredientBook = defaultBook;

        model.setIngredientBook(readOnlyIngredientBook);

        IngredientBook original = new IngredientBook();
        original.addIngredient(new Ingredient(new IngredientName("Milk"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Pearl"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Boba"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Black Tea"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Green Tea"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Brown Sugar"), amount));
        ReadOnlyIngredientBook defaultReadOnlyIngredientBook = original;

        expectedModel.setIngredientBook(defaultReadOnlyIngredientBook);

        final IngredientRestockCommand standardCommand = new
                IngredientRestockCommand();
        String ingredientList = "";
        final char lineSeparator = '\n';
        List<Ingredient> lastShownList = expectedModel.getFilteredIngredientList();
        for (Ingredient i : lastShownList) {
            ingredientList += i.toString() + lineSeparator;
        }
        assertCommandSuccess(standardCommand, model,
                IngredientRestockCommand.MESSAGE_SUCCESS + ingredientList, expectedModel);

    }
}
