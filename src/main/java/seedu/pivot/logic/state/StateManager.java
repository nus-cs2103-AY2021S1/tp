package seedu.pivot.logic.state;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.ui.UiStateManager;

/**
 * Tracks the state of the program.
 */
public class StateManager {

    private static Optional<Index> state = Optional.empty();
    private static final Logger logger = LogsCenter.getLogger(StateManager.class);

    /**
     * Gets the state of the program.
     *
     * @return Index of case that the program is at,
     * or null if at main page.
     */
    public static Index getState() {
        return state.orElse(null);
    }

    /**
     * Sets the state of program to a case index.
     *
     * @param index Index of a case in the list.
     */
    public static void setState(Index index) {
        logger.info("StateManager: Setting state with index" + index);
        assert (index != null) : "index should not be null";
        state = Optional.of(index);
        UiStateManager.setCasePanelState(index);
    }

    /**
     * Resets the state of program to an empty state.
     */
    public static void resetState() {
        logger.info("StateManager: Resetting State");
        state = Optional.empty();
        UiStateManager.resetCasePanelState();
    }

    /**
     * Checks if the state of the program is at an investigation case.
     *
     * @return true if the state is non empty.
     */
    public static boolean atCasePage() {
        return state.isPresent();
    }

    /**
     * Checks if the state of the program is at the main page.
     *
     * @return true if the state of the program is empty.
     */
    public static boolean atMainPage() {
        return state.isEmpty();
    }

    /**
     * Requests UIStateManager to refresh the GUI.
     */
    public static void refresh() {
        logger.info("StateManager: Requests UIStateManager to refresh state");
        UiStateManager.refresh();
    }
}
