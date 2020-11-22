package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions - for residents */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_STUDENT_GROUP = new Prefix("s/");
    public static final Prefix PREFIX_BLOCKROOM = new Prefix("br/");
    public static final Prefix PREFIX_BLOCK = new Prefix("b/");
    public static final Prefix PREFIX_FLOOR = new Prefix("f/");
    public static final Prefix PREFIX_ROOM_NUMBER = new Prefix("r/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_MATRICULATION_NUMBER = new Prefix("m/");

    /* Prefix definitions - for events */
    public static final Prefix PREFIX_EVENT_NAME = PREFIX_NAME;
    public static final Prefix PREFIX_EVENT_DESC = new Prefix("d/");
    public static final Prefix PREFIX_EVENT_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_EVENT_DATE = new Prefix("dt/");

}
