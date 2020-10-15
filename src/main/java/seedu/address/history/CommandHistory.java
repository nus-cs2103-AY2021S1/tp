package seedu.address.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.history.exception.HistoryException;

public class CommandHistory implements History {
    private static final String MESSAGE_LENGTH_LIMIT_AT_LEAST_ONE = "Length of Command History must atleast be 1";

    private List<String> commandHistory;
    private int currentCommandIndex;
    private final int lengthLimit;

    /**
     * Constructor for CommandHistory
     * @param lengthLimit length limit of command history. Must be strictly more than 0.
     * @throws HistoryException if lengthLimit is less than or equals to 0.
     */
    public CommandHistory(int lengthLimit) throws HistoryException {
        if (lengthLimit > 0) {
            this.commandHistory = new ArrayList<>(lengthLimit);

            // length limit is one-indexed similar to commandHistory.size()
            this.lengthLimit = lengthLimit;

            // since commandHistory is zero-indexed, initializing currentCommandIndex to -1 solves the zero-index issue
            this.currentCommandIndex = -1;
        } else {
            throw new HistoryException(MESSAGE_LENGTH_LIMIT_AT_LEAST_ONE);
        }
    }

    public int getCurrentCommandIndex() {
        return currentCommandIndex;
    }

    public int getLengthLimit() {
        return lengthLimit;
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    private boolean isLimitReached() {
        return commandHistory.size() == lengthLimit;
    }

    private boolean isWithinLengthLimit() {
        return currentCommandIndex >= 0 && currentCommandIndex < lengthLimit;
    }

    private boolean isAbleToReturnNextCommand() {
        return currentCommandIndex < commandHistory.size() - 1 && isWithinLengthLimit();
    }

    private boolean isAbleToReturnPreviousCommand() {
        return currentCommandIndex > 0 && isWithinLengthLimit();
    }

    public boolean hasNextCommand() {
        return currentCommandIndex < commandHistory.size() - 1;
    }

    @Override
    public void addToHistory(String command) {
        if (hasNextCommand()) {
            // if there exist a next command,
            // copy commandHistory[0] to commandHistory[currentCommandIndex],
            // store it as the new commandHistory
            // and append the new command.
            commandHistory = commandHistory.subList(0, currentCommandIndex + 1);
            this.currentCommandIndex++;
            this.commandHistory.add(command);
        } else if (isLimitReached()) {
            this.commandHistory.remove(0);
            this.commandHistory.add(command);
        } else {
            // when there is sufficient space for nextCommand to be added to history
            this.currentCommandIndex++;
            this.commandHistory.add(command);
        }
    }

    @Override
    public Optional<String> previousCommand() {
        if (isAbleToReturnPreviousCommand()) {
            currentCommandIndex--;
            return Optional.of(commandHistory.get(currentCommandIndex));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> nextCommand() {
        if (isAbleToReturnNextCommand()) {
            currentCommandIndex++;
            return Optional.of(commandHistory.get(currentCommandIndex));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> currentCommand() {
        if (isWithinLengthLimit()) {
            return Optional.of(commandHistory.get(currentCommandIndex));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandHistory, currentCommandIndex, lengthLimit);
    }

    /**
     * Returns true if both command history have the same commandHistory, currentCommandIndex and length limit.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof CommandHistory) {
            CommandHistory c = (CommandHistory) o;
            return c.getCommandHistory().equals(this.commandHistory)
                    && c.getCurrentCommandIndex() == this.currentCommandIndex
                    && c.getLengthLimit() == this.lengthLimit;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return commandHistory.toString()
                + " || Length Limit: " + lengthLimit
                + " || Current Index: " + currentCommandIndex;
    }
}
