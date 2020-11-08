package com.eva.logic.commands;

import static com.eva.commons.core.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
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
import com.eva.model.person.applicant.Applicant;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindApplicantCommandTest {
    private Model model = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate<Applicant> firstPredicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("first"));
        NameContainsKeywordsPredicate<Applicant> secondPredicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("second"));

        FindApplicantCommand findFirstCommand = new FindApplicantCommand(firstPredicate);
        FindApplicantCommand findSecondCommand = new FindApplicantCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindApplicantCommand findFirstCommandCopy = new FindApplicantCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate<Applicant> predicate = preparePredicate(" ");
        FindApplicantCommand command = new FindApplicantCommand(predicate);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.updateFilteredApplicantList(predicate);
        assertChangePanelCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Applicant> predicate = preparePredicate("Kurz Elle Kunz");
        FindApplicantCommand command = new FindApplicantCommand(predicate);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.updateFilteredApplicantList(predicate);
        assertChangePanelCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Applicant> preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
