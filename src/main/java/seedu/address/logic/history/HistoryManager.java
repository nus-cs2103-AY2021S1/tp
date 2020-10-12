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
    private int currentIndex;

    /**
     * Constructs a {@code HistoryManager}.
     */
    public HistoryManager() {
        commandHistory = new ArrayList<>();
        currentIndex = 0;
    }

    @Override
    public void add(CommandHistory command) {
        commandHistory.subList(currentIndex, commandHistory.size()).clear();
        commandHistory.add(command);
        currentIndex = commandHistory.size();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        for (int i = currentIndex - 1; i >= 0; i--) {
            Optional<Undoable> command = commandHistory.get(i).getCommand();

            if (command.isPresent()) {
                currentIndex = i;
                return command.get().undo(model);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_UNDO);
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        while (currentIndex < commandHistory.size()) {
            Optional<Undoable> command = commandHistory.get(currentIndex).getCommand();
            currentIndex++;

            if (command.isPresent()) {
                return command.get().redo(model, this);
            }
        }

        throw new CommandException(MESSAGE_CANNOT_REDO);
    }

    @Override
    public String getHistory() {
        StringJoiner sj = new StringJoiner("\n");
        ListIterator<CommandHistory> reversedHistory = commandHistory.listIterator(currentIndex);

        while (reversedHistory.hasPrevious()) {
            CommandHistory command = reversedHistory.previous();
            sj.add(command.getCommandText());
        }

        return sj.toString();
    }
}
