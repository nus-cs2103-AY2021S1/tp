package seedu.homerce.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_AMOUNT = new Prefix("v/");
    public static final Prefix PREFIX_ISFIXED = new Prefix("f/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DATE = new Prefix("dt/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");

    /* Prefixes for Service commands */
    public static final Prefix PREFIX_SERVICE_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_SERVICE_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_SERVICE_DURATION = new Prefix("du/");
    public static final Prefix PREFIX_SERVICE_SERVICE_CODE = new Prefix("s/");

    // Prefixes for Appointment command
    public static final Prefix PREFIX_TIME_OF_DAY = new Prefix("t/");
    public static final Prefix PREFIX_MONTH_OF_YEAR = new Prefix("m/");

}
