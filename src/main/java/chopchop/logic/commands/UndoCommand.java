package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

/**
 * Undo the last undoable command.
 */
public class UndoCommand extends Command {

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);
        requireNonNull(historyManager);

        return historyManager.undo(model);
    }

    @Override
    public String toString() {
        return String.format("UndoCommand");
    }

    public static String getCommandString() {
        return "undo";
    }

    public static String getCommandHelp() {
        return "Undoes the last performed command";
    }
}
