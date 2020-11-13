package seedu.address.logic.parser.deliverable;

import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to deliverable commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_MILESTONE = new Prefix("m/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("by/");
    public static final Prefix PREFIX_CONTACTS = new Prefix("c/");

}
