package com.eva.logic.commands;

import static com.eva.commons.core.Messages.MESSAGE_STAFFS_LISTED_OVERVIEW;
import static com.eva.logic.commands.CommandTestUtil.assertChangePanelCommandSuccess;
import static com.eva.testutil.TypicalPersons.CARL;
import static com.eva.testutil.TypicalPersons.ELLE;
import static com.eva.testutil.TypicalPersons.FIONA;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.PanelState;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.NameContainsKeywordsPredicate;
import com.eva.model.person.staff.Staff;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindStaffCommandTest {
    private Model model = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate<Staff> firstPredicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("first"));
        NameContainsKeywordsPredicate<Staff> secondPredicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("second"));

        FindStaffCommand findFirstCommand = new FindStaffCommand(firstPredicate);
        FindStaffCommand findSecondCommand = new FindStaffCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStaffCommand findFirstCommandCopy = new FindStaffCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate<Staff> predicate = preparePredicate("some_funny_things");
        FindStaffCommand command = new FindStaffCommand(predicate);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        expectedModel.updateFilteredStaffList(predicate);
        assertChangePanelCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStaffList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Staff> predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindStaffCommand(predicate);
        expectedModel.updateFilteredStaffList(predicate);
        assertChangePanelCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStaffList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Staff> preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
