package seedu.pivot.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleObjectProperty;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.model.investigationcase.ArchiveStatus;


/**
 * Tracks the state of the GUI.
 */
public class UiStateManager {
    private static SimpleObjectProperty<Index> caseState = new SimpleObjectProperty<>(null);
    private static SimpleObjectProperty<ArchiveStatus> currentSection = new SimpleObjectProperty<>(null);
    private static SimpleObjectProperty<String> tabState = new SimpleObjectProperty<>(null);
    private static final Logger logger = LogsCenter.getLogger(UiStateManager.class);

    public static SimpleObjectProperty<Index> getCaseState() {
        return caseState;
    }

    public static void setCasePanelState(Index index) {
        logger.info("UIStateManager: Setting UI State: " + index);
        caseState.set(index);
    }

    /**
     * Resets the UI State to null.
     */
    public static void resetCasePanelState() {
        logger.info("UIStateManager: Resetting UI State");
        caseState.set(null);
    }

    public static SimpleObjectProperty<String> getTabState() {
        return tabState;
    }

    /**
     * Sets the UI Tab State to given Tab Type.
     * @param tabType
     */
    public static void setTabState(String tabType) {
        logger.info("UIStateManager: Setting UI Tab State: " + tabType);
        tabState.set(tabType);
    }

    /**
     * Resets the UI Tab State to null.
     */
    public static void resetTabState() {
        logger.info("UIStateManager: Resetting UI Tab State");
        tabState.set(null);
    }

    /**
     * Signals the GUI to refresh window.
     */
    public static void refresh() {
        logger.info("UIStateManager: Refreshing UI State");
        Index index = caseState.get();
        resetCasePanelState();
        setCasePanelState(index);

        String tabType = tabState.get();
        resetTabState();
        setTabState(tabType);
    }

    public static void setStatusBarArchived() {
        logger.info("UIStateManager: Setting UI section: " + currentSection);
        currentSection.set(ArchiveStatus.ARCHIVED);
    }

    public static void setStatusBarDefault() {
        logger.info("UIStateManager: Setting UI section: " + currentSection);
        currentSection.set(ArchiveStatus.DEFAULT);
    }

}
