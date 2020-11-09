package seedu.address.logic.commands.itinerary;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListItineraryCommand.
 */
class ListItineraryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListItineraryCommand(), model, ListItineraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showItineraryAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListItineraryCommand(), model, ListItineraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hasCurrentItineraryBefore_noCurrentItineraryAfter() {
        model.setCurrentItinerary(PARIS_TRIP);
        expectedModel.setCurrentItinerary(null);
        assertCommandSuccess(new ListItineraryCommand(), model, ListItineraryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
