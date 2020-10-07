package seedu.address.logic.history;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.Undoable;

/**
 * Represents a command history.
 */
public class History {
    private final String commandText;
    private final Undoable command;

    /**
     * @param commandText of the command.
     * @param command the command.
     */
    public History(String commandText, Command command) {
        this.commandText = commandText;

        if (command instanceof Undoable) {
            this.command = (Undoable) command;
        } else {
            this.command = null;
        }
    }

    /**
     * Gets the command text.
     */
    public String getCommandText() {
        return commandText;
    }

    /**
     * Gets the undoable command if it exists.
     * @return An Optional of the undoable command.
     */
    public Optional<Undoable> getCommand() {
        return Optional.ofNullable(command);
    }
}
