package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineAddExerciseCommand;
import seedu.address.logic.commands.routines.RoutineCreateCommand;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.commands.routines.RoutineFindCommand;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.routine.Routine;

/**
 * A utility class for Routine.
 */
public class RoutineUtil {

    public static final String WHITESPACE = " ";

    /**
     * Returns a routine create command string for adding the {@code routine}.
     *
     * @param routine The routine to be created.
     * @return The routine create command string.
     */
    public static String getRoutineCreateCommand(Routine routine) {
        return RoutineCreateCommand.COMMAND_WORD + WHITESPACE + getRoutineDetails(routine);
    }

    private static String getRoutineDetails(Routine routine) {
        return PREFIX_ROUTINE + routine.getName().fullName;
    }

    /**
     * Returns a routine delete command string.
     *
     * @param index The index of the routine.
     * @return The routine delete command string.
     */
    public static String getRoutineDeleteCommand(Index index) {
        return RoutineDeleteCommand.COMMAND_WORD + WHITESPACE + index.getOneBased();
    }

    /**
     * Returns a routine find command string.
     *
     * @param keyword The keyword for the find command.
     * @return The routine find command string.
     */
    public static String getRoutineFindCommand(String keyword) {
        return RoutineFindCommand.COMMAND_WORD + WHITESPACE + keyword;
    }

    /**
     * Returns a routine add exercise command string for adding the {@code exercise} to the {@code routine}.
     *
     * @param routine The routine to be added to.
     * @param exercise The exercise to add.
     * @return The routine add exercise command string.
     */
    public static String getRoutineAddExerciseCommand(Routine routine, Exercise exercise) {
        return RoutineAddExerciseCommand.COMMAND_WORD + WHITESPACE
                + getRoutineDetails(routine) + WHITESPACE + getExerciseDetails(exercise);
    }

    /**
     * Returns a routine delete exercise command string for deleting the {@code exercise} from the {@code routine}.
     *
     * @param routine The routine to delete from.
     * @param exercise The exercise to delete.
     * @return The routine add exercise command string.
     */
    public static String getRoutineDeleteExerciseCommand(Routine routine, Exercise exercise) {
        return RoutineDeleteExerciseCommand.COMMAND_WORD + WHITESPACE
                + getRoutineDetails(routine) + WHITESPACE + getExerciseDetails(exercise);
    }

    private static String getExerciseDetails(Exercise exercise) {
        return PREFIX_EXERCISE + exercise.getName().fullName;
    }

    /**
     * Returns a routine view command string for viewing a specific routine.
     *
     * @param index The index of the routine.
     * @return The routine view command string.
     */
    public static String getRoutineViewCommand(Index index) {
        return RoutineViewCommand.COMMAND_WORD + WHITESPACE + index.getOneBased();
    }
}
