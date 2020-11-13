package seedu.jarvis.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.\nJarvis currently only supports Add, Delete,"
            + " Edit, Help, View, and Exit commands.\nFor more information, please type 'help' and press enter to visit"
            + " our User Guide and find out more.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The student index provided is invalid.";
    public static final String MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX =
            "The mastery check index provided is invalid.";
    public static final String MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX =
            "The consultation index provided is invalid.";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_ID = "The task ID provided is invalid.\nUse 'view -t' "
            + "command to check the task ID for the Task you are going to delete.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_STUDENT_NAME_NOT_FOUND = "The student name does not match anybody in Jarvis.";
    public static final String MESSAGE_NULL_MODEL = "Model does not contain Students.";
    public static final String MESSAGE_VIEW_TYPE_NOT_FOUND = "Invalid view command, unable to identify view type.";

}
