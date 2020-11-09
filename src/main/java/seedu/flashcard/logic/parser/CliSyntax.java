package seedu.flashcard.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_QUESTION = new Prefix("q/");
    public static final Prefix PREFIX_ANSWER = new Prefix("a/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");
    public static final Prefix PREFIX_NOTE = new Prefix("n/");
    public static final Prefix PREFIX_RATING = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DIAGRAM = new Prefix("d/");
    public static final Prefix PREFIX_FAV = new Prefix("f/");
    public static final Prefix PREFIX_FLAG = new Prefix("-");
}
