package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fma.model.LogBook;
import seedu.fma.model.Model;

/**
 * Clears the log book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Log book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLogBook(new LogBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
