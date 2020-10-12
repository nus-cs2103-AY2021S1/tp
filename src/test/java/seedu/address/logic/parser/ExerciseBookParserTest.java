package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.ExerciseBuilder;



public class ExerciseBookParserTest {
    private final ExerciseBookParser parser = new ExerciseBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Exercise exercise = new ExerciseBuilder().build();
        AddCommand command = (AddCommand) parser.
                parseCommand(AddCommand.COMMAND_WORD + " "
                        + "n/Push up d/Testing 1 2 3 at/09-10-2020 c/2254");
        assertEquals(new AddCommand(exercise), command);
    }
}
