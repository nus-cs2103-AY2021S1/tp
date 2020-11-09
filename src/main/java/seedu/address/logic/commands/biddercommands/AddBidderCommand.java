package seedu.address.logic.commands.biddercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.bidder.Bidder;

public class AddBidderCommand extends Command {

    public static final String COMMAND_WORD = "add-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a bidder to the bidder address book. "
            + "\n\nParameters: "
            + "\n" + PREFIX_NAME + "NAME "
            + "\n" + PREFIX_PHONE + "PHONE "
            + "\n" + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 ";

    public static final String MESSAGE_SUCCESS = "New bidder added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This bidder already exists in the bidder address book";

    private final Bidder bidder;

    /**
     * Creates an AddBidderCommand to add the specified {@code Bidder}
     */
    public AddBidderCommand(Bidder bidder) {
        requireNonNull(bidder);
        this.bidder = bidder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBidder(bidder)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addBidder(bidder);
        return new CommandResult(String.format(MESSAGE_SUCCESS, bidder)).setEntity(EntityType.BIDDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBidderCommand // instanceof handles nulls
                && bidder.equals(((AddBidderCommand) other).bidder));
    }
}
