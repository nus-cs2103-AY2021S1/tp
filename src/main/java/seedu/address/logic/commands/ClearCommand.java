package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BugList;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Description book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBugList(new BugList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
