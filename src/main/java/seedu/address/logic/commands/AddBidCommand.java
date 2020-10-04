package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bid.Bid;

import static java.util.Objects.requireNonNull;

public class AddBidCommand extends Command {

    public static final String COMMAND_WORD = "add-bid";
    private final Bid bid;
    public static final String MESSAGE_SUCCESS = "New bid added: %1$s";

   public AddBidCommand(Bid bid) {
        this.bid = bid;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        model.addBid(bid);
        return new CommandResult(String.format(MESSAGE_SUCCESS, bid));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBidCommand // instanceof handles nulls
                && bid.equals(((AddBidCommand) other).bid));
    }
}
