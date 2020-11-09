package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_JOB_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_COMPANY_NAME = new Prefix("c/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("pr/");
    public static final Prefix PREFIX_VACANCY = new Prefix("v/");
    public static final Prefix PREFIX_EXPERIENCE = new Prefix("exp/");
    public static final Prefix PREFIX_URL_LINK = new Prefix("link/");
    public static final Prefix PREFIX_SALARY = new Prefix("sal/");
    public static final Prefix PREFIX_DATE_OF_APPLICATION = new Prefix("doa/");
    public static final Prefix PREFIX_BLACKLIST = new Prefix("bl/");

    /* Sort Keywords */
    public static final Prefix PREFIX_SORT_ORDER = new Prefix("order/");
    public static final Prefix PREFIX_SORT_TYPE = new Prefix("type/");
}
