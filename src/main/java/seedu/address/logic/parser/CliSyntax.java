package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_SPECIALISATION = new Prefix("s/");
    public static final Prefix PREFIX_TITLE = new Prefix("ti/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // prefixes for Appointment
    public static final Prefix PREFIX_CLIENT_ID = new Prefix("cid/");
    public static final Prefix PREFIX_HAIRDRESSER_ID = new Prefix("hid/");
    public static final Prefix PREFIX_DATE_OF_APPT = new Prefix("d/");
    public static final Prefix PREFIX_START_TIME = new Prefix("t/");
    public static final Prefix PREFIX_APPT_STATUS = new Prefix("s/");
    public static final String PLACEHOLDER_CLIENT_INDEX = "CLIENT_INDEX";
    public static final String PLACEHOLDER_HAIRDRESSER_INDEX = "HAIRDRESSER_INDEX";
    public static final String PLACEHOLDER_DATE_OF_APPT = "DATE_OF_APPT";
    public static final String PLACEHOLDER_START_TIME = "START_TIME";
    public static final String PLACEHOLDER_APPT_STATUS = "APPT_STATUS";

    public static final Prefix PREFIX_OLD_COMMAND = new Prefix("old/");
    public static final Prefix PREFIX_NEW_COMMAND = new Prefix("new/");

}
