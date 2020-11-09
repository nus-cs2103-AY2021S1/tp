package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.model.Model;

/**
 * Lists all properties in the property book to the user.
 */
public class ListPropertyCommand extends Command {

    public static final String COMMAND_WORD = "list-p";

    public static final String MESSAGE_SUCCESS = "Displaying full property list.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(MESSAGE_SUCCESS).setEntity(EntityType.PROPERTY);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ListPropertyCommand;
    }
}
