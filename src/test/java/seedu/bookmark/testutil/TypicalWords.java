package seedu.bookmark.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.WordBank;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.wordstore.Word;



public class TypicalWords {
    public static final String HARRY = "harry";
    public static final String POTTER = "potter";
    public static final String AND = "and";
    public static final String CHAMBER = "chamber";
    public static final String SECRETS = "secrets";

    public static final String MISSPELT_HARRY = "hbrry";
    public static final String MISSPELT_POTTER = "pptter";
    public static final String MISSPELT_AND = "abd";
    public static final String MISSPELT_CHAMBER = "chabber";
    public static final String MISSPELT_SECRETS = "sekrets";

    //correctly spelt
    public static final Word CORRECT_HARRY = new Word("harry");
    public static final Word CORRECT_POTTER = new Word("potter");
    public static final Word CORRECT_AND = new Word("and");
    public static final Word CORRECT_CHAMBER = new Word("chamber");
    public static final Word CORRECT_SECRETS = new Word("secrets");

    private TypicalWords() {} //prevents instantiation

    /**
     * Returns an {@code WordBank} with all the typical books converted into words.
     */
    public static WordBank getTypicalWordBank() {
        Library lib = new Library();
        for (Book book : TypicalBooks.getTypicalBooks()) {
            lib.addBook(book);
        }
        return new WordBank(lib);
    }

    public static WordBank getEmptyWordBank() {
        Library lib = new Library();
        return new WordBank(lib);
    }

    public static List<Word> getTypicalWords() {
        return new ArrayList<>(Arrays.asList(CORRECT_HARRY, CORRECT_POTTER, CORRECT_AND,
                CORRECT_CHAMBER, CORRECT_SECRETS));
    }

    public static List<String> getTypicalStrings() {
        return new ArrayList<>(Arrays.asList(HARRY, POTTER, AND,
                CHAMBER, SECRETS));
    }




}
