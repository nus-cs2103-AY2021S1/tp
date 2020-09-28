package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

/**
 * Adds a person to the stock book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a stock to the stock book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SOURCE + "SOURCE "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_LOCATION + "LOCATION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Umbrella "
            + PREFIX_SOURCE + "Kc company "
            + PREFIX_QUANTITY + "100 "
            + PREFIX_LOCATION + "section B ";

    public static final String MESSAGE_SUCCESS = "New stock added: %1$s";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the stock book";

    private final Stock toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Stock}
     */
    public AddCommand(Stock stock) {
        requireNonNull(stock);
        toAdd = stock;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStock(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STOCK);
        }

        model.addStock(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
