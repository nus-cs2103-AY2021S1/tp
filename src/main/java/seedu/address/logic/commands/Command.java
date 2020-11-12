package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Checks if some indexes supplied in a command are invalid.
     *
     * @param targetIndexes the list of all indexes supplied.
     * @param lastShownList the list to check against.
     * @throws CommandException CommandException with customised error message.
     */
    static <T> List<T> checkIndexValidity(Index[] targetIndexes, List<T> lastShownList, String error)
            throws CommandException {
        if (Index.hasDuplicateIndex(targetIndexes)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_INDEX);
        }
        List<T> toOperate = new ArrayList<T>();
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(error);
            }
            toOperate.add(lastShownList.get(targetIndex.getZeroBased()));
        }
        return toOperate;
    }
}
