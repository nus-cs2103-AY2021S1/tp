package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.storage.Storage;

/**
 * Edits the details of an existing student in ResiReg.
 */
public class RestoreCommand extends Command {

    public static final String COMMAND_WORD = "restore";

    public static final String MESSAGE_RESTORE_SUCCESS = "Restore success!";
    public static final String MESSAGE_DUPLICATE_ITEM = "Cannot restore since this item already exists in ResiReg.";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Restores the bin item.",
        "Parameters: INDEX (must be a positive integer) ");

    private final Index index;

    /**
     * @param index of the bin item in the bin item list to edit
     */
    public RestoreCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<BinItem> lastShownList = model.getFilteredBinItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BIN_ITEM_DISPLAYED_INDEX);
        }

        BinItem binItem = lastShownList.get(index.getZeroBased());
        Binnable itemToRestore = binItem.getBinnedItem();

        if (model.hasDuplicateBinnedItem(itemToRestore)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.restoreItem(itemToRestore);
        model.deleteBinItem(binItem);
        model.saveStateResiReg();
        return new CommandResult(MESSAGE_RESTORE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RestoreCommand)) {
            return false;
        }

        // state check
        RestoreCommand e = (RestoreCommand) other;
        return index.equals(e.index);
    }
}
