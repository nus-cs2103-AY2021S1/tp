package seedu.expense.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-d");
    public static final Prefix PREFIX_AMOUNT = new Prefix("-$");
    public static final Prefix PREFIX_DATE = new Prefix("-@");
    public static final Prefix PREFIX_REMARK = new Prefix("-r");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_SORT = new Prefix("-by");

}
