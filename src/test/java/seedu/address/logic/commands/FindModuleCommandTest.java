package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindModuleCommandTest {
    private Model model = new ModelManager(getTypicalModuleList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModuleList(), new UserPrefs());

    @Test
    public void equals() {
        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModuleCommand findFirstCommand = new FindModuleCommand(firstPredicate);
        FindModuleCommand findSecondCommand = new FindModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindModuleCommand findFirstCommandCopy = new FindModuleCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        ModuleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 3);
        ModuleContainsKeywordsPredicate predicate = preparePredicate("CS2100 CS2103T CS2040");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100, CS2103T, CS2040), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ModuleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModuleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
