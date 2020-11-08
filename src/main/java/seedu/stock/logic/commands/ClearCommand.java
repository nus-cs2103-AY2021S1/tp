package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
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
            + "Format: "
            + COMMAND_WORD;
    private static final Logger logger = LogsCenter.getLogger(ClearCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute clear command");
        requireNonNull(model);
        model.setStockBook(new StockBook());
        model.setSerialNumberSetsBook(new SerialNumberSetsBook());
        logger.log(Level.INFO, "Finished clearing stock book successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand);
    }
}
