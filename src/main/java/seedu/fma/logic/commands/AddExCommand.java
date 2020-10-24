package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fma.logic.parser.CliSyntax.*;

import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.exercise.Exercise;

/**
 * Adds an exercise to the log book.
 */
public class AddExCommand extends Command {

    public static final String COMMAND_WORD = "addex";

    public static final String AC_SUGGESTION = COMMAND_WORD + " "
            + PREFIX_E + "<exercise> "
            + PREFIX_C + "<calories per rep>";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to the log book. "
            + "Parameters: "
            + PREFIX_E + "<exercise> "
            + PREFIX_C + "<calories per rep>";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_LOG = "This exercise already exists in the log book";

    private final Exercise toAdd;

    /**
     * Creates an AddExCommand to add the specified {@code Exercise}
     */
    public AddExCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LOG);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddExCommand
                && toAdd.equals(((AddExCommand) other).toAdd));
    }

}
