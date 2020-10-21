package seedu.pivot.ui;

import javafx.beans.property.SimpleObjectProperty;
import seedu.pivot.commons.core.index.Index;

/**
 * Tracks the state of the GUI.
 */
public class UiStateManager {
    private static SimpleObjectProperty<Index> caseState = new SimpleObjectProperty<>(null);

    public static SimpleObjectProperty<Index> getCaseState() {
        return caseState;
    }

    public static void setCasePanelState(Index index) {
        caseState.set(index);
    }

    public static void resetCasePanelState() {
        caseState.set(null);
    }

    /**
     * Signals the GUI to refresh window.
     */
    public static void refresh() {
        Index index = caseState.get();
        resetCasePanelState();
        setCasePanelState(index);
    }
}
