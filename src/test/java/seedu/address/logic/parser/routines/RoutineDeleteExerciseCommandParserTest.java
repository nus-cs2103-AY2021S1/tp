package seedu.address.logic.parser.routines;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SPRINTS;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.FitNus;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.util.Name;

public class RoutineDeleteExerciseCommandParserTest {
    private final RoutineDeleteExerciseCommandParser parser = new RoutineDeleteExerciseCommandParser();

    @Test
    public void parse_allRoutineFieldsPresent_success() {
        Routine legDay = new Routine(new Name("Leg Day"));

        // whitespace only preamble
        assertParseSuccess(parser, " r/Leg Day e/Sprints", new RoutineDeleteExerciseCommand(legDay, SPRINTS));
    }

    @Test
    public void parse_compulsoryRoutineFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoutineDeleteExerciseCommand.MESSAGE_USAGE);

        // missing routine prefix
        assertParseFailure(parser, " Leg Day e/Squats", expectedMessage);
    }

    @Test
    public void parse_compulsoryExerciseFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoutineDeleteExerciseCommand.MESSAGE_USAGE);

        // missing exercise prefix
        assertParseFailure(parser, " r/Leg Day Squats", expectedMessage);
    }

    @Test
    public void parse_invalidRoutineAndExerciseValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoutineDeleteExerciseCommand.MESSAGE_USAGE);

        //invalid Routine and Exercise
        assertParseFailure(parser, " Leg Day Squats", expectedMessage);

        // invalid Routine
        assertParseFailure(parser, PREAMBLE_WHITESPACE + LEG_DAY + "&" + " e/Squats", expectedMessage);

        //invalid Exercise
        assertParseFailure(parser, PREAMBLE_WHITESPACE + LEG_DAY + " e/Squats" + "&", expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + "Leg Day", expectedMessage);

        //Double Routine entries
        assertParseFailure(parser, " r/Leg Day r/Lower Body e/Squats", expectedMessage);

        //Double Exercise entries
        assertParseFailure(parser, " r/Leg Day e/Squats e/Hamstrings", expectedMessage);

    }

    @Test
    public void routineAndExerciseProduced() {
        try {
            FitNus fitNus = new FitNus();
            LEG_DAY.addExercise(BENCH_PRESS);
            fitNus.addRoutine(LEG_DAY);
            fitNus.addExercise(BENCH_PRESS);
            assertTrue(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));
            RoutineDeleteExerciseCommand command = parser.parse(" r/Leg Day e/Bench Press");
            command.execute(new ModelManager(fitNus, new UserPrefs()));
            assertFalse(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));
        } catch (ParseException | CommandException e) {
            fail();
        }
    }
}
