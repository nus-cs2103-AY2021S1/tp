package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Id;
import seedu.address.model.Model;

/**
 * Deletes an entity identified using it's displayed index from the address book.
 */
public abstract class DeleteEntityCommand<T extends Id> extends DeleteCommand {

    protected final T targetId;

    public DeleteEntityCommand(T targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other != null && other.getClass() == this.getClass()
                && targetId.equals(((DeleteEntityCommand) other).targetId)); // state check
    }

    public abstract String deleteFromModel(Model model) throws CommandException;
}
