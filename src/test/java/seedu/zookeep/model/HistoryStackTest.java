package seedu.zookeep.model;

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
        historyStack.clearRedo();
        historyStack.addToHistory(book);
        assertEquals(historyStack.toString(), "Undo stack: [0 animals]" + "\n" + "Redo stack: []");
    }

    @Test
    public void execute_addToHistory_duplicate() {
        ZooKeepBook bookA = new ZooKeepBook();
        ZooKeepBook bookB = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToHistory(bookA);
        historyStack.addToHistory(bookB);
        assertEquals(historyStack.toString(), "Undo stack: [0 animals]" + "\n" + "Redo stack: []");
    }

    @Test
    public void execute_removeRecentHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToHistory(book);
        historyStack.removeRecentHistory();
        assertEquals(historyStack.toString(), "Undo stack: []" + "\n" + "Redo stack: []");
    }

    @Test
    public void execute_viewRecentHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToHistory(book);
        assertEquals(historyStack.viewRecentHistory().toString(), "0 animals");
    }

    @Test
    public void execute_getHistorySize() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToHistory(book);
        assertEquals(historyStack.getHistorySize(), 1);
    }

    @Test
    public void execute_clearHistory() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        assertEquals(historyStack.toString(), "Undo stack: []" + "\n" + "Redo stack: []");
    }

    @Test
    public void execute_addToRedo_success() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToRedo(book);
        assertEquals(historyStack.toString(), "Undo stack: []" + "\n" + "Redo stack: [0 animals]");
    }

    @Test
    public void execute_removeRecentRedo() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToRedo(book);
        historyStack.removeRecentRedo();
        assertEquals(historyStack.toString(), "Undo stack: []" + "\n" + "Redo stack: []");
    }

    @Test
    public void execute_viewRecentRedo() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToRedo(book);
        assertEquals(historyStack.viewRecentRedo().toString(), "0 animals");
    }

    @Test
    public void execute_getRedoSize() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        historyStack.addToRedo(book);
        assertEquals(historyStack.getRedoSize(), 1);
    }

    @Test
    public void execute_clearRedo() {
        ZooKeepBook book = new ZooKeepBook();
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        historyStack.clearRedo();
        assertEquals(historyStack.toString(), "Undo stack: []" + "\n" + "Redo stack: []");
    }
}
