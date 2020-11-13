package seedu.address.logic.parser.meeting;

import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_FROM = new Prefix("from/");
    public static final Prefix PREFIX_TO = new Prefix("to/");
    public static final Prefix PREFIX_CONTACTS = new Prefix("c/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");

}
