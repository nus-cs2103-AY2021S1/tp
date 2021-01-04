package seedu.homerce.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command, please use the help command to see the"
        + " available commands in Homerce.";
    public static final String MESSAGE_NOT_EDITED = "No changes in details detected. "
        + "At least one field to edit must be provided.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_MULTIPLE_PARAMETERS = "Please only input one parameter";

    // ============== Client related messages ===========
    public static final String MESSAGE_CLIENT_INVALID_DELETION = "The client you want to delete is being scheduled"
        + " today or at a future appointment, it cannot be deleted.";
    public static final String MESSAGE_CLIENTS_LISTED_OVERVIEW = "%1$d clients listed!";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid";

    // ============== Service related messages ===========
    public static final String MESSAGE_SERVICES_LISTED_OVERVIEW = "%1$d services listed!";
    public static final String MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX = "The service index provided is invalid";
    public static final String MESSAGE_SERVICES_INVALID_SERVICE_DISPLAYED_INDEX = "The service index "
        + "provided is invalid";
    public static final String MESSAGES_SERVICES_INVALID_DELETION = "The service you want to delete is being scheduled"
        + " today or at a future appointment, it cannot be deleted.";
    public static final String MESSAGE_SERVICE_DUPLICATE_TITLE = "This service title is already used by one of the"
        + " other services";

    // ============== Appointment related messages ===========
    public static final String MESSAGE_APPOINTMENTS_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_APPOINTMENT_ALREADY_DONE = "This appointment is already marked as done!";
    public static final String MESSAGE_APPOINTMENT_ALREADY_UNDONE = "This appointment is already marked as not done!";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX = "The appointment index provided is "
        + "invalid";

    // ============== Revenue related messages ===========
    public static final String MESSAGE_REVENUE_LISTED_OVERVIEW = "%1$d revenue listed!";

    // ============== Expense related messages ===========
    public static final String MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX = "The expense index provided is invalid";
    public static final String MESSAGE_EXPENSES_LISTED_OVERVIEW = "%1$d expenses listed!";
}
