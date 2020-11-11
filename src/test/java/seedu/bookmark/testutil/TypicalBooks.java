package seedu.bookmark.testutil;

import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_JANE_EYRE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {

    public static final Book HARRY_POTTER = new BookBuilder().withName("Harry Potter")
            .withGenre("Fiction")
            .withTags("Good")
            .withTotalPages("1000")
            .withBookmark("500").build();
    public static final Book TO_KILL_A_MOCKINGBIRD = new BookBuilder().withName("To Kill a Mockingbird")
            .withGenre("Fiction")
            .withTags("Good", "Lengthy")
            .withTotalPages("1500")
            .withBookmark("269")
            .withNotes("Chapter 1", "Chapter 2", "Chapter 3").build();
    public static final Book THE_HUNGER_GAMES = new BookBuilder().withName("The Hunger Games")
            .withGenre("Fiction")
            .withTotalPages("500")
            .withBookmark("20").build();
    public static final Book CRIME_AND_PUNISHMENT = new BookBuilder().withName("Crime and Punishment")
            .withGenre("Crime")
            .withTags("Bad")
            .withTotalPages("1000")
            .withBookmark("1000").build();
    public static final Book LORD_OF_THE_FLIES = new BookBuilder().withName("Lord of the Flies")
            .withGenre("Mystery").withTotalPages("369")
            .withBookmark("123").build();
    public static final Book ENDERS_GAME = new BookBuilder().withName("Enders Game")
            .withGenre("Thriller").withTotalPages("123")
            .withBookmark("23").build();
    public static final Book ON_THE_ROAD = new BookBuilder().withName("On the Road")
            .withGenre("Travel").withTotalPages("239")
            .withBookmark("111").build();

    // Manually added
    public static final Book HAMLET = new BookBuilder().withName("Hamlet")
            .withGenre("Classic").withTotalPages("239").build();
    public static final Book THE_FAULT_IN_OUR_STARS = new BookBuilder().withName("The Fault in Our Stars")
            .withGenre("Tragic").withTotalPages("239").build();

    // Manually added - Books's compulsory details found in {@code CommandTestUtil}
    public static final Book COMPULSORY_NINETEEN_EIGHTY_FOUR = new BookBuilder().withName(VALID_NAME_1984)
            .withGenre(VALID_GENRE_1984)
            .withTotalPages(VALID_TOTAL_PAGES_1984).build();
    public static final Book COMPULSORY_JANE_EYRE = new BookBuilder().withName(VALID_NAME_JANE_EYRE)
            .withGenre(VALID_GENRE_JANE_EYRE)
            .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE).build();

    // Manually added - Book's full details found in {@code CommandTestUtil}
    public static final Book FULL_NINETEEN_EIGHTY_FOUR = new BookBuilder().withName(VALID_NAME_1984)
            .withGenre(VALID_GENRE_1984)
            .withTags(VALID_TAG_GOOD, VALID_TAG_BAD)
            .withTotalPages(VALID_TOTAL_PAGES_1984)
            .withBookmark(VALID_BOOKMARK_1984).build();
    public static final Book FULL_JANE_EYRE = new BookBuilder().withName(VALID_NAME_JANE_EYRE)
            .withGenre(VALID_GENRE_JANE_EYRE)
            .withTags(VALID_TAG_GOOD, VALID_TAG_BAD)
            .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE)
            .withBookmark(VALID_BOOKMARK_JANE_EYRE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code Library} with all the typical books.
     */
    public static Library getTypicalLibrary() {
        Library ab = new Library();
        for (Book book : getTypicalBooks()) {
            ab.addBook(book);
        }
        return ab;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(HARRY_POTTER, TO_KILL_A_MOCKINGBIRD, THE_HUNGER_GAMES,
                CRIME_AND_PUNISHMENT, LORD_OF_THE_FLIES, ENDERS_GAME, ON_THE_ROAD));
    }
}
