package seedu.address.logic;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.Undoable;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The HistoryManager of the main LogicManager.
 */
public class HistoryManager {
    public static final String MESSAGE_CANNOT_UNDO = "No commands to undo";
    public static final String MESSAGE_CANNOT_REDO = "No commands to redo";

    private final List<Undoable> history;
    private int index;

    /**
     * Constructs a {@code HistoryManager}.
     */
    public HistoryManager() {
        history = new ArrayList<>();
        index = 0;
    }

    /**
     * Adds an undoable command to the history.
     */
    public void add(Undoable command) {
        history.subList(index, history.size()).clear();
        history.add(index, command);
        index++;
    }

    /**
     * Undo a command and returns the result.
     * @param model {@code Model} which the undo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during undo execution.
     */
    public CommandResult undo(Model model) throws CommandException {
        if (index == 0) {
            throw new CommandException(MESSAGE_CANNOT_UNDO);
        }

        index--;
        Undoable command = history.get(index);
        return command.undo(model);
    }

    /**
     * Redo a command and returns the result.
     * @param model {@code Model} which the redo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during redo execution.
     */
    public CommandResult redo(Model model) throws CommandException {
        if (index == history.size()) {
            throw new CommandException(MESSAGE_CANNOT_REDO);
        }

        Undoable command = history.get(index);
        index++;
        return command.redo(model, this);
    }
}
