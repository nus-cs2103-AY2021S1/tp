package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_SCHOOL = new Prefix("s/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");
    public static final Prefix PREFIX_PAYMENT = new Prefix("d/");
    public static final Prefix PREFIX_DETAILS = new Prefix("a/");

    public static final Prefix[] COMPULSORY_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE,
        PREFIX_SCHOOL, PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT};

    public static final Prefix[] ALL_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL,
        PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT, PREFIX_DETAILS};

    /**
     * Returns a list of all CLI syntax definitions.
     */
    public static Prefix[] valuesOf() {
        return new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL,
            PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_DETAILS,
            PREFIX_FEE, PREFIX_PAYMENT};
    }
}
