package seedu.bookmark.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;

/**
 * An Immutable Library that is serializable to JSON format.
 */
@JsonRootName(value = "bookmark")
class JsonSerializableLibrary {

    public static final String MESSAGE_DUPLICATE_BOOKS = "book list contains duplicate book(s).";

    private final List<JsonAdaptedBook> books = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLibrary} with the given books.
     */
    @JsonCreator
    public JsonSerializableLibrary(@JsonProperty("books") List<JsonAdaptedBook> books) {
        this.books.addAll(books);
    }

    /**
     * Converts a given {@code ReadOnlyLibrary} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLibrary}.
     */
    public JsonSerializableLibrary(ReadOnlyLibrary source) {
        books.addAll(source.getBookList().stream().map(JsonAdaptedBook::new).collect(Collectors.toList()));
    }

    /**
     * Converts this library into the model's {@code Library} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Library toModelType() throws IllegalValueException {
        Library library = new Library();
        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (library.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOKS);
            }
            library.addBook(book);
        }
        return library;
    }

}
