package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.stock.commons.core.Messages;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Deletes a stock identified using it's displayed serial number from the stock book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the stock identified by the serial number used in the displayed.\n"
            + "Parameters: SERIAL NUMBER (must be a valid serial number)\n"
            + "Example: " + COMMAND_WORD + " sn/Kc company1";

    public static final String MESSAGE_DELETE_STOCK_SUCCESS = "Deleted Stock: %1$s";

    private final SerialNumber targetSerialNumber;

    public DeleteCommand(SerialNumber targetSerialNumber) {
        this.targetSerialNumber = targetSerialNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stock> lastShownList = model.getFilteredStockList();
        for (int i = 0; i < lastShownList.size(); i++) {
            Stock currentStock = lastShownList.get(i);
            if (currentStock.getSerialNumber().getSerialNumberAsString().
                    equals(targetSerialNumber.getSerialNumberAsString())) {
                model.deleteStock(currentStock);
                return new CommandResult(String.format(MESSAGE_DELETE_STOCK_SUCCESS,currentStock));
            }
        }

        throw new CommandException(Messages.MESSAGE_SERIAL_NUMBER_NOT_FOUND);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetSerialNumber.equals(((DeleteCommand) other).targetSerialNumber)); // state check
    }
}
