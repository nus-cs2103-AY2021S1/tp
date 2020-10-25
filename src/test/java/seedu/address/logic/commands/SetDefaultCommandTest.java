package seedu.address.logic.commands;

/*
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.SalesBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;*/


class SetDefaultCommandTest {

    /*@Test
    public void execute_zeroIngredientBook_success() {
        Model model = new ModelManager();

        Model expectedModel = new ModelManager();

        IngredientBook defaultIngredientBook = new IngredientBook();
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Boba")),
                new Ingredient(new IngredientName("Boba"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Oolong Tea")),
                new Ingredient(new IngredientName("Oolong Tea"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Brown Sugar")),
                new Ingredient(new IngredientName("Brown Sugar"), new Amount("20")));

        ReadOnlyIngredientBook defaultReadOnlyIngredientBook = defaultIngredientBook;
        expectedModel.setIngredientBook(defaultReadOnlyIngredientBook);

        assertCommandSuccess(new SetDefaultCommand(), model, SetDefaultCommand.MESSAGE_SUCCESS, expectedModel);
    }*/

    /*@Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new SalesBook(),
                new IngredientBook(), new UserPrefs());

        model.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), new Amount("100")));
        model.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), new Amount("120")));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new SalesBook(),
                new IngredientBook(), new UserPrefs());

        IngredientBook defaultIngredientBook = new IngredientBook();
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Boba")),
                new Ingredient(new IngredientName("Boba"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Oolong Tea")),
                new Ingredient(new IngredientName("Oolong Tea"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Brown Sugar")),
                new Ingredient(new IngredientName("Brown Sugar"), new Amount("20")));

        ReadOnlyIngredientBook defaultReadOnlyIngredientBook = defaultIngredientBook;

        expectedModel.setIngredientBook(defaultReadOnlyIngredientBook);

        assertCommandSuccess(new SetDefaultCommand(), model, SetDefaultCommand.MESSAGE_SUCCESS, expectedModel);
    }*/

}
