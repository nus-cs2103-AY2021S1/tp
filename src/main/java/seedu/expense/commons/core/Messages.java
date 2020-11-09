package seedu.expense.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX = "The expense index provided is invalid";
    public static final String MESSAGE_EXPENSES_LISTED_OVERVIEW = "%1$d expenses listed!";
    public static final String MESSAGE_INVALID_CATEGORY = "The \"%s\" category does not exist in the expense book. "
            + "If you need to, please add it using the \"AddCat\" command first.";
    public static final String MESSAGE_INPUT_OVERLIMIT =
        "Input is too long! Maximum characters for %1$s: %2$d";
}
