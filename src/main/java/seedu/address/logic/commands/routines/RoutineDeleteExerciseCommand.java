package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.address.model.routine.Routine;

/**
 * Deletes an exercise from a routine in fitNUS.
 */
public class RoutineDeleteExerciseCommand extends Command {

    public static final String COMMAND_WORD = "routine_delete_exercise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the exercise from the specified routine. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE "
            + PREFIX_EXERCISE + "EXERCISE"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session "
            + PREFIX_EXERCISE + "Squats ";

    public static final String MESSAGE_DELETE_EXERCISE_SUCCESS = "Deleted Exercise from Routine: %1$s";
    public static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS!";
    public static final String MESSAGE_MISSING_EXERCISE = "This exercise does not exist within this routine!";

    private final Routine routine;
    private final Exercise exercise;

    /**
     * Creates a RoutineDeleteExerciseCommand to delete the specified {@code Exercise} from the {@code Routine}
     */
    public RoutineDeleteExerciseCommand(Routine routine, Exercise exercise) {
        requireNonNull(routine);
        this.routine = routine;
        this.exercise = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoutine(routine)) {
            throw new CommandException(MESSAGE_MISSING_ROUTINE);
        } else if (!model.hasExercise(exercise)) {
            throw new CommandException(MESSAGE_MISSING_EXERCISE);
        }

        try {
            model.deleteExerciseFromRoutine(routine, exercise);
            return new CommandResult(String.format(String.format(MESSAGE_DELETE_EXERCISE_SUCCESS,
                    routine), exercise));
        } catch (ExerciseNotFoundException e) {
            throw new CommandException(MESSAGE_MISSING_EXERCISE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineDeleteExerciseCommand // instanceof handles nulls
                && exercise.equals(((RoutineDeleteExerciseCommand) other).exercise) // state check
                && routine.equals(((RoutineDeleteExerciseCommand) other).routine)); // state check
    }
}
