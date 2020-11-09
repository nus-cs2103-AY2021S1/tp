package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAttemptsCommand extends PerformanceCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all attempts in Performance interface \n"
            + "Example: " + COMMAND_WORD + "(case sensitive, 'list xx' is not allowed)";

    public static final String MESSAGE_LIST_ATTEMPTS_SUCCESS = "Listing all past attempts";

    @Override
    public PerformanceCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new PerformanceCommandResult(MESSAGE_LIST_ATTEMPTS_SUCCESS, true, false);
    }
}
