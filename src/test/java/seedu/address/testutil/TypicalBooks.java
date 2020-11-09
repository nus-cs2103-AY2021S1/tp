package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOOK2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Library;
import seedu.address.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {
    public static final Book BOOK1 = new BookBuilder().withName("Alice")
            .withLanguage("English").withEmail("book1@example.com").withReviews()
            .withIsbn("94351253").withTimes("12")
            .withCategories("novels").withAuthor("author one")
            .withPublisher("publisher 1").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK2 = new BookBuilder().withName("Benson").withReviews()
            .withLanguage("Chinese").withTimes("20")
            .withEmail("book2@example.com").withIsbn("98765432")
            .withCategories("owesMoney", "friends").withAuthor("author two")
            .withPublisher("publisher 2").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK3 = new BookBuilder().withName("Carl").withIsbn("95352563")
            .withEmail("book3@example.com").withReviews().withTimes("20")
            .withLanguage("French").withAuthor("author 3")
            .withPublisher("publisher 3").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK4 = new BookBuilder().withName("Daniel").withIsbn("87652533")
            .withEmail("book4@example.com").withReviews().withLanguage("Chinese").withCategories("science")
            .withAuthor("author four").withPublisher("publisher 4").withTimes("20")
            .withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK5 = new BookBuilder().withName("Elle").withIsbn("9482224")
            .withEmail("book5@example.com").withReviews().withTimes("20")
            .withLanguage("English").withAuthor("author five")
            .withPublisher("publisher 5").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK6 = new BookBuilder().withName("Fiona").withIsbn("9482427")
            .withEmail("book6@example.com").withReviews().withTimes("20")
            .withLanguage("Japanese").withAuthor("author six")
            .withPublisher("publisher 6").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BOOK7 = new BookBuilder().withName("George").withIsbn("9482442")
            .withEmail("book7@example.com").withReviews().withTimes("20")
            .withLanguage("English").withAuthor("author seven")
            .withPublisher("publisher 7").withStocking("centralLb 10 scienceLb 10").build();

    // Manually added - Book's details found in {@code CommandTestUtil}
    public static final Book BOOK8 = new BookBuilder().withName(VALID_NAME_BOOK1).withIsbn(VALID_ISBN_BOOK1)
            .withEmail(VALID_EMAIL_BOOK1).withLanguage(VALID_LANGUAGE_BOOK1).withCategories(VALID_CATEGORY_SCIENCE)
            .withTimes("12").withStocking(VALID_STOCKING_BOOK1).withAuthor("a").withPublisher("pub").build();
    public static final Book BOOK9 = new BookBuilder().withName(VALID_NAME_BOOK2).withIsbn(VALID_ISBN_BOOK2)
            .withEmail(VALID_EMAIL_BOOK2).withLanguage(VALID_LANGUAGE_BOOK2).withAuthor("a").withPublisher("pub")
            .withTimes("12").withCategories(VALID_CATEGORY_MATH, VALID_CATEGORY_SCIENCE)
            .withStocking(VALID_STOCKING_BOOK2).build();

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code Library} with all the typical books.
     */
    public static Library getTypicalLibrary() {
        Library lib = new Library();
        for (Book book : getTypicalBooks()) {
            lib.addBook(book);
        }
        return lib;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(BOOK1, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7));
    }
}
