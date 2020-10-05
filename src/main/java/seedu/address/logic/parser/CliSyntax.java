package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_SCHOOL = new Prefix("s/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_CLASSVENUE = new Prefix("v/");
    public static final Prefix PREFIX_CLASSTIME = new Prefix("ti/");
    public static final Prefix PREFIX_ADDITIONALDETAILS = new Prefix("a/");
    public static final Prefix PREFIX_MEETINGLINK = new Prefix("m/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("sb/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_ADDRESS = new Prefix("z/");
    public static final Prefix PREFIX_EMAIL = new Prefix("x/");
}
