package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE_DESCRIPTION;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

/**
 * Adds a stock to the stock book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new stock into the inventory.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + PREFIX_NAME_DESCRIPTION + " "
            + PREFIX_SOURCE + PREFIX_SOURCE_DESCRIPTION + " "
            + PREFIX_QUANTITY + PREFIX_QUANTITY_DESCRIPTION + " "
            + PREFIX_LOCATION + PREFIX_LOCATION_DESCRIPTION + " "
            + "[" + PREFIX_LOW_QUANTITY + PREFIX_LOW_QUANTITY_DESCRIPTION + "]\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "Banana cake "
            + PREFIX_SOURCE + "Fairprice "
            + PREFIX_QUANTITY + "100 "
            + PREFIX_LOCATION + "Food section ";

    public static final String MESSAGE_SUCCESS = "New stock added: %1$s";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the stock book";
    private static final Logger logger = LogsCenter.getLogger(AddCommand.class);

    public final Stock toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Stock}
     */
    public AddCommand(Stock stock) {
        requireNonNull(stock);
        toAdd = stock;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "Starting to execute add command");
        requireNonNull(model);

        if (model.hasStock(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STOCK);
        } else {
            toAdd.setSerialNumber(model.generateNextSerialNumber(toAdd.getSource()));
            model.addStock(toAdd);
            logger.log(Level.INFO, "Finished adding stock successfully");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
