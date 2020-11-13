package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.budget.Budget;
import seedu.address.testutil.TypicalBudget;

class HistoryManagerTest {
    @Test
    void hasHistory_withoutHistory_returnsFalse() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertFalse(historyManager.hasHistory());
    }

    @Test
    void hasHistory_nullHistory_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.setHistory(null);
        assertThrows(AssertionError.class, () -> historyManager.hasHistory());
    }

    @Test
    void hasFuture_withoutFuture_returnsFalse() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertFalse(historyManager.hasFuture());
    }

    @Test
    void hasFuture_nullHistory_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.setHistory(null);
        assertThrows(AssertionError.class, () -> historyManager.hasFuture());
    }

    @Test
    void getHistory_withoutHistory_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertThrows(AssertionError.class, () -> historyManager.getHistory());
    }

    @Test
    void getHistory_withHistory_returnsCorrectly() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.saveToHistory(TypicalBudget.getKfcBudget());
        assertEquals(TypicalBudget.getKfcBudget(), historyManager.getHistory());
    }

    @Test
    void getFuture_withFuture_returnsCorrectly() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        Node<Budget> node = new Node<>(TypicalBudget.getKfcBudget());
        Node<Budget> next = new Node<>(TypicalBudget.getMcDonaldsBudget());
        node.connectTo(next);
        historyManager.setHistory(node);
        assertEquals(TypicalBudget.getMcDonaldsBudget(), historyManager.getFuture());
    }

    @Test
    void getFuture_withoutHistory_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertThrows(AssertionError.class, () -> historyManager.getFuture());
    }

    @Test
    void saveToHistory_nullParameters_throwsNullPointerException() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertThrows(NullPointerException.class, () -> historyManager.saveToHistory(null));
    }

    @Test
    void saveToHistory_validInput_savesSuccessfully() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.saveToHistory(TypicalBudget.getKfcBudget());
        assertEquals(TypicalBudget.getKfcBudget(), historyManager.getHistory());
    }

    @Test
    void saveToHistory_nullHistory_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.setHistory(null);
        assertThrows(AssertionError.class, () -> historyManager.saveToHistory(TypicalBudget.getKfcBudget()));
    }

    @Test
    void saveToFuture_nullParameters_throwsNullPointerException() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertThrows(NullPointerException.class, () -> historyManager.saveToFuture(null));
    }

    @Test
    void saveToFuture_hasFutureAfterSaving_savesSuccessfully() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        Node<Budget> previous = new Node<>(TypicalBudget.getKfcBudget());
        Node<Budget> node = new Node<>();
        previous.connectTo(node);
        historyManager.setHistory(node);
        historyManager.saveToFuture(TypicalBudget.getMcDonaldsBudget());
        historyManager.setHistory(previous);
        assertEquals(TypicalBudget.getMcDonaldsBudget(), historyManager.getFuture());
    }

    @Test
    void saveToFuture_hasNoFutureAfterSaving_throwsAssertionError() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        Node<Budget> previous = new Node<>(TypicalBudget.getKfcBudget());
        Node<Budget> node = new Node<>();
        previous.connectTo(node);
        historyManager.setHistory(node);
        historyManager.saveToFuture(TypicalBudget.getMcDonaldsBudget());
        assertThrows(AssertionError.class, () -> historyManager.getFuture());
    }

    @Test
    void equals_sameHistoryManager_returnsTrue() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertTrue(historyManager.equals(historyManager));
    }

    @Test
    void equals_validHistoryManager_returnsTrue() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        historyManager.saveToHistory(TypicalBudget.getKfcBudget());
        HistoryManager<Budget> historyManagerToCompare = new HistoryManager<>();
        historyManager.saveToHistory(TypicalBudget.getKfcBudget());
        assertTrue(historyManager.equals(historyManagerToCompare));
    }

    @Test
    void equals_withNull_returnsFalse() {
        HistoryManager<Budget> historyManager = new HistoryManager<>();
        assertFalse(historyManager.equals(null));
    }
}
