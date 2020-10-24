package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid";
    public static final String MESSAGE_UNDO_LIMIT_REACHED = "No more commands to undo";
    public static final String MESSAGE_REDO_LIMIT_REACHED = "No more commands to redo";
    public static final String MESSAGE_ITEMS_LISTED_OVERVIEW = "%1$d items listed!";
    public static final String MESSAGE_DELIVERIES_LISTED_OVERVIEW = "%1$d deliveries listed!";
    public static final String MESSAGE_EARLY_TEST_FAILURE = "Test should not have failed at this point!";
    public static final String MESSAGE_HELP_ON_START = "Press F1 to view the user guide,\n"
            + "Press F2 to get started with some commands!";
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
                .append("Undo previous command: undo\n")
                .append("Redo undone command: redo\n")
                .append("Exit application: exit\n")
                .append(DIVIDER)
                .append("INVENTORY SPECIFIC COMMANDS\n")
                .append(DIVIDER)
                .append("Add inventory: add-i n/NAME q/QUANTITY [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]\n")
                .append("Delete an inventory item: delete-i INDEX\n")
                .append("Edit an inventory item: edit-i INDEX [n/NAME] [q/QUANTITY]"
                        + " [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]\n")
                .append("Find an inventory item: find-i KEYWORD [MORE_KEYWORDS]\n")
                .append("Clear all inventory items: clear-i\n")
                .append("List all inventory items: list-i\n")
                .append("DELIVERY SPECIFIC COMMANDS\n")
                .append(DIVIDER)
                .append("Add delivery: add-d n/NAME p/PHONE a/ADDRESS o/ORDER\n")
                .append("Delete a delivery: delete-d INDEX\n")
                .append("Edit a delivery: edit-d INDEX [n/NAME] [p/PHONE] [a/ADDRESS] [o/ORDER]\n")
                .append("Find a delivery: find-d KEYWORD [MORE_KEYWORDS]\n")
                .append("Clear all deliveries: clear-d\n")
                .append("List all deliveries: list-d\n");
        return summary.toString();
    }
}
