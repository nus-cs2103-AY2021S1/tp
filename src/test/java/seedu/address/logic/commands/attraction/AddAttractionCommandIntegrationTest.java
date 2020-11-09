package seedu.address.logic.commands.attraction;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.testutil.AttractionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddAttractionCommand}.
 */
public class AddAttractionCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
    }

    @Test
    public void execute_newAttraction_success() {
        Attraction validAttraction = new AttractionBuilder().build();

        Model expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(), new UserPrefs());
        expectedModel.addAttraction(validAttraction);

        assertCommandSuccess(new AddAttractionCommand(validAttraction), model,
                String.format(AddAttractionCommand.MESSAGE_SUCCESS, validAttraction), expectedModel);
    }

    @Test
    public void execute_duplicateAttraction_throwsCommandException() {
        Attraction attractionInList = model.getAttractionList().getAttractionList().get(0);
        assertCommandFailure(new AddAttractionCommand(attractionInList), model,
                AddAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

}
