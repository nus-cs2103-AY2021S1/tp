package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_ICNUMBER = new Prefix("ic/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_FILE_PATH = new Prefix(("f/"));
    public static final Prefix PREFIX_SEX = new Prefix("s/");
    public static final Prefix PREFIX_BLOODTYPE = new Prefix("b/");
    public static final Prefix PREFIX_ALLERGY = new Prefix("g/");
    public static final Prefix PREFIX_COLORTAG = new Prefix("ct/");
    public static final Prefix PREFIX_VISIT_DATE = new Prefix("vd/");
    public static final Prefix PREFIX_VISIT_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_APP_PATIENTNAME = new Prefix("pn/");
    public static final Prefix PREFIX_APP_PATIENTIC = new Prefix("pi/");
    public static final Prefix PREFIX_APP_STARTTIME = new Prefix("st/");
    public static final Prefix PREFIX_APP_DURATION = new Prefix("d/");
}
