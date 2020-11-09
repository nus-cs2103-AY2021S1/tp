package seedu.fma.logic.parser;

import static seedu.fma.logic.commands.CommandTestUtil.CALORIES_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_B;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_B;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.AddExCommand;
import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

class AddExCommandParserTest {
    private final AddExCommandParser parser = new AddExCommandParser();
    private final LogBook logBook = new LogBook();
    private final Exercise exerciseA = new Exercise(new Name(EXERCISE_A), new Calories(30));
    private final Exercise exerciseB = new Exercise(new Name(EXERCISE_B), new Calories(30));

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void parse_exercises_success() {
        assertParseSuccess(parser, EXERCISE_DESC_A + CALORIES_DESC_A,
                new AddExCommand(exerciseA), logBook);
        assertParseSuccess(parser, EXERCISE_DESC_B + CALORIES_DESC_A,
                new AddExCommand(exerciseB), logBook);
    }
}
