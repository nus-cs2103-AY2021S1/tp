package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS3216;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.CompoundFindPredicate;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeContainsKeywordsPredicate firstPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleCodeContainsKeywordsPredicate secondPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("second"));

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
        String expectedMessage = String.format(MESSAGE_MODULES_FOUND_OVERVIEW, 0);
        CompoundFindPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleModuleCodes_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_FOUND_OVERVIEW, 2);
        CompoundFindPredicate predicate = preparePredicate("CS2103T CS3216");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T, CS3216), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneTagKeyword_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_FOUND_OVERVIEW, 1);
        CompoundFindPredicate predicate = preparePredicate(VALID_TAG_NON_CORE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS3216), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneCapitalizedTagKeyword_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_FOUND_OVERVIEW, 1);
        CompoundFindPredicate predicate = preparePredicate(VALID_TAG_NON_CORE.toUpperCase());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS3216), model.getFilteredModuleList());
    }

    @Test
    public void execute_tagAndModuleCodeKeywords_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_FOUND_OVERVIEW, 2);
        CompoundFindPredicate predicate = preparePredicate(VALID_TAG_CORE + " CS3216");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T, CS3216), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodeContainsKeywordsPredicate}.
     */
    private CompoundFindPredicate preparePredicate(String userInput) {
        return new CompoundFindPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
