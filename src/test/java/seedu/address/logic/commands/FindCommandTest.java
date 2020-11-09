package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULES_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.address.testutil.TypicalModules.SWE;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());
    private Model expectedModel = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());

    @Test
    public void equals() {
        ModuleNameContainsKeywordsPredicate firstPredicate =
                new ModuleNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleNameContainsKeywordsPredicate secondPredicate =
                new ModuleNameContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different module -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_NO_MODULES_FOUND, 0);
        ModuleNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_singleKeyword_singleModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 1);
        ModuleNameContainsKeywordsPredicate predicate = preparePredicate(VALID_MOD_NAME_A);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(SWE), model.getFilteredModuleList());
    }

    @Test
    public void execute_find_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                false, false, false, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ModuleNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModuleNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
