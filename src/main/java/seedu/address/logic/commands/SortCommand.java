package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

/**
 * Sorts the current selected menu.
 */
public class SortCommand extends Command {
    public static final String NAME = "n";
    public static final String PRICE = "p";

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the menu by either price or name.\n"
            + "Format: sort MODE [DIRECTION]\n"
            + "- MODE can either be \"n\" or \"p\" (without quotes), indicating sort by name or price respectively.\n"
            + "- DIRECTION can either be \"a\" or \"d\" (without quotes), indicating sort in ascending or descending "
                + "order.\n"
            + "- If DIRECTION is not specified or if direction is \"t\" (without quotes), "
            + "it will be treated as a toggle,"
            + " and ascending direction will be sorted as descending order and vice versa.\n"
            + "Examples:\n"
            + "sort n a: sorts the menu by name in ascending direction.\n"
            + "sort p: sorts the menu by price in opposite direction as last sorted.";

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
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }
        model.sortMenuItemBy(sortedBy, ascending, toggle);
        return new CommandResult(Messages.MESSAGE_FOOD_SORTED, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortedBy.equals(((SortCommand) other).sortedBy))
                && ascending == ((SortCommand) other).ascending
                && toggle == ((SortCommand) other).toggle;
    }
}
