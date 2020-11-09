package seedu.address.logic.parser.patient;

import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple patient-related commands
 */
public class PatientCliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TEMP = new Prefix("t/");
    public static final Prefix PREFIX_PERIOD_OF_STAY = new Prefix("d/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_AGE = new Prefix("a/");
    public static final Prefix PREFIX_COMMENTS = new Prefix("c/");
    public static final Prefix PREFIX_TEMP_RANGE = new Prefix("tr/");

}
