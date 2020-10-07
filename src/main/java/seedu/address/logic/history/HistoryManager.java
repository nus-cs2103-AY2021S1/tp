package seedu.address.logic.history;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.StringJoiner;

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

    private final List<History> history;
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
    public void add(History command) {
        history.subList(index, history.size()).clear();
        history.add(command);
        index = history.size();
    }

    /**
     * Undo a command and returns the result. Skips any commands that are not undoable.
     * @param model {@code Model} which the undo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during undo execution.
     */
    public CommandResult undo(Model model) throws CommandException {
        int i;

        for (i = index - 1; i >= 0; i--) {
            Optional<Undoable> command = history.get(i).getCommand();

            if (command.isPresent()) {
                index = i;
                return command.get().undo(model);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_UNDO);
    }

    /**
     * Redo a command and returns the result. Skips any commands that are not redoable.
     * @param model {@code Model} which the redo should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during redo execution.
     */
    public CommandResult redo(Model model) throws CommandException {
        while (index < history.size()) {
            Optional<Undoable> command = history.get(index).getCommand();
            index++;

            if (command.isPresent()) {
                return command.get().redo(model, this);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_REDO);
    }

    public String getHistory() {
        StringJoiner sj = new StringJoiner("\n");
        ListIterator<History> reversedHistory = history.listIterator(index);

        while (reversedHistory.hasPrevious()) {
            History command = reversedHistory.previous();
            sj.add(command.getCommandText());
        }

        return sj.toString();
    }
}
