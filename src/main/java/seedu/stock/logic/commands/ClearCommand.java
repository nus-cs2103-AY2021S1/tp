package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.stock.model.Model;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;

/**
 * Clears the database in Warenager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all the existing data in Warenager.\n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStockBook(new StockBook());
        model.setSerialNumberSetsBook(new SerialNumberSetsBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand);
    }
}
