package seedu.stock.logic.commands;

/**
 * Lists all persons in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all stocks in the inventory \n"
            + "list lt/all : Lists all stocks in the inventory\n"
            + "list lt/bookmark : Lists all bookmarked stocks in the inventory\n"
            + "list lt/low : List all stocks in the inventory that are low in quantity\n"
            + "Parameters: no parameters\n";

}
