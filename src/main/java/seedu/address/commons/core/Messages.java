package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String UNKNOWN_EDIT_COMMAND = "Only edit contact, todo, and event are available";
    public static final String UNKNOWN_FIND_COMMAND = "Only find contact, todo, and event are available";
    public static final String UNKNOWN_ADD_COMMAND = "Only add contact, todo, and event are available";
    public static final String UNKNOWN_SHOW_TAG_COMMAND = "Only add contact, todo, and event are available \n%1$s";
    public static final String UNKNOWN_CLEAR_COMMAND = "Only clear contact is available";
    public static final String UNKNOWN_DELETE_COMMAND = "Only delete contact and task are available";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DISPLAYED_INDEX = "The person/task index provided is invalid "
            + "(Cannot be 0, negative number, or greater than person/task list's index!)";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_TODO_DISPLAYED_INDEX = "The todo index provided is invalid";
    public static final String MESSAGE_INVALID_INDEX_NOT_TODO = "The task at the given index is not a todo";
    public static final String MESSAGE_INVALID_INDEX_NOT_EVENT = "The task at the given index is not an event";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Date should be in DD-MM-YYYY "
            + "and the numbers have to be valid!";
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Time should be in HHmm and the numbers have to be valid!";
    public static final String EXTRA_ARGUMENT_MESSAGE = "Extra parameter found! Please remove parameter: %1$s";
    public static final String EXTRA_SINGULAR_ARGUMENT_MESSAGE =
            "Extra parameter found! Please have only 1 parameter: %1$s";
}
