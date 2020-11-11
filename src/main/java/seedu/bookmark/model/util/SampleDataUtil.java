package seedu.bookmark.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSampleBooks() {
        return new Book[] {
            new Book(new Name("MIPS Book"), new Genre("Educational"),
                getTagSet("Good"), new TotalPages("500"), new Bookmark("50")),
            new Book(new Name("Algorithms"), new Genre("Educational"),
                getTagSet("Bad", "Lengthy"), new TotalPages("5050"), new Bookmark()),
            new Book(new Name("Barney"), new Genre("Children"),
                getTagSet("Funny"), new TotalPages("25"), new Bookmark("5")),
            new Book(new Name("The Book"), new Genre("Fiction"),
                getTagSet("Lame"), new TotalPages("333"), new Bookmark()),
            new Book(new Name("The Bible"), new Genre("Religion"),
                getTagSet("Holy"), new TotalPages("10000"), new Bookmark("25")),
            new Book(new Name("The Girl with the Dragon Tatoo"), new Genre("Novel"),
                getTagSet("Classic"), new TotalPages("1500"), new Bookmark("222"))
        };
    }

    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleLibrary = new Library();
        for (Book sampleBook : getSampleBooks()) {
            sampleLibrary.addBook(sampleBook);
        }
        return sampleLibrary;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list containing notes with the same title and text provided by the list of strings given.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                       .map(string -> new Note(string, string))
                       .collect(Collectors.toList());
    }

}
