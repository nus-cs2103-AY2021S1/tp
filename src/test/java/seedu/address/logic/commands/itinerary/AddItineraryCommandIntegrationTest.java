package seedu.address.logic.commands.itinerary;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.testutil.ItineraryBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddItineraryCommand}.
 */
public class AddItineraryCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
    }

    @Test
    public void execute_newItinerary_success() {
        Itinerary validItinerary = new ItineraryBuilder().build();

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        expectedModel.addItinerary(validItinerary);

        assertCommandSuccess(new AddItineraryCommand(validItinerary), model,
                String.format(AddItineraryCommand.MESSAGE_SUCCESS, validItinerary), expectedModel);
    }

    @Test
    public void execute_duplicateItinerary_throwsCommandException() {
        Itinerary itineraryInList = model.getItineraryList().getItineraryList().get(0);
        assertCommandFailure(new AddItineraryCommand(itineraryInList), model,
                AddItineraryCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

    @Test
    public void execute_hasCurrentItineraryBefore_noCurrentItineraryAfter() {
        // Follows execute_newItinerary_success() except with current itinerary
        Itinerary validItinerary = new ItineraryBuilder().build();

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        expectedModel.addItinerary(validItinerary);
        expectedModel.setCurrentItinerary(null);

        model.setCurrentItinerary(PARIS_TRIP);

        assertCommandSuccess(new AddItineraryCommand(validItinerary), model,
                String.format(AddItineraryCommand.MESSAGE_SUCCESS, validItinerary), expectedModel);
    }
}
