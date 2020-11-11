package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Goal;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * Adds a Note to a Book.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "notedel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a note from the book. \n"
                                                       + "Parameters: INDEX (must be a positive integer) "
                                                       + "NOTE_INDEX (must be a positive integer)";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Note: %1$s, from Book: %2$s";

    private final Index index;
    private final Index noteIndex;
    /**
     * Creates a DeleteNoteCommand to delete a {@code note} at {@code index} from the specified book.
     */
    public DeleteNoteCommand(Index index, Index noteIndex) {
        requireNonNull(index);
        requireNonNull(noteIndex);
        this.index = index;
        this.noteIndex = noteIndex;
    }

    /**
     * Creates and returns a {@code Book} with the updated details of {@code bookToEdit} without the deleted note.
     */
    protected static Book createEditedBook(Book bookToEdit, Index index) {
        assert bookToEdit != null;

        Name updatedName = bookToEdit.getName();
        Genre updatedGenre = bookToEdit.getGenre();
        Set<Tag> updatedTags = bookToEdit.getTags();
        TotalPages updatedTotalPages = bookToEdit.getTotalPages();
        Bookmark updatedBookmark = bookToEdit.getBookmark();
        Goal updatedGoal = bookToEdit.getGoal();
        List<Note> updatedNotes = new ArrayList<>(bookToEdit.getNotes());
        updatedNotes.remove(index.getZeroBased());

        return new Book(updatedName, updatedGenre, updatedTags,
                updatedTotalPages, updatedBookmark, updatedGoal, updatedNotes);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }
        Book bookToEdit = lastShownList.get(index.getZeroBased());
        if (noteIndex.getZeroBased() >= bookToEdit.getNotes().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }
        Note deletedNote = bookToEdit.getNotes().get(noteIndex.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, noteIndex);

        model.setBook(bookToEdit, editedBook);
        model.save();
        storeViewType(model.getCurrentState(), ViewType.MOST_RECENTLY_USED);
        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS,
                deletedNote.title, bookToEdit.getName()), false, false,
                ViewType.MOST_RECENTLY_USED);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteNoteCommand)) {
            return false;
        }

        // state check
        DeleteNoteCommand e = (DeleteNoteCommand) other;
        return index.equals(e.index)
                       && noteIndex.equals(e.noteIndex);

    }
}
