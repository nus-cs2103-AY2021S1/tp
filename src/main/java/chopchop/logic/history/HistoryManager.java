package chopchop.logic.history;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;

/**
 * The HistoryManager of the main LogicManager.
 */
public class HistoryManager {
    public static final String MESSAGE_CANNOT_UNDO = "No commands to undo";
    public static final String MESSAGE_CANNOT_REDO = "No commands to redo";

    private final List<CommandHistory> commandHistory;
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
    public void add(CommandHistory command) {
        this.commandHistory.subList(this.currentIndex, this.commandHistory.size()).clear();
        this.commandHistory.add(command);
        this.currentIndex = this.commandHistory.size();
    }

    /**
     * Undo a command and returns the result. Skips any commands that are not undoable.
     *
     * @param model {@code Model} which the undo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during undo execution.
     */
    public CommandResult undo(Model model) throws CommandException {
        for (var i = this.currentIndex - 1; i >= 0; i--) {
            var command = this.commandHistory.get(i).getCommand();

            if (command.isPresent()) {
                this.currentIndex = i;
                return command.get().undo(model);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_UNDO);
    }

    /**
     * Redo a command and returns the result. Skips any commands that are not redoable.
     *
     * @param model {@code Model} which the redo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during redo execution.
     */
    public CommandResult redo(Model model) throws CommandException {
        while (this.currentIndex < this.commandHistory.size()) {
            var command = this.commandHistory.get(this.currentIndex).getCommand();
            this.currentIndex++;

            if (command.isPresent()) {
                return command.get().redo(model, this);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_REDO);
    }

    /**
     * Returns the command history in reverse chronological order.
     */
    public String getHistory() {
        var sj = new StringJoiner("\n");
        var reversedHistory = this.commandHistory.listIterator(this.currentIndex);

        while (reversedHistory.hasPrevious()) {
            var command = reversedHistory.previous();
            sj.add(command.getCommandText());
        }

        return sj.toString();
    }
}
