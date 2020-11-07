package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.vendor.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalMenuItems;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different vendor -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_unknownKeyword_noMenuItemFound() {
        Model model = TypicalModel.getModelManagerWithMenu();
        NameContainsKeywordsPredicate predicate = preparePredicate("triangle square circle");
        FindCommand command = new FindCommand(predicate);
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.updateFilteredMenuItemList(predicate);
        String expectedMessage = "0 food listed!\n" + Messages.MESSAGE_CHAIN;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredMenuItemList(), Collections.EMPTY_LIST);
    }

    @Test
    public void execute_multipleKeywords_multipleMenuItemFound() {
        Model model = TypicalModel.getModelManagerWithMenu();
        String expectedMessage = "2 food listed!\n" + Messages.MESSAGE_CHAIN;
        NameContainsKeywordsPredicate predicate = preparePredicate("Milo prata");
        FindCommand command = new FindCommand(predicate);
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.updateFilteredMenuItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalMenuItems.PRATA, TypicalMenuItems.MILO), model.getFilteredMenuItemList());
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new FindCommand(preparePredicate("hey")),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        String trimmedArgs = userInput.trim();
        String finalArgs = trimmedArgs.replaceAll("( )+", " ");
        return new NameContainsKeywordsPredicate(Arrays.asList(finalArgs.split("\\s+")));
    }
}
