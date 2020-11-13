package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPerson.getTypicalMyFitnessBuddy;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.day.Day;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());
    }

    /*
    @Test
    public void execute_newDay_success() {
        Day validDay = new DayBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addDay(validDay);

        assertCommandSuccess(new AddCommand(validDay), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validDay), expectedModel);
    }
*/

    //    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Day dayInList = model.getMyFitnessBuddy().getPerson().getDayList().get(0);
        assertCommandFailure(new AddCommand(dayInList), model, AddCommand.MESSAGE_DUPLICATE_DAY);
    }

}
