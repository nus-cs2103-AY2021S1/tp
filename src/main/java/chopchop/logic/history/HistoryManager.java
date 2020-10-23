package chopchop.logic.history;

import java.util.ArrayList;
import java.util.List;

import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.Undoable;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;

/**
 * The HistoryManager of the main LogicManager.
 */
public class HistoryManager {
    public static final String MESSAGE_CANNOT_UNDO = "No commands to undo";
    public static final String MESSAGE_CANNOT_REDO = "No commands to redo";

    private final List<Undoable> commandHistory;
    private int currentIndex;

    /**
     * Constructs a {@code HistoryManager}.
     */
    public HistoryManager() {
        this.commandHistory = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Adds an undoable command to the history.
     */
    public void add(Undoable command) {
        this.commandHistory.subList(this.currentIndex, this.commandHistory.size()).clear();
        this.commandHistory.add(command);
        this.currentIndex = this.commandHistory.size();
    }

    /**
     * Undo a command and returns the result.
     *
     * @param model {@code Model} which the undo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during undo execution.
     */
    public CommandResult undo(Model model) throws CommandException {
        if (this.currentIndex == 0) {
            throw new CommandException(MESSAGE_CANNOT_UNDO);
        }

        this.currentIndex--;
        return this.commandHistory.get(this.currentIndex).undo(model);
    }

    /**
     * Redo a command and returns the result.
     *
     * @param model {@code Model} which the redo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during redo execution.
     */
    public CommandResult redo(Model model) throws CommandException {
        if (this.currentIndex == this.commandHistory.size()) {
            throw new CommandException(MESSAGE_CANNOT_REDO);
        }

        CommandResult result = this.commandHistory.get(this.currentIndex).redo(model, this);
        this.currentIndex++;
        return result;
    }
}
