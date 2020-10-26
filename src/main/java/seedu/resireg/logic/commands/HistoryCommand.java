package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Lists all the commands entered by user from the start of app launch,
 * in reverse chronological order.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "Entered commands: \n%1$s";
    public static final String MESSAGE_NO_HISTORY = "No commands have been entered so far.";

    public static final Help HELP =
            new Help(COMMAND_WORD, "Shows all commands entered in reverse chronological order");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(history);
        ArrayList<String> previousCommands = new ArrayList<>(history.getHistory());

        if (previousCommands.size() == 0) {
            return new CommandResult(MESSAGE_NO_HISTORY);
        }

        Collections.reverse(previousCommands);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", previousCommands)));
    }

}
