package seedu.address.logic.commands.hairdresser;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showHairdresserWithId;
import static seedu.address.testutil.TypicalHairdressers.getTypicalHairStyleX;
import static seedu.address.testutil.TypicalIds.ID_FIRST_HAIRDRESSER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListHairdresserCommand.
 */
public class ListHairdresserCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHairStyleX(), new UserPrefs());
        expectedModel = new ModelManager(model.getHairStyleX(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListHairdresserCommand(), model,
                ListHairdresserCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showHairdresserWithId(model, ID_FIRST_HAIRDRESSER);
        assertCommandSuccess(new ListHairdresserCommand(), model,
                ListHairdresserCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
