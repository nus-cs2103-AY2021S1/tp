package seedu.address.logic.commands.sellercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.seller.Seller;

public class AddSellerCommand extends Command {

    public static final String COMMAND_WORD = "add-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a seller to the seller address book. "
            + "\n\nParameters: "
            + "\n" + PREFIX_NAME + "NAME "
            + "\n" + PREFIX_PHONE + "PHONE "
            + "\n" + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 ";

    public static final String MESSAGE_SUCCESS = "New seller added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This seller already exists in the seller address book";

    private final Seller seller;

    /**
     * Creates an AddSellerCommand to add the specified {@code Seller}
     */
    public AddSellerCommand(Seller seller) {
        requireNonNull(seller);
        this.seller = seller;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSeller(seller)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addSeller(seller);
        return new CommandResult(String.format(MESSAGE_SUCCESS, seller)).setEntity(EntityType.SELLER);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSellerCommand // instanceof handles nulls
                && seller.equals(((AddSellerCommand) other).seller));
    }
}
