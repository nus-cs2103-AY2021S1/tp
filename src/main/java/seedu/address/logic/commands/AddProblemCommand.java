package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEVERITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.problem.Problem;

/**
 * Adds a book to the intellibrary.
 */
public class AddProblemCommand extends Command {

    public static final String COMMAND_WORD = "report";
    public static final String SUGGESTION = "report s/<severity> d/<description>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reports a problem. " + "Parameters: " + PREFIX_SEVERITY
            + "severity " + PREFIX_DESCRIPTION + "description ";
    public static final String MESSAGE_DUPLICATE_PROB = "This problem already exists";
    public static final String MESSAGE_SUCCESS = "problem reported: %1$s";

    private final Problem toAdd;

    /**
     * Creates an AddProblemCommand to add the specified {@code problem}
     */
    public AddProblemCommand(Problem problem) {
        requireNonNull(problem);
        toAdd = problem;
    }

    /**
     * Executes add problem command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     * @throws CommandException if duplicate problem
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProblem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROB);
        }

        model.addProblem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProblemCommand // instanceof handles nulls
                && toAdd.equals(((AddProblemCommand) other).toAdd));
    }
}
