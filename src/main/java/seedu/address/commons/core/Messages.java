package seedu.address.commons.core;

/**
 * Container for user visible error messages.
 */
public class Messages {
    // invalid user inputs
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! %1$s\n%2$s";
    public static final String MESSAGE_INVALID_DISPLAYED_INDEX_INFER = "Perhaps you made a typo?";
    public static final String MESSAGE_INVALID_DISPLAYED_INDEX = "The %1$s index provided does not exist in the "
            + "%1$s list. " + MESSAGE_INVALID_DISPLAYED_INDEX_INFER;
    public static final String MESSAGE_INVALID_DISPLAYED_INDEXES = "One or more %1$s indexes do not exist in the "
            + "%1$s list. " + MESSAGE_INVALID_DISPLAYED_INDEX_INFER;

    // user input duplications
    public static final String MESSAGE_DUPLICATE_INDEX = "Please do not include duplicate indexes.";
    public static final String MESSAGE_DUPLICATE_MODEL = "This %1$s already exists in PlaNus.";
    public static final String MESSAGE_DUPLICATE_LESSON = String.format(MESSAGE_DUPLICATE_MODEL, "lesson");
    public static final String MESSAGE_DUPLICATE_EVENT = String.format(MESSAGE_DUPLICATE_MODEL, "event");
    public static final String MESSAGE_DUPLICATE_DEADLINE = String.format(MESSAGE_DUPLICATE_MODEL, "deadline");
    public static final String MESSAGE_DUPLICATE_TASK = String.format(MESSAGE_DUPLICATE_MODEL, "task");

    // invalid user search phrases
    public static final String MESSAGE_EMPTY_SEARCH_PHRASE = "Search phrase cannot be empty!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEX, "task");
    public static final String MESSAGE_INVALID_TASKS_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEXES, "task");
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEX, "lesson");
    public static final String MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX =
            String.format(MESSAGE_INVALID_DISPLAYED_INDEXES, "lesson");

    // mark as done error messages
    public static final String MESSAGE_INCORRECT_TASK_STATUS = "One or more targeted deadline is already completed.\n"
            + "Please check your command carefully.";
    public static final String MESSAGE_INVALID_DONE_TASK_TYPE = "You can only mark a deadline as done.\n"
            + "One or more task selected is not in deadline type";
    public static final String INVALID_DURATION_FORMAT = "The duration must be a positive integer";
    public static final String INVALID_DONE_INDEX_FORMAT = "Index for done command must be in the form: "
            + "INDEX:DURATION";

    // edit error messages
    public static final String MESSAGE_INVALID_EVENT_EDIT_TYPE = "You cannot edit system generated lesson event";
    public static final String MESSAGE_INVALID_EDIT_TYPE = "You cannot edit this task with the given attributes";
    public static final String MESSAGE_INVALID_DEADLINE_EDIT_STATUS = "You cannot edit a deadline after marked as done";

    // misc error messages
    public static final String MESSAGE_START_BEFORE_END = "start time is cannot be before end time!";
    public static final String MESSAGE_MULTIPLE_ATTRIBUTES = "Please do not include more than one of each attribute.";
    public static final String OVERLAP_CONSTRAINTS =
            "This %1$s overlaps with another existing event or lesson in PlaNus.";
    public static final String LESSON_OVERLAP_CONSTRAINTS = String.format(OVERLAP_CONSTRAINTS, "lesson");
    public static final String EVENT_OVERLAP_CONSTRAINTS = String.format(OVERLAP_CONSTRAINTS, "event");
}
