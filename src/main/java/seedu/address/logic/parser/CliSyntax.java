package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_PROJECT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/");
    public static final Prefix PREFIX_REPOURL = new Prefix("ru/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_PROJECT_TAG = new Prefix("tg/");
    public static final Prefix PREFIX_TASK = new Prefix("tk/");
    // Prefixes related to Task
    public static final Prefix PREFIX_TASK_ASSIGNEE = new Prefix("ta/");
    public static final Prefix PREFIX_TASK_NAME = new Prefix("tn/");
    public static final Prefix PREFIX_TASK_DEADLINE = new Prefix("td/");
    public static final Prefix PREFIX_TASK_PROGRESS = new Prefix("tp/");
    public static final Prefix PREFIX_TASK_IS_DONE = new Prefix("done/");
    // Prefixes related to Task Sorter
    public static final Prefix PREFIX_ASCENDING_SORT = new Prefix("sa/");
    public static final Prefix PREFIX_DESCENDING_SORT = new Prefix("sd/");
    // Prefixes related to Task Filter
    public static final Prefix PREFIX_START_DATE = new Prefix("start/");
    public static final Prefix PREFIX_END_DATE = new Prefix("end/");
    // Prefixes related to Teammate
    public static final Prefix PREFIX_PERSON_ADDRESS = new Prefix("ma/");
    public static final Prefix PREFIX_PERSON_EMAIL = new Prefix("me/");
    public static final Prefix PREFIX_PERSON_GIT_USERNAME = new Prefix("mg/");
    public static final Prefix PREFIX_PERSON_NAME = new Prefix("mn/");
    public static final Prefix PREFIX_PERSON_PHONE = new Prefix("mp/");

}
