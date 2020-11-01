package seedu.zookeep.model;

import java.util.Arrays;
import java.util.Stack;

/**
 * Encapsulates 2 stacks, with one containing past states of the ZooKeepBook while the other contains the
 * future states of the ZooKeepBook.
 * This is a singleton.
 */
public class HistoryStack {

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
     * Push a read only state of ZooKeepBook into the stack
     * @param book the ZooKeepBook to be pushed
     */
    public void addToHistory(ReadOnlyZooKeepBook book) {
        if (getHistorySize() == 0 || !viewRecentHistory().equals(book)) {
            historyStack.push(book);
        }
    }

    public void addToRedo(ReadOnlyZooKeepBook book) {
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

    public void clearHistory() {
        historyStack.clear();
    }

    public void clearRedo() {
        redoStack.clear();
    }

    @Override
    public String toString() {
        return "Undo stack: " + Arrays.toString(historyStack.toArray()) + "\n"
                + "Redo stack: " + Arrays.toString(redoStack.toArray());
    }
}
