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

    /* Prefix for question commands */
    public static final Prefix PREFIX_ADD_QUESTION = new Prefix("a/");
    public static final Prefix PREFIX_SOLVE_QUESTION = new Prefix("s/");
    public static final Prefix PREFIX_DELETE_QUESTION = new Prefix("d/");

    /* Prefix for additionalDetail commands */
    public static final Prefix PREFIX_DETAIL_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_DETAIL_TEXT = new Prefix("d/");

    /* Prefix for Schedule */
    public static final Prefix PREFIX_VIEW = new Prefix("view");
    public static final Prefix PREFIX_VIEW_MODE = new Prefix("mode/");
    public static final Prefix PREFIX_VIEW_DATE = new Prefix("date/");
    public static final Prefix PREFIX_ADD_EVENT = new Prefix("add");
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EVENT_START_DATE_TIME = new Prefix("start/");
    public static final Prefix PREFIX_EVENT_END_DATE_TIME = new Prefix("end/");
    public static final Prefix PREFIX_DELETE_EVENT = new Prefix("delete");
    public static final Prefix PREFIX_RECURRENCE = new Prefix("r/");

    public static final Prefix[] COMPULSORY_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE,
        PREFIX_SCHOOL, PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT};

    public static final Prefix[] ALL_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL,
        PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT, PREFIX_DETAILS};

    public static final Prefix[] FIND_SUPPORTED_PREFIXES =
            new Prefix[] {PREFIX_NAME, PREFIX_SCHOOL, PREFIX_YEAR};

    public static final Prefix[] QUESTION_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_ADD_QUESTION, PREFIX_SOLVE_QUESTION, PREFIX_DELETE_QUESTION};

    public static final Prefix[] ADDITIONAL_DETAIL_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_DETAIL_INDEX, PREFIX_DETAIL_TEXT};

    public static final Prefix[] SCHEDULE_COMMAND_PREFIXES = new Prefix[] {PREFIX_VIEW, PREFIX_VIEW_MODE,
        PREFIX_VIEW_DATE, PREFIX_EVENT_NAME, PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME,
        PREFIX_ADD_EVENT, PREFIX_RECURRENCE, PREFIX_DELETE_EVENT};

    public static final Prefix[] SCHEDULE_ADD_COMPULSORY_PREFIXES = new Prefix[] {PREFIX_ADD_EVENT,
        PREFIX_EVENT_NAME, PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME, PREFIX_RECURRENCE};
}
