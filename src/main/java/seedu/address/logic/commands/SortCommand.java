package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

/**
 * Sorts the current selected menu.
 */
public class SortCommand extends Command {
    public static final String NAME = "n";
    public static final String PRICE = "p";

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current select menu. "
            + "Parameters: "
            + " Sort by Price or Quantity "
            + " [Ascending / Descending]";

    public static final String MESSAGE_SUCCESS = "Menu successfully sorted!";

    private final String sortedBy;
    private final boolean ascending;
    private final boolean toggle;


    /**
     * Creates an SortCommand to sort the current menu with the respective sort type
     */
    public SortCommand(String sortedBy, String ascending) {
        assert sortedBy.equals(SortCommand.NAME) || sortedBy.equals(SortCommand.PRICE);
        assert ascending.equals("a") || ascending.equals("d") || ascending.equals("t");

        this.sortedBy = sortedBy;
        if (ascending.equals("t")) {
            this.toggle = true;
            this.ascending = false;
        } else {
            this.toggle = false;
            this.ascending = ascending.equals("a");
        }
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }
        model.sortFoodBy(sortedBy, ascending, toggle);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
