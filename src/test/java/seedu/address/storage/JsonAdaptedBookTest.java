package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;


public class JsonAdaptedBookTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ISBN = "+651234";
    private static final String INVALID_LANGUAGE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CATEGORY = "#friend";

    private static final String VALID_NAME = BOOK2.getName().toString();
    private static final String VALID_ISBN = BOOK2.getIsbn().toString();
    private static final String VALID_EMAIL = BOOK2.getEmail().toString();
    private static final String VALID_LANGUAGE = BOOK2.getLanguage().toString();
    private static final String VALID_TIMES = BOOK2.getTimes().toString();
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = BOOK2.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());
    private static final String VALID_AUTHOR = BOOK2.getAuthor().toString();
    private static final String VALID_PUBLISHER = BOOK2.getPublisher().toString();
    private static final JsonAdaptedStocking VALID_STOCKING = new JsonAdaptedStocking(BOOK2.getStocking());
    private static final List<JsonAdaptedReview> VALID_REVIEWS = BOOK2.getReviews().stream()
            .map(JsonAdaptedReview::new)
            .collect(Collectors.toList());

    /* @Test
    public void toModelType_validBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(BENSON);
        assertEquals(BENSON, book.toModelType());
    } // storage parser */

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(INVALID_NAME, VALID_ISBN, VALID_EMAIL, VALID_LANGUAGE, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(null, VALID_ISBN, VALID_EMAIL, VALID_LANGUAGE,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, INVALID_ISBN, VALID_EMAIL, VALID_LANGUAGE, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Isbn.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, null, VALID_EMAIL, VALID_LANGUAGE,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, INVALID_EMAIL, VALID_LANGUAGE, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, VALID_ISBN, null, VALID_LANGUAGE,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidLanguage_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, INVALID_LANGUAGE, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Language.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullLanguage_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, null,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Language.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORY));
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, VALID_LANGUAGE,
                        VALID_TIMES, invalidCategories, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        assertThrows(IllegalValueException.class, book::toModelType);
    }

}
