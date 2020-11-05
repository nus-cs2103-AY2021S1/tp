package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Lists all the nonempty commands entered by user from the start of app launch,
 * in chronological order. The positive integer n in front of a command
 * specifies that it is the nth command to be entered.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "Entered nonempty commands: \n%1$s";
    public static final String MESSAGE_NO_HISTORY = "No nonempty commands have been entered yet.";

    public static final Help HELP =
            new Help(COMMAND_WORD, "Shows all previous nonempty commands and their respective positions"
                    + " entered in chronological order.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(history);

        String result = history.getCommandResultString();

        if (result.equals("")) {
            return new CommandResult(MESSAGE_NO_HISTORY);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, result));
    }

}
