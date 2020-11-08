package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.exceptions.DuplicateExerciseException;
import seedu.address.model.routine.Routine;

/**
 * Adds an exercise to a routine in fitNUS.
 */
public class RoutineAddExerciseCommand extends Command {

    public static final String COMMAND_WORD = "routine_add_exercise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing exercise to a routine in fitNUS. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE "
            + PREFIX_EXERCISE + "EXERCISE"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session "
            + PREFIX_EXERCISE + "Squats ";

    public static final String MESSAGE_SUCCESS = "Exercise added to Routine: %1$s";
    public static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS";
    public static final String MESSAGE_MISSING_EXERCISE = "This exercise does not exist in fitNUS";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the routine!";


    private final Routine routineToAdd;
    private final Exercise exerciseToAdd;

    /**
     * Creates a RoutineAddExerciseCommand to add the specified {@code Exercise} to the {@code Routine}
     */
    public RoutineAddExerciseCommand(Routine routine, Exercise exercise) {
        requireNonNull(routine);
        routineToAdd = routine;
        exerciseToAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoutine(routineToAdd)) {
            throw new CommandException(MESSAGE_MISSING_ROUTINE);
        } else if (!model.hasExercise(exerciseToAdd)) {
            throw new CommandException(MESSAGE_MISSING_EXERCISE);
        }

        try {
            model.addExerciseToRoutine(routineToAdd, exerciseToAdd);
            return new CommandResult(String.format(String.format(MESSAGE_SUCCESS, routineToAdd), exerciseToAdd));
        } catch (DuplicateExerciseException error) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineAddExerciseCommand // instanceof handles nulls
                && routineToAdd.equals(((RoutineAddExerciseCommand) other).routineToAdd))
                && exerciseToAdd.equals(((RoutineAddExerciseCommand) other).exerciseToAdd);
    }
}

