package seedu.resireg.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    // students
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_FACULTY = new Prefix("f/");
    public static final Prefix PREFIX_STUDENT_ID = new Prefix("i/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STUDENT_INDEX = new Prefix("si/");
    // rooms
    public static final Prefix PREFIX_ROOM_INDEX = new Prefix("ri/");
    public static final Prefix PREFIX_ROOM_FLOOR = new Prefix("floor/");
    public static final Prefix PREFIX_ROOM_NUMBER = new Prefix("number/");
    public static final Prefix PREFIX_ROOM_TYPE = new Prefix("type/");

    /**
     * Prefix for --style keywords eg. --vacant
     */
    public static final Prefix PREFIX_KEYWORD = new Prefix("--");
}
