package seedu.address.logic.commands.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ATTRACTIONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.BOTANIC_GARDENS;
import static seedu.address.testutil.TypicalAttractions.ORCHARD_ROAD;
import static seedu.address.testutil.TypicalAttractions.RIVER_SAFARI;
import static seedu.address.testutil.TypicalAttractions.SINGAPORE_ZOO;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAttractionCommand}.
 */
public class FindAttractionCommandTest {
    private Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(),
            new UserPrefs());

    @Test
    public void equals() {
        AttractionContainsKeywordsPredicate firstPredicate =
                new AttractionContainsKeywordsPredicate(Collections.singletonList("first"));
        AttractionContainsKeywordsPredicate secondPredicate =
                new AttractionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAttractionCommand findFirstCommand = new FindAttractionCommand(firstPredicate);
        FindAttractionCommand findSecondCommand = new FindAttractionCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAttractionCommand findFirstCommandCopy = new FindAttractionCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different attraction -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAttractionFound() {
        String expectedMessage = String.format(MESSAGE_ATTRACTIONS_LISTED_OVERVIEW, 0);
        AttractionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAttractionCommand command = new FindAttractionCommand(predicate);
        expectedModel.updateFilteredAttractionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAttractionList());
    }

    @Test
    public void execute_multipleKeywords_multipleAttractionsFound() {
        String expectedMessage = String.format(MESSAGE_ATTRACTIONS_LISTED_OVERVIEW, 4);
        AttractionContainsKeywordsPredicate predicate = preparePredicate("Zoo Orchard Gardens");
        FindAttractionCommand command = new FindAttractionCommand(predicate);
        expectedModel.updateFilteredAttractionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SINGAPORE_ZOO, RIVER_SAFARI, ORCHARD_ROAD, BOTANIC_GARDENS),
                model.getFilteredAttractionList());
    }

    /**
     * Parses {@code userInput} into a {@code AttractionContainsKeywordsPredicate}.
     */
    private AttractionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AttractionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
