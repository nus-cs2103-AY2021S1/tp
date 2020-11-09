package seedu.address.logic.commands.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;
import static seedu.address.testutil.TypicalItineraries.SG_ZOOS_TOUR;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.itinerary.ItineraryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindItineraryCommand}.
 */
class FindItineraryCommandTest {
    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(),
            new UserPrefs());

    @Test
    public void equals() {
        ItineraryContainsKeywordsPredicate firstPredicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("first"));
        ItineraryContainsKeywordsPredicate secondPredicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("second"));

        FindItineraryCommand findFirstCommand = new FindItineraryCommand(firstPredicate);
        FindItineraryCommand findSecondCommand = new FindItineraryCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindItineraryCommand findFirstCommandCopy = new FindItineraryCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noItineraryFound() {
        String expectedMessage = String.format(MESSAGE_ITINERARIES_LISTED_OVERVIEW, 0);
        ItineraryContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindItineraryCommand command = new FindItineraryCommand(predicate);
        expectedModel.updateFilteredItineraryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItineraryList());
    }

    @Test
    public void execute_multipleKeywords_multipleItinerariesFound() {
        String expectedMessage = String.format(MESSAGE_ITINERARIES_LISTED_OVERVIEW, 2);
        ItineraryContainsKeywordsPredicate predicate = preparePredicate("Zoo paris");
        FindItineraryCommand command = new FindItineraryCommand(predicate);
        expectedModel.updateFilteredItineraryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SG_ZOOS_TOUR, PARIS_TRIP),
                model.getFilteredItineraryList());
    }

    @Test
    public void execute_hasCurrentItineraryBefore_noCurrentItineraryAfter() {
        // Follows execute_multipleKeywords_multipleItinerariesFound() except with current itinerary
        String expectedMessage = String.format(MESSAGE_ITINERARIES_LISTED_OVERVIEW, 2);
        ItineraryContainsKeywordsPredicate predicate = preparePredicate("Zoo paris");
        FindItineraryCommand command = new FindItineraryCommand(predicate);
        expectedModel.updateFilteredItineraryList(predicate);

        model.setCurrentItinerary(JAPAN_TRIP);
        expectedModel.setCurrentItinerary(null);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code ItineraryContainsKeywordsPredicate}.
     */
    private ItineraryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItineraryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
