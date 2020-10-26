package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;

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

    /**
     * Creates an SortCommand to sort the current menu
     */
    public SortCommand(String sortedBy, String ascending) {
        this.sortedBy = sortedBy;
        if (ascending.equals("t")) {
            this.ascending = true;
        } else {
            this.ascending = false;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        switch (sortedBy) {
        case NAME:
            model.sortFoodByName(ascending);
            break;
        case PRICE:
            model.sortFoodByPrice(ascending);
            break;
        default:
            throw new CommandException(ParserUtil.MESSAGE_UNKNOWN_COMMAND);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
