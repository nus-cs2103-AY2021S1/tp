package seedu.zookeep.model;

import java.util.Arrays;
import java.util.Stack;
import java.util.logging.Logger;

import seedu.zookeep.commons.core.LogsCenter;

/**
 * Encapsulates 2 stacks, with one containing past states of the ZooKeepBook while the other contains the
 * future states of the ZooKeepBook.
 * This is a singleton.
 */
public class HistoryStack {

    private static final Logger logger = LogsCenter.getLogger(HistoryStack.class);
    private static HistoryStack history;

    private final Stack<ReadOnlyZooKeepBook> historyStack;
    private final Stack<ReadOnlyZooKeepBook> redoStack;

    private HistoryStack() {
        historyStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public static HistoryStack getHistoryStack() {
        if (history == null) {
            history = new HistoryStack();
        }
        return history;
    }

    /**
     * Checks if the 2 given ZooKeepBooks have the same content.
     * @param before the state of the ZooKeepBook before an unknown command was executed
     * @param after the state of the ZooKeepBook after command execution
     */
    public void checkEdit(ReadOnlyZooKeepBook before, ReadOnlyZooKeepBook after) {
        if (!before.equals(after)) {
            clearRedo();
        }
    }

    /**
     * Pushes a read only state of ZooKeepBook into the undo stack.
     * @param book the ZooKeepBook to be pushed
     */
    public void addToHistory(ReadOnlyZooKeepBook book) {
        if (getHistorySize() == 0 || !viewRecentHistory().equals(book)) {
            logger.info("Adding new ZooKeepBook state to undo stack: " + book);
            historyStack.push(book);
        }
    }

    /**
     * Pushes a read only state of ZooKeepBook into the redo stack.
     * @param book the ZooKeepBook to be pushed
     */
    public void addToRedo(ReadOnlyZooKeepBook book) {
        logger.info("Adding new ZooKeepBook state to redo stack: " + book);
        redoStack.push(book);
    }

    public void removeRecentHistory() {
        historyStack.pop();
    }

    public void removeRecentRedo() {
        redoStack.pop();
    }

    public ReadOnlyZooKeepBook viewRecentHistory() {
        return historyStack.peek();
    }

    public ReadOnlyZooKeepBook viewRecentRedo() {
        return redoStack.peek();
    }

    public int getHistorySize() {
        return historyStack.size();
    }

    public int getRedoSize() {
        return redoStack.size();
    }

    /**
     * Clears the undo stack.
     */
    public void clearHistory() {
        logger.info("Clearing the undo stack.");
        historyStack.clear();
    }

    /**
     * Clears the redo stack.
     */
    public void clearRedo() {
        logger.info("Clearing the redo stack.");
        redoStack.clear();
    }

    @Override
    public String toString() {
        return "Undo stack: " + Arrays.toString(historyStack.toArray()) + "\n"
                + "Redo stack: " + Arrays.toString(redoStack.toArray());
    }
}
