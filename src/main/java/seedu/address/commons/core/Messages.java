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
    public static final String DIVIDER = "======================================================================\n";

    public static final String getHelpStart() {
        StringBuilder summary = new StringBuilder();
        summary.append("Getting Started\n")
                .append("Welcome to OneShelf!\nYou have summoned me - your trusty Assistant, probably because you are "
                        + "lost and do not know where to start.\nBut don't worry, because that is the whole point of "
                        + "my existence!\n\n")
                .append(DIVIDER)
                .append("Storing inventory items\n")
                .append(DIVIDER)
                .append("First, let's kick-start your quest by storing inventory items so that you can keep track \n"
                        + "of your restaurant stocks. ")
                .append("TO BE CONTINUED ONCE MORE FEATURES ARE UP (V1.3-V1.4)");
        return summary.toString();
    }

    public static final String getHelpSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Command Summary\n")
                .append(DIVIDER)
                .append("GENERAL COMMANDS\n")
                .append(DIVIDER)
                .append("Open up help options: help summary OR help start\n")
                .append("Clear all data: clear\n")
                .append("List all data: list\n")
                .append("Exit application: exit\n")
                .append(DIVIDER)
                .append("INVENTORY SPECIFIC COMMANDS\n")
                .append(DIVIDER)
                .append("Add inventory: add n/NAME q/QUANTITY [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]\n")
                .append("Delete an inventory item: delete INDEX\n")
                .append("Edit an inventory item: edit INDEX [n/NAME] [q/QUANTITY]" +
                        " [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]\n")
                .append("Find an inventory item: find KEYWORD [MORE_KEYWORDS]\n");
        return summary.toString();
    }
}
