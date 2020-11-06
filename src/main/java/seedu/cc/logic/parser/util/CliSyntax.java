package seedu.cc.logic.parser.util;

import seedu.cc.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Account Commands Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    /* Entry Commands Prefix definitions */
    // To determine whether an expense or a revenue is added
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_AMOUNT = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_KEYWORDS = new Prefix("k/");

}
