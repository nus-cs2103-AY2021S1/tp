package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;

//@@author {zhXchD}
/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteJournalEntryCommand extends Command {

    public static final String COMMAND_WORD = "deletej";

    public static final String MESSAGE_USAGE = "%s: Deletes the entry at the "
            + "index position in the currently "
            + "displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: %s 1";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS =
        "Deleted Entry: %1$s";

    private final Index targetIndex;

    public DeleteJournalEntryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteEntry(entryToDelete);
        boolean isEmptyList = lastShownList.size() == 0;
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete))
            .setJournalTab().setCleaningJournalView(isEmptyList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DeleteJournalEntryCommand
            && ((DeleteJournalEntryCommand) other).targetIndex.equals(targetIndex));
    }
}
