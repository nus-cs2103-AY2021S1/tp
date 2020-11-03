package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.PropertyId;
import seedu.address.model.property.Property;

/**
 * Deletes a property identified using it's displayed index from the property book.
 */
public class DeletePropertyCommand extends Command {

    public static final String COMMAND_WORD = "delete-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the property identified by the index number used in the displayed property list.\n"
            + "\n\nParameters: \nINDEX (must be a positive integer)\n"
            + "\n\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROPERTY_SUCCESS = "Deleted Property: %1$s\n"
            + "All related bids and meetings have been deleted.";

    private final Index targetIndex;
    private final PropertyId targetId;

    /**
     * Creates a DeletePropertyCommand to delete the specified {@code targetIndex} or {@code targetId}.
     */
    public DeletePropertyCommand(Index targetIndex, PropertyId propertyId) {
        this.targetIndex = targetIndex;
        this.targetId = propertyId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Property propertyToDelete;
        if (targetId == null) {
            propertyToDelete = deleteByProperty(model);
        } else if (targetIndex == null) {
            propertyToDelete = deleteByPropertyId(model);
        } else {
            throw new AssertionError("Either targetId or targetIndex must be null.");
        }
        return new CommandResult(String.format(
                MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete)).setEntity(EntityType.PROPERTY);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof DeletePropertyCommand)) {
            return false;
        } else {
            DeletePropertyCommand command = (DeletePropertyCommand) other;
            boolean isTargetIdEqual = (this.targetId == null && command.targetId == null)
                    || (this.targetId != null && this.targetId.equals(command.targetId));
            boolean isTargetIndexEqual = (this.targetIndex == null && command.targetIndex == null)
                    || (this.targetIndex != null && this.targetIndex.equals(command.targetIndex));
            return isTargetIdEqual && isTargetIndexEqual;
        }
    }

    private Property deleteByProperty(Model model) throws CommandException {
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProperty(propertyToDelete);
        return propertyToDelete;
    }

    private Property deleteByPropertyId(Model model) {
        Property propertyToDelete = model.getPropertyById(targetId);
        model.deletePropertyByPropertyId(targetId);
        return propertyToDelete;
    }
}
