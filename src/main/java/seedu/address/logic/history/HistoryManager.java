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
public class HistoryManager implements History {
    public static final String MESSAGE_CANNOT_UNDO = "No commands to undo";
    public static final String MESSAGE_CANNOT_REDO = "No commands to redo";

    private final List<CommandHistory> commandHistory;
    private int index;

    /**
     * Constructs a {@code HistoryManager}.
     */
    public HistoryManager() {
        commandHistory = new ArrayList<>();
        index = 0;
    }

    @Override
    public void add(CommandHistory command) {
        commandHistory.subList(index, commandHistory.size()).clear();
        commandHistory.add(command);
        index = commandHistory.size();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        int i;

        for (i = index - 1; i >= 0; i--) {
            Optional<Undoable> command = commandHistory.get(i).getCommand();

            if (command.isPresent()) {
                index = i;
                return command.get().undo(model);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_UNDO);
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        while (index < commandHistory.size()) {
            Optional<Undoable> command = commandHistory.get(index).getCommand();
            index++;

            if (command.isPresent()) {
                return command.get().redo(model, this);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_REDO);
    }

    @Override
    public String getHistory() {
        StringJoiner sj = new StringJoiner("\n");
        ListIterator<CommandHistory> reversedHistory = commandHistory.listIterator(index);

        while (reversedHistory.hasPrevious()) {
            CommandHistory command = reversedHistory.previous();
            sj.add(command.getCommandText());
        }

        return sj.toString();
    }
}
