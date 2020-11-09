package seedu.address.logic.parser;

import java.util.Arrays;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_URL = new Prefix("url/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_TIME = new Prefix("time/");
    public static final Prefix PREFIX_STARTDATE = new Prefix("startdate/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("starttime/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("enddate/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("endtime/");
    public static final Prefix PREFIX_RECURRING = new Prefix("recurring/");
    public static final Prefix PREFIX_CONTACT_INDEX = new Prefix("contactIndex/");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("taskIndex/");

    public static final Prefix[] PLURAL_PREFIX_ARRAY = {PREFIX_TAG};

    public static boolean isPrefixSingular(Prefix p) {
        return !Arrays.asList(PLURAL_PREFIX_ARRAY).contains(p);
    }
}
