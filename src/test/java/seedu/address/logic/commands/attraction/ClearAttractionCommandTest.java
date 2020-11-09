package seedu.address.logic.commands.attraction;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.Test;

import seedu.address.model.AttractionList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearAttractionCommandTest {

    @Test
    public void execute_emptyTrackPad_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAttractionCommand(), model,
                ClearAttractionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAttractionList_success() {
        Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        expectedModel.setAttractionList(new AttractionList());

        assertCommandSuccess(new ClearAttractionCommand(), model,
                ClearAttractionCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
