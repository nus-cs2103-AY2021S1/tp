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
                parseCommand("add n/run d/half an hour at/30-09-2020 /c 12345");
        assertEquals(new AddCommand(exercise), command);
    }
}
