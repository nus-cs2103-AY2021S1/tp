package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HistoryStackTest {

    @Test
    public void execute_get_historyStack() {
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        assertEquals(historyStack, HistoryStack.getHistoryStack());
    }

    @Test
    public void execute_addToHistory_success() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.addToHistory(book);
        assertEquals(historyStack.toString(), "[0 animals]");
    }

    @Test
    public void execute_addToHistory_duplicate() {
        ZooKeepBook bookA = new ZooKeepBook();
        ZooKeepBook bookB = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.addToHistory(bookA);
        historyStack.addToHistory(bookB);
        assertEquals(historyStack.toString(), "[0 animals]");
    }

    @Test
    public void execute_removeRecentHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.addToHistory(book);
        historyStack.removeRecentHistory();
        assertEquals(historyStack.toString(), "[]");
    }

    @Test
    public void execute_viewRecentHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.addToHistory(book);
        assertEquals(historyStack.viewRecentHistory().toString(), "0 animals");
    }

    @Test
    public void execute_getSize() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.addToHistory(book);
        assertEquals(historyStack.getSize(), 1);
    }

    @Test
    public void execute_clearHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        assertEquals(historyStack.toString(), "[]");
    }
}
