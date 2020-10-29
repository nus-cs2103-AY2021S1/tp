package seedu.taskmaster.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("u/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_NUSNETID = new Prefix("i/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ATTENDANCE_TYPE = new Prefix("a/");
    public static final Prefix PREFIX_ATTENDANCE_FILENAME = new Prefix("fn/");
    public static final Prefix PREFIX_SESSION_NAME = new Prefix("s/");
    public static final Prefix PREFIX_SESSION_DATE_TIME = new Prefix("dt/");
}
