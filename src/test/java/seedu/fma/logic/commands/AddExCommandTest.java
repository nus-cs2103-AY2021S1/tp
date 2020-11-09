package seedu.fma.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.UserPrefs;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

class AddExCommandTest {
    private Model model = new ModelManager(getTypicalLogBook(), new UserPrefs());
    private Exercise validExercise = new Exercise(new Name("Jump"), new Calories(99));

    @Test
    public void constructor_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExCommand(null));
    }

    @Test
    public void execute_logAcceptedByModel_addSuccessful() throws Exception {
        CommandResult commandResult = new AddExCommand(validExercise).execute(model);

        assertEquals(String.format(AddExCommand.MESSAGE_SUCCESS, validExercise), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateLog_throwsCommandException() {
        AddExCommand addExCommand = new AddExCommand(validExercise);

        assertThrows(CommandException.class, AddExCommand.MESSAGE_DUPLICATE_LOG, () -> {
            addExCommand.execute(model);
            addExCommand.execute(model);
        });
    }
}
