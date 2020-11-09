package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.model.review.Review;
import seedu.address.ui.Mode;

/**
 * Edits the details of an existing book in the intellibrary.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String SUGGESTION = "edit <index> n/<name> i</isbn> e/<email> l/<language> t/<times>"
            + "c/<category>… s/<stocking> a/<author> p/<publisher>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the book identified "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ISBN + "ISBN] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_LANGUAGE + "LANGUAGE] "
            + "[" + PREFIX_TIMES + "TIMES] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
            + "[" + PREFIX_STOCKING + "STOCKING] "
            + "[" + PREFIX_AUTHOR + "AUTHOR] "
            + "[" + PREFIX_PUBLISHER + "PUBLISHER] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ISBN + "13091234567 "
            + PREFIX_EMAIL + "thisbook@publisher.com";

    public static final String MESSAGE_EDIT_BOOK_SUCCESS = "Edited Book: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the intellibrary.";

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

    /**
     * Executes edit command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     * @throws CommandException if invalid index
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, editBookDescriptor);

        if (!bookToEdit.isSameBook(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOK_SUCCESS, editedBook));
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    private static Book createEditedBook(Book bookToEdit, EditBookDescriptor editBookDescriptor) {
        assert bookToEdit != null;

        Name updatedName = editBookDescriptor.getName().orElse(bookToEdit.getName());
        Isbn updatedIsbn = editBookDescriptor.getIsbn().orElse(bookToEdit.getIsbn());
        Email updatedEmail = editBookDescriptor.getEmail().orElse(bookToEdit.getEmail());
        Language updatedLanguage = editBookDescriptor.getLanguage().orElse(bookToEdit.getLanguage());
        List<Review> bookReviews = bookToEdit.getReviews();
        Set<Category> updatedCategories = editBookDescriptor.getCategories().orElse(bookToEdit.getCategories());
        Author updatedAuthor = editBookDescriptor.getAuthor().orElse(bookToEdit.getAuthor());
        Publisher updatedPublisher = editBookDescriptor.getPublisher().orElse(bookToEdit.getPublisher());
        Stocking updatedStocking = editBookDescriptor.getStocking().orElse(bookToEdit.getStocking());
        Times updatedTimes = editBookDescriptor.getTimes().orElse(bookToEdit.getTimes());

        return new Book(updatedName, updatedIsbn, updatedEmail,
                updatedLanguage, updatedTimes, updatedCategories, updatedStocking, bookReviews,
                updatedAuthor, updatedPublisher);

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
        private Isbn isbn;
        private Email email;
        private Language language;
        private Times times;
        private Set<Category> categories;
        private Author author;
        private Publisher publisher;
        private Stocking stocking;

        public EditBookDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code categories} is used internally.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setName(toCopy.name);
            setIsbn(toCopy.isbn);
            setEmail(toCopy.email);
            setLanguage(toCopy.language);
            setTimes(toCopy.times);
            setCategories(toCopy.categories);
            setAuthor(toCopy.author);
            setPublisher(toCopy.publisher);
            setStocking(toCopy.stocking);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, isbn, email, language, times,
                categories, publisher, author, stocking);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setIsbn(Isbn isbn) {
            this.isbn = isbn;
        }

        public Optional<Isbn> getIsbn() {
            return Optional.ofNullable(isbn);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTimes(Times times) {
            this.times = times;
        }

        public Optional<Times> getTimes() {
            return Optional.ofNullable(times);
        }

        public void setLanguage(Language language) {
            this.language = language;
        }

        public Optional<Language> getLanguage() {
            return Optional.ofNullable(language);
        }

        /**
         * Sets {@code categories} to this object's {@code categories}.
         * A defensive copy of {@code categories} is used internally.
         */
        public void setCategories(Set<Category> categories) {
            this.categories = (categories != null) ? new HashSet<>(categories) : null;
        }

        /**
         * Returns an unmodifiable category set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code categories} is null.
         */
        public Optional<Set<Category>> getCategories() {
            return (categories != null) ? Optional.of(Collections.unmodifiableSet(categories)) : Optional.empty();
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Optional<Author> getAuthor() {
            return Optional.ofNullable(author);
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public Optional<Publisher> getPublisher() {
            return Optional.ofNullable(publisher);
        }

        public void setStocking(Stocking stocking) {
            this.stocking = stocking;
        }

        public Optional<Stocking> getStocking() {
            return Optional.ofNullable(stocking);
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
                    && getIsbn().equals(e.getIsbn())
                    && getEmail().equals(e.getEmail())
                    && getLanguage().equals(e.getLanguage())
                    && getCategories().equals(e.getCategories())
                    && getStocking().equals(e.getStocking())
                    && getTimes().equals(e.getTimes())
                    && getAuthor().equals(e.getAuthor())
                    && getPublisher().equals(e.getPublisher());

        }
    }
}
