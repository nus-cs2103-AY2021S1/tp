package seedu.bookmark.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_GENRE = new Prefix("g/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TOTAL_PAGES = new Prefix("tp/");
    public static final Prefix PREFIX_BOOKMARK = new Prefix("b/");
    public static final Prefix PREFIX_READING_PROGRESS = new Prefix("rp/");
    public static final Prefix PREFIX_PAGE = new Prefix("p/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_NOTE_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_NOTE_TEXT = new Prefix("txt/");
    public static final Prefix PREFIX_COMPLETED = new Prefix("c/");
    public static final Prefix PREFIX_NOT_COMPLETED = new Prefix("nc/");

    /**
     * Returns a prefix based on the input string.
     * @return Prefix based on the input string.
     */
    public static Prefix sortingPrefixGenerator(String prefix) {
        Prefix result;
        switch (prefix) {
        case "n/":
            result = PREFIX_NAME;
            break;
        case "g/":
            result = PREFIX_GENRE;
            break;
        case "b/":
            result = PREFIX_BOOKMARK;
            break;
        case "rp/":
            result = PREFIX_READING_PROGRESS;
            break;
        default:
            result = null;
            break;
        }
        return result;
    }
}
