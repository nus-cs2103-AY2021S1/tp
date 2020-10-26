package seedu.address.logic.commands.bidcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bid.Bid;

public class AddBidCommand extends Command {

    public static final String COMMAND_WORD = "add-bid";

    public static final String MESSAGE_SUCCESS = "New bid added: %1$s";

    public static final String MESSAGE_DUPLICATE_BID = "This bid already exists in the bid book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a bid to the bid book. "
            + "\n\nParameters: "
            + "\n" + PREFIX_PROPERTY_ID + "PROPERTY ID "
            + "\n" + PREFIX_CLIENT + "CLIENT "
            + "\n" + PREFIX_MONEY + "MONEY "
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_PROPERTY_ID + "P12 "
            + PREFIX_CLIENT + "B24 "
            + PREFIX_MONEY + "500000";

    private final Bid bid;

    /**
     * constructor for a AddBidCommand object
     * @param bid
     */
    public AddBidCommand(Bid bid) {
        requireNonNull(bid);
        this.bid = bid;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBid(bid)) {
            throw new CommandException(MESSAGE_DUPLICATE_BID);
        }

        model.addBid(bid);
        return new CommandResult(String.format(MESSAGE_SUCCESS, bid)).setEntity(EntityType.BID);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBidCommand // instanceof handles nulls
                && bid.equals(((AddBidCommand) other).bid));
    }
}
