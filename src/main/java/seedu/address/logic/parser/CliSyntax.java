package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Patient prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_BLOODTYPE = new Prefix("bt/");
    public static final Prefix PREFIX_BIRTHDATE = new Prefix("bd/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");

    /* Appointment prefix definitions */
    public static final Prefix PREFIX_APPOINTMENT_START = new Prefix("start/");
    public static final Prefix PREFIX_APPOINTMENT_END = new Prefix("end/");
    public static final Prefix PREFIX_PATIENT = new Prefix("pt/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");

    /* General prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");
}
