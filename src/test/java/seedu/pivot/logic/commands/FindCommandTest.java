package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_CASES_LISTED_OVERVIEW;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ARCHIVED_CASES;
import static seedu.pivot.model.Model.PREDICATE_SHOW_DEFAULT_CASES;
import static seedu.pivot.testutil.TypicalCases.CARL_KURZ_FIRE;
import static seedu.pivot.testutil.TypicalCases.ELLE_MEYER_SHOOTING;
import static seedu.pivot.testutil.TypicalCases.FIONA_KUNZ_KIDNAPPING;
import static seedu.pivot.testutil.TypicalCases.JUNK_YARD_MURDER;
import static seedu.pivot.testutil.TypicalCases.KLOOK_SCAM;
import static seedu.pivot.testutil.TypicalCases.LOUIS_HOMICIDE;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.DetailsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void equals() {
        DetailsContainsKeywordsPredicate firstPredicate =
                new DetailsContainsKeywordsPredicate(Collections.singletonList("first"));
        DetailsContainsKeywordsPredicate secondPredicate =
                new DetailsContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_CASES_LISTED_OVERVIEW, 0);
        DetailsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCaseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCaseList());
    }

    @Test
    public void execute_multipleKeywordsArchivedSection_multiplePersonsFound() {
        StateManager.setArchivedSection();

        String expectedMessage = String.format(MESSAGE_CASES_LISTED_OVERVIEW, 3);
        DetailsContainsKeywordsPredicate predicate = preparePredicate("Junk Klook Louis Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCaseList(predicate.and(PREDICATE_SHOW_ARCHIVED_CASES));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JUNK_YARD_MURDER, KLOOK_SCAM,
                LOUIS_HOMICIDE), model.getFilteredCaseList());

        StateManager.setDefaultSection();
    }

    @Test
    public void execute_multipleKeywordsDefaultSection_multiplePersonsFound() {
        StateManager.setDefaultSection();
        String expectedMessage = String.format(MESSAGE_CASES_LISTED_OVERVIEW, 3);
        DetailsContainsKeywordsPredicate predicate = preparePredicate("Junk Klook Louis Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCaseList(predicate.and(PREDICATE_SHOW_DEFAULT_CASES));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL_KURZ_FIRE, ELLE_MEYER_SHOOTING,
                FIONA_KUNZ_KIDNAPPING), model.getFilteredCaseList());
    }

    /**
     * Parses {@code userInput} into a {@code DetailsContainsKeywordsPredicate}.
     */
    private DetailsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DetailsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
