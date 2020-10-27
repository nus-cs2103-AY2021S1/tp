package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.ui.UiManager;

/**
 * Adds a property to the property book.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to the property book. "
            + "\n\nParameters: "
            + "\n" + PREFIX_PROPERTY_NAME + "PROPERTY NAME "
            + "\n" + PREFIX_PROPERTY_ADDRESS + "ADDRESS "
            + "\n" + PREFIX_PROPERTY_SELLER_ID + "SELLER ID "
            + "\n" + PREFIX_PROPERTY_ASKING_PRICE + "ASKING PRICE "
            + "\n" + PREFIX_PROPERTY_TYPE + "PROPERTY TYPE "
            + "\n" + PREFIX_PROPERTY_IS_RENTAL + "IS RENTAL?"
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_PROPERTY_NAME + "Harsha Vista "
            + PREFIX_PROPERTY_ADDRESS + "25 Marcus Street "
            + PREFIX_PROPERTY_SELLER_ID + "S2 "
            + PREFIX_PROPERTY_ASKING_PRICE + "1792.50 "
            + PREFIX_PROPERTY_TYPE + "Landed "
            + PREFIX_PROPERTY_IS_RENTAL + "No";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the property book";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);

    private final Property toAdd;

    /**
     * Creates an AddPropertyCommand to add the specified {@code Property}
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        toAdd = property;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.addProperty(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)).setEntity(EntityType.PROPERTY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && toAdd.equals(((AddPropertyCommand) other).toAdd));
    }
}
