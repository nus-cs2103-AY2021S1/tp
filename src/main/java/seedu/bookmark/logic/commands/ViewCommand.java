package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;

/**
 * Allows user to view a all details of a specific book
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View all details of the book identified by index number in book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Viewing %1$d";

    public final Index index;

    public ViewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToView = lastShownList.get(index.getZeroBased());
        model.updateFilteredBookList(b -> b.equals(bookToView));
        model.save();
        storeViewType(model.getCurrentState(), ViewType.DETAILED);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()),
                false, false, ViewType.DETAILED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ViewCommand) {
            return index.equals(((ViewCommand) other).index);
        } else {
            return false;
        }
    }
}
