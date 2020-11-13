package seedu.jarvis.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("t/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_USERNAME = new Prefix("u/");
    public static final Prefix PREFIX_PASSWORD = new Prefix("p/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");

    public static final String PREFIX_DATE = "d/";
    public static final String PREFIX_TIME = "t/";

    /* Edit Command flags */
    public static final String EDIT_STUDENT = "-s";
    public static final String EDIT_LOGIN = "-l";
    public static final String EDIT_MASTERY_CHECK = "-mc";

    /* View Command flags */
    public static final String UNGRADED_MISSION = "-um";
    public static final String UNGRADED_QUEST = "-uq";
    public static final String MISSION_DEADLINE = "-m";
    public static final String QUEST_DEADLINE = "-q";
    public static final String VIEW_STUDENT = "-s";
    public static final String VIEW_TASK_LIST = "-t";
    public static final String VIEW_TODO_LIST = "-tt";
    public static final String VIEW_EVENT_LIST = "-te";
    public static final String VIEW_DEADLINE_LIST = "-td";
    public static final String VIEW_CONSULTATION_LIST = "-c";
    public static final String VIEW_UPCOMING_CONSULTATION_LIST = "-cu";
    public static final String VIEW_PAST_CONSULTATION_LIST = "-cp";
    public static final String VIEW_MASTERY_CHECK_LIST = "-mc";
    public static final String VIEW_UPCOMING_MASTERY_CHECK_LIST = "-mcu";
    public static final String VIEW_PAST_MASTERY_CHECK_LIST = "-mcp";

    /* Add Command flags */
    public static final String TASK_TODO = "-t";
    public static final String TASK_EVENT = "-e";
    public static final String TASK_DEADLINE = "-d";
    public static final String CONSULTATION = "-c";
    public static final String MASTERY_CHECK = "-mc";

    /* Delete Command flags */
    public static final String DELETE_TASK = "-t";
    public static final String DELETE_CONSULTATION = "-c";
    public static final String DELETE_MASTERY_CHECK = "-mc";
}
