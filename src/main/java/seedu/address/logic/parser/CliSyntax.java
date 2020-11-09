package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_IMPORTANCE = new Prefix("i/");
    public static final Prefix PREFIX_COST = new Prefix("c/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_MOBILE = new Prefix("m/");
    public static final Prefix PREFIX_PASSPORT = new Prefix("p/");
    public static final Prefix PREFIX_START = new Prefix("sd/");
    public static final Prefix PREFIX_END = new Prefix("ed/");

    /* travel plan object type prefix */
    public static final Prefix PREFIX_TPO = new Prefix("-");
}
