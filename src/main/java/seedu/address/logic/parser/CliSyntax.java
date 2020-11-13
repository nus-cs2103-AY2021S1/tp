package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("mod/");
    public static final Prefix PREFIX_TIMETABLE_URL = new Prefix("url/");
    public static final Prefix PREFIX_EXPECTED_HOURS = new Prefix("hrs/");
    public static final Prefix PREFIX_DO_BEFORE = new Prefix("by/");
    public static final Prefix PREFIX_DO_AFTER = new Prefix("af/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");
}
