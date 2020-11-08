package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.body.Height;
import seedu.address.testutil.FitNusBuilder;

/**
 * Invalid inputs such as entering negative values, zero, or impossible numbers
 * will be handled by AddHeightCommandParser. AddHeightCommand asserts that the
 * value it receives is correct.
 */
public class AddHeightCommandTest {

    private static FitNusBuilder fitNusBuilder = new FitNusBuilder();
    private static final Model typicalModel = new ModelManager(fitNusBuilder.build(), new UserPrefs());

    @Test
    public void execute_validHeight_success() {

        //Pre-configured BMI is 17.58 which is the figure we will compare to.
        assertEquals(String.format("%.2f", typicalModel.getBmi()), "17.58");

        Height newHeight = new Height(170);
        typicalModel.addHeight(newHeight);
        AddHeightCommand addHeightCommand = new AddHeightCommand(newHeight);

        String expectedMessage = String.format(AddHeightCommand.MESSAGE_SUCCESS, newHeight);

        try {
            assertEquals(expectedMessage, addHeightCommand.execute(typicalModel).getFeedbackToUser());

            //After updating Height of the user, the BMI will naturally change and we will check that.
            assertEquals(String.format("%.2f", typicalModel.getBmi()), "15.57");
        } catch (CommandException e) {
            fail();
        }
    }


    @Test
    public void equals() {
        Height heightFirst = new Height(170);
        Height heightSecond = new Height(180);

        AddHeightCommand addHeightCommandFirst = new AddHeightCommand(
                heightFirst);
        AddHeightCommand addHeightCommandSecond = new AddHeightCommand(
                heightSecond);

        // same object -> returns true
        assertTrue(addHeightCommandFirst.equals(addHeightCommandFirst));

        // same values -> returns true
        AddHeightCommand addHeightCommandFirstCopy = new AddHeightCommand(
                heightFirst
        );
        assertTrue(addHeightCommandFirst.equals(addHeightCommandFirstCopy));

        // different types -> returns false
        assertFalse(addHeightCommandFirst.equals(1));

        // null -> returns false
        assertFalse(addHeightCommandFirst.equals(null));

        // different Height -> returns false
        assertFalse(addHeightCommandFirst.equals(addHeightCommandSecond));
    }
}
