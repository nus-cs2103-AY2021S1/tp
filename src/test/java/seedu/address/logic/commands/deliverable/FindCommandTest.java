package seedu.address.logic.commands.deliverable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELIVERABLES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliverables.HOME_PAGE;
import static seedu.address.testutil.TypicalDeliverables.NAVIGATION;
import static seedu.address.testutil.TypicalDeliverables.SORT_AND_FILTER;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.TitleDescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private ModelDeliverable modelDeliverable = new ModelDeliverableManager(
            getTypicalDeliverableBook(), new UserPrefs());
    private ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
            getTypicalDeliverableBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleDescriptionContainsKeywordsPredicate firstPredicate =
                new TitleDescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleDescriptionContainsKeywordsPredicate secondPredicate =
                new TitleDescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different deliverable -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDeliverableFound() {
        String expectedMessage = String.format(MESSAGE_DELIVERABLES_LISTED_OVERVIEW, 0);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModelDeliverable.updateFilteredDeliverableList(predicate);
        assertCommandSuccess(command, modelDeliverable, expectedMessage, expectedModelDeliverable);
        assertEquals(Collections.emptyList(), modelDeliverable.getFilteredDeliverableList());
    }

    @Test
    public void execute_multipleTitleKeywords_multipleDeliverablesFound() {
        String expectedMessage = String.format(MESSAGE_DELIVERABLES_LISTED_OVERVIEW, 3);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("build navigation filter");
        FindCommand command = new FindCommand(predicate);
        expectedModelDeliverable.updateFilteredDeliverableList(predicate);
        assertCommandSuccess(command, modelDeliverable, expectedMessage, expectedModelDeliverable);
        assertEquals(
                Arrays.asList(HOME_PAGE, NAVIGATION, SORT_AND_FILTER), modelDeliverable.getFilteredDeliverableList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_multipleDeliverablesFound() {
        String expectedMessage = String.format(MESSAGE_DELIVERABLES_LISTED_OVERVIEW, 2);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("company box");
        FindCommand command = new FindCommand(predicate);
        expectedModelDeliverable.updateFilteredDeliverableList(predicate);
        assertCommandSuccess(command, modelDeliverable, expectedMessage, expectedModelDeliverable);
        assertEquals(Arrays.asList(HOME_PAGE, SORT_AND_FILTER), modelDeliverable.getFilteredDeliverableList());
    }

    @Test
    public void execute_multipleTitleDescriptionKeywords_multipleDeliverablesFound() {
        String expectedMessage = String.format(MESSAGE_DELIVERABLES_LISTED_OVERVIEW, 2);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("filter tab");
        FindCommand command = new FindCommand(predicate);
        expectedModelDeliverable.updateFilteredDeliverableList(predicate);
        assertCommandSuccess(command, modelDeliverable, expectedMessage, expectedModelDeliverable);
        assertEquals(Arrays.asList(NAVIGATION, SORT_AND_FILTER), modelDeliverable.getFilteredDeliverableList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TitleDescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
