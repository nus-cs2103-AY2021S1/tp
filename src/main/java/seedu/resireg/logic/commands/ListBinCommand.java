package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.model.Model.PREDICATE_SHOW_ALL_BIN_ITEMS;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Lists all students in ResiReg to the user.
 */
public class ListBinCommand extends Command {

    public static final String COMMAND_WORD = "bin";

    public static final String MESSAGE_SUCCESS = "Listed all bin items";

    public static final Help HELP = new Help(COMMAND_WORD, "Lists all bin items.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredBinItemList(PREDICATE_SHOW_ALL_BIN_ITEMS);
        return new ToggleCommandResult(MESSAGE_SUCCESS, TabView.BIN_ITEMS);
    }
}
