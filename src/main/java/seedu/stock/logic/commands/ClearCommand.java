package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.stock.model.Model;
import seedu.stock.model.StockBook;

/**
 * Clears the stock book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Stock book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStockBook(new StockBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
