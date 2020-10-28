package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DRINKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.IngredientBook;
import seedu.address.model.InputContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SalesBook;
import seedu.address.model.UserPrefs;


public class SalesFindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());

    @Test
    public void equals() {
        InputContainsKeywordsPredicate firstPredicate =
                new InputContainsKeywordsPredicate(Collections.singletonList("BSBM"));
        InputContainsKeywordsPredicate secondPredicate =
                new InputContainsKeywordsPredicate(Collections.singletonList("BSBBT"));

        SalesFindCommand findFirstCommand = new SalesFindCommand(firstPredicate);
        SalesFindCommand findSecondCommand = new SalesFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different drink keywords -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDrinkFound() {
        String expectedMessage = String.format(MESSAGE_DRINKS_LISTED_OVERVIEW, 0);
        InputContainsKeywordsPredicate predicate = preparePredicate(" ");
        SalesFindCommand command = new SalesFindCommand(predicate);
        expectedModel.updateFilteredSalesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSalesRecordList());
    }



    /**
     * Parses {@code userInput} into a {@code InputContainsKeywordsPredicate}.
     */
    private InputContainsKeywordsPredicate preparePredicate(String userInput) {
        return new InputContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
