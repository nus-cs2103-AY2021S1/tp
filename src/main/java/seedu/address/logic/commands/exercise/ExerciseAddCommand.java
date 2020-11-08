package seedu.address.logic.commands.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exercise.Exercise;

/**
 * Adds an exercise to fitNUS.
 */
public class ExerciseAddCommand extends Command {

    public static final String COMMAND_WORD = "exercise_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to fitNUS. "
            + "Parameters: "
            + PREFIX_EXERCISE + "EXERCISE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EXERCISE + "Bench Press "
            + PREFIX_TAG + "Chest "
            + PREFIX_TAG + "Triceps";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in fitNUS";

    private final Exercise toAdd;

    /**
     * Creates an ExerciseAddCommand to add the specified {@code Exercise}
     */
    public ExerciseAddCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseAddCommand // instanceof handles nulls
                && toAdd.equals(((ExerciseAddCommand) other).toAdd));
    }
}
