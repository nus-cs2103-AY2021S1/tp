package chopchop.logic.commands;

import chopchop.commons.core.index.Index;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;


/**
 * Deletes a recipe identified using it's displayed index from the recipe book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    protected final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

}
