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
 * Deletes a book identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the index number used in the displayed book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted Book: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBook(bookToDelete);
        model.save();
        storeViewType(model.getCurrentState(), ViewType.DEFAULT);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
