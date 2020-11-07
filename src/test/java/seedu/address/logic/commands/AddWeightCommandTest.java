package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Weight;
import seedu.address.testutil.FitNusBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Invalid inputs such as entering negative values, zero, or impossible numbers
 * will be handled by AddWeightCommandParser. AddWeightCommand asserts that the
 * value it receives is correct.
 */
public class AddWeightCommandTest {

    private static FitNusBuilder fitNusBuilder = new FitNusBuilder();
    private static final Model typicalModel = new ModelManager(fitNusBuilder.build(), new UserPrefs());

    @Test
    public void execute_validWeight_success() {

        //Pre-configured BMI is 17.58 which is the figure we will compare to.
        assertEquals(String.format("%.2f", typicalModel.getBmi()), "17.58");

        Weight newWeight = new Weight(75);
        typicalModel.addWeight(newWeight);
        AddWeightCommand AddWeightCommand = new AddWeightCommand(newWeight);

        String expectedMessage = String.format(AddWeightCommand.MESSAGE_SUCCESS, newWeight);

        try {
            assertEquals(expectedMessage, AddWeightCommand.execute(typicalModel).getFeedbackToUser());

            //After updating Weight of the user, the BMI will naturally change and we will check that.
            assertEquals(String.format("%.2f", typicalModel.getBmi()), "29.30");
        } catch (CommandException e) {
            fail();
        }
    }


    @Test
    public void equals() {
        Weight weightFirst = new Weight(75);
        Weight weightSecond = new Weight(76);

        AddWeightCommand addWeightCommandFirst = new AddWeightCommand(
                weightFirst);
        AddWeightCommand addWeightCommandSecond = new AddWeightCommand(
                weightSecond);

        // same object -> returns true
        assertTrue(addWeightCommandFirst.equals(addWeightCommandFirst));

        // same values -> returns true
        AddWeightCommand AddWeightCommandFirstCopy = new AddWeightCommand(
                weightFirst
        );
        assertTrue(addWeightCommandFirst.equals(AddWeightCommandFirstCopy));

        // different types -> returns false
        assertFalse(addWeightCommandFirst.equals(1));

        // null -> returns false
        assertFalse(addWeightCommandFirst.equals(null));

        // different Weight -> returns false
        assertFalse(addWeightCommandFirst.equals(addWeightCommandSecond));
    }
}
