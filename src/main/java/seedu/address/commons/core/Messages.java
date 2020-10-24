package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_EMPTY_SEARCH_PHRASE = "Search phrase cannot be empty!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";
    public static final String MESSAGE_INVALID_TASKS_DISPLAYED_INDEX = "One or more task indexes provided are invalid";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_DUPLICATE_TASK_INDEX = "Please do not include duplicate indexes.";
    public static final String MESSAGE_INCORRECT_TASK_STATUS = "One or more targeted task is already completed.\n"
            + "Please check your command carefully.";
    public static final String MESSAGE_INVALID_DONE_TASK_TYPE = "You can only mark a deadline as done.\n"
            + "One or more task selected is not in deadline type";
    public static final String MESSAGE_INVALID_EVENT_EDIT_TYPE = "You cannot edit system generated lesson event";
    public static final String MESSAGE_INVALID_DEADLINE_EDIT_STATUS = "You cannot edit a deadline after marked as done";


}
