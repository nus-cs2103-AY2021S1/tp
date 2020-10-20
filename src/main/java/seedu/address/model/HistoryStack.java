package seedu.address.model;

import java.util.Arrays;
import java.util.Stack;

/**
 * Encapsulates a stack containing various states of the ZooKeepBook.
 * This is a singleton.
 */
public class HistoryStack {

    private static HistoryStack history;

    private final Stack<ReadOnlyZooKeepBook> historyStack;

    private HistoryStack() {
        historyStack = new Stack<>();
    }

    public static HistoryStack getHistoryStack() {
        if (history == null) {
            history = new HistoryStack();
        }
        return history;
    }

    /**
     * Push a read only state of ZooKeepBook into the stack
     * @param book the ZooKeepBook to be pushed
     */
    public void addToHistory(ReadOnlyZooKeepBook book) {
        if (getSize() == 0 || !viewRecentHistory().equals(book)) {
            historyStack.push(book);
        }
    }

    public void removeRecentHistory() {
        historyStack.pop();
    }

    public ReadOnlyZooKeepBook viewRecentHistory() {
        return historyStack.peek();
    }

    public int getSize() {
        return historyStack.size();
    }

    public void clearHistory() {
        historyStack.clear();
    }

    @Override
    public String toString() {
        return Arrays.toString(historyStack.toArray());
    }
}
