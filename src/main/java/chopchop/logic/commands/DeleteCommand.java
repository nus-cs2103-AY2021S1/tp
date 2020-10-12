// DeleteCommand.java

package chopchop.logic.commands;

import chopchop.model.Model;
import chopchop.logic.parser.ItemReference;
import chopchop.logic.commands.exceptions.CommandException;

/**
 * Deletes an item
 */
public abstract class DeleteCommand extends Command {

    protected final ItemReference item;

    public DeleteCommand(ItemReference item) {
        this.item = item;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

}
