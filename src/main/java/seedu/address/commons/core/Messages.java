package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid";
    public static final String MESSAGE_ITEMS_LISTED_OVERVIEW = "%1$d items listed!";
    public static final String HELP_START = getHelpStart();
    public static final String HELP_SUMMARY = getHelpSummary();

    public static final String getHelpStart() {
        StringBuilder summary = new StringBuilder();
        summary.append("Getting Started\n")
                .append("WELCOME WELCOME WELCOME")
                .append("===========================================================================\n")
                .append("I. User Profile\n")
                .append("===========================================================================\n")
                .append("First...");
        return summary.toString();
    }

    public static final String getHelpSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Getting Started\n")
                .append("WELCOME WELCOME WELCOME")
                .append("===========================================================================\n")
                .append("I. User Profile\n")
                .append("===========================================================================\n")
                .append("Second...");
        return summary.toString();
    }
}
