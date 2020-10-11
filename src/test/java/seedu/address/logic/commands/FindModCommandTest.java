package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.CodeOrNameMatchesKeywordPredicate;
import seedu.address.testutil.TypicalModules;

class FindModCommandTest {
    private Model model = new ModelManager(TypicalModules.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalModules.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CodeOrNameMatchesKeywordPredicate firstPredicate =
                new CodeOrNameMatchesKeywordPredicate(Collections.singletonList("first"));
        CodeOrNameMatchesKeywordPredicate secondPredicate =
                new CodeOrNameMatchesKeywordPredicate(Collections.singletonList("second"));

        FindModCommand findModFirstCommand = new FindModCommand(firstPredicate);
        FindModCommand findModSecondCommand = new FindModCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findModFirstCommand.equals(findModFirstCommand));

        // same values -> returns true
        FindModCommand findFirstCommandCopy = new FindModCommand(firstPredicate);
        assertTrue(findModFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findModFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findModFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findModFirstCommand.equals(findModSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED, 0);
        CodeOrNameMatchesKeywordPredicate predicate = preparePredicate(" ");
        FindModCommand command = new FindModCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneKeyword_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED, 3);
        CodeOrNameMatchesKeywordPredicate predicate = preparePredicate("Programming");
        FindModCommand command = new FindModCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS1010S, CS1101S, CS2030), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeyword_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED, 3);
        CodeOrNameMatchesKeywordPredicate predicate = preparePredicate("cs2030 organization software");
        FindModCommand command = new FindModCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2100, CS2103), model.getFilteredModuleList());
    }

    @Test
    public void execute_partialKeyword_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED, 3);
        CodeOrNameMatchesKeywordPredicate predicate = preparePredicate("cs2");
        FindModCommand command = new FindModCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2100, CS2103), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code CodeOrNameMatchesKeywordPredicate}.
     */
    private CodeOrNameMatchesKeywordPredicate preparePredicate(String userInput) {
        return new CodeOrNameMatchesKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
