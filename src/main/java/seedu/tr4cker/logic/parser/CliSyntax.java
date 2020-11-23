package seedu.tr4cker.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("des/");
    public static final Prefix PREFIX_COMPLETION_STATUS = new Prefix("p/");
    public static final Prefix PREFIX_DELETE_TAG = new Prefix("del/");
    public static final Prefix PREFIX_NEW_TAG = new Prefix("new/");
    public static final Prefix PREFIX_MODULE_NAME = new Prefix("n/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("m/");
    public static final Prefix PREFIX_MODULE_DELETE = new Prefix("del/");
    public static final Prefix PREFIX_PLANNER_GOTO = new Prefix("goto/");
    public static final Prefix PREFIX_COUNTDOWN_NEW = new Prefix("n/");
    public static final Prefix PREFIX_COUNTDOWN_TASK = new Prefix("task/");
    public static final Prefix PREFIX_COUNTDOWN_DELETE = new Prefix("del/");
    public static final Prefix PREFIX_COUNTDOWN_DATE = new Prefix("d/");
    public static final Prefix PREFIX_COUNTDOWN_DAYS = new Prefix("days/");
    public static final Prefix PREFIX_DAILY_DELETE = new Prefix("del/");

}
