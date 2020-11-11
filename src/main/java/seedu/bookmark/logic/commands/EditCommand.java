package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TOTAL_PAGES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.commons.util.CollectionUtil;
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
 * Edits the details of an existing book in the library.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the book identified "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENRE + "GENRE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_TOTAL_PAGES + "TOTAL_PAGES] "
            + "[" + PREFIX_BOOKMARK + "BOOKMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GENRE + "Horror";

    public static final String MESSAGE_EDIT_BOOK_SUCCESS = "Edited Book: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the library.";
    private static final Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;

    private final Index index;
    private final EditBookDescriptor editBookDescriptor;

    /**
     * @param index of the book in the filtered book list to edit
     * @param editBookDescriptor details to edit the book with
     */
    public EditCommand(Index index, EditBookDescriptor editBookDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookDescriptor);

        this.index = index;
        this.editBookDescriptor = new EditBookDescriptor(editBookDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, editBookDescriptor);
        if (!bookToEdit.isSameBook(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }
        if (!Bookmark.isValidBookmark(editedBook.getBookmark(), editedBook.getTotalPages())) {
            throw new CommandException(Bookmark.MESSAGE_CONSTRAINTS);
        }
        model.setBook(bookToEdit, editedBook);
        model.sortByDefaultComparator();
        model.save();
        storeViewType(model.getCurrentState(), ViewType.MOST_RECENTLY_USED);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOK_SUCCESS, editedBook),
                false, false, ViewType.MOST_RECENTLY_USED);
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    private static Book createEditedBook(Book bookToEdit, EditBookDescriptor editBookDescriptor) {
        assert bookToEdit != null;

        Name updatedName = editBookDescriptor.getName().orElse(bookToEdit.getName());
        Genre updatedGenre = editBookDescriptor.getGenre().orElse(bookToEdit.getGenre());
        Set<Tag> updatedTags = editBookDescriptor.getTags().orElse(bookToEdit.getTags());
        TotalPages updatedTotalPages = editBookDescriptor.getTotalPages().orElse(bookToEdit.getTotalPages());
        Bookmark updatedBookmark = editBookDescriptor.getBookmark().orElse(bookToEdit.getBookmark());
        Goal currentGoal = bookToEdit.getGoal();
        List<Note> currentNotes = bookToEdit.getNotes();

        return new Book(updatedName, updatedGenre, updatedTags,
                updatedTotalPages, updatedBookmark, currentGoal, currentNotes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editBookDescriptor.equals(e.editBookDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditBookDescriptor {
        private Name name;
        private Genre genre;
        private Set<Tag> tags;
        private TotalPages totalPages;
        private Bookmark bookmark;

        public EditBookDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setName(toCopy.name);
            setGenre(toCopy.genre);
            setTags(toCopy.tags);
            setTotalPages(toCopy.totalPages);
            setBookmark(toCopy.bookmark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, genre, tags, totalPages, bookmark);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setGenre(Genre genre) {
            this.genre = genre;
        }

        public Optional<Genre> getGenre() {
            return Optional.ofNullable(genre);
        }

        public void setTotalPages(TotalPages totalPages) {
            this.totalPages = totalPages;
        }

        public Optional<TotalPages> getTotalPages() {
            return Optional.ofNullable(totalPages);
        }

        public void setBookmark(Bookmark bookmark) {
            this.bookmark = bookmark;
        }

        public Optional<Bookmark> getBookmark() {
            return Optional.ofNullable(bookmark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookDescriptor)) {
                return false;
            }

            // state check
            EditBookDescriptor e = (EditBookDescriptor) other;

            return getName().equals(e.getName())
                    && getGenre().equals(e.getGenre())
                    && getTags().equals(e.getTags())
                    && getTotalPages().equals(e.getTotalPages())
                    && getBookmark().equals(e.getBookmark());
        }
    }
}
