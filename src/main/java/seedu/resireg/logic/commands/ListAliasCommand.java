package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Lists all students in the address book to the user.
 */
public class ListAliasCommand extends Command {

    public static final String COMMAND_WORD = "aliases";

    public static final String MESSAGE_SUCCESS = "Here are the command-alias pairs defined so far: \n%1$s";
    public static final String MESSAGE_EMPTY_ALIAS = "No command-alias pairs have been defined so far.";


    public static final Help HELP = new Help(COMMAND_WORD, "Lists all command-alias pairs.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);
        if (model.getCommandWordAliases().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_ALIAS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getCommandWordAliasesAsString()));
    }
}
