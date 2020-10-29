package seedu.pivot.logic.state;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.ui.UiStateManager;

/**
 * Tracks the state of the program.
 */
public class StateManager {

    private static Optional<Index> state = Optional.empty();
    private static ArchiveStatus currentSection = ArchiveStatus.DEFAULT;
    private static Optional<String> tabState = Optional.empty();
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

    /**
     * Sets the archiveStatus of the program to be at archived section.
     *
     */
    public static void setArchivedSection() {
        logger.info("StateManager: Setting archiveStatus:" + ArchiveStatus.ARCHIVED);
        currentSection = ArchiveStatus.ARCHIVED;
    }

    /**
     * Sets the archiveStatus of the program to be at default section.
     *
     */
    public static void setDefaultSection() {
        logger.info("StateManager: Setting archiveStatus:" + ArchiveStatus.DEFAULT);
        currentSection = ArchiveStatus.DEFAULT;
    }

    /**
     * Checks if the program is at the archived section
     */
    public static boolean atArchivedSection() {
        return currentSection.equals(ArchiveStatus.ARCHIVED);
    }

    /**
     * Checks if the program is at the default section
     */
    public static boolean atDefaultSection() {
        return currentSection.equals(ArchiveStatus.DEFAULT);
    }

    /**
     * Sets the tabState of program to given tabType
     * @param tabType
     */
    public static void setTabState(String tabType) {
        logger.info("StateManager: Setting tabState with tab" + tabType);
        assert (tabType != null) : "tabType should not be null";
        assert (atCasePage()) : "State should not be null";
        tabState = Optional.of(tabType);
        UiStateManager.setTabState(tabType);
    }

    /**
     * Resets the tabState to empty state.
     */
    public static void resetTabState() {
        logger.info("StateManager: Resetting tabState");
        tabState = Optional.empty();
        UiStateManager.resetTabState();
    }
}
