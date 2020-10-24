package seedu.stock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.Stock;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Controller for a NotesView page
 */
public class NotesViewWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(NotesViewWindow.class);
    private static final String FXML = "NotesViewWindow.fxml";


    /**
     * Shows the source statistics window.
     *
     * @param statisticsData The data to be used.
     * @param otherStatisticsDetails The other statistics data that is needed.
     * @throws IllegalStateException <ul>
     * <li>
     * if this method is called on a thread other than the JavaFX Application Thread.
     * </li>
     * <li>
     * if this method is called during animation or layout processing.
     * </li>
     * <li>
     * if this method is called on the primary stage.
     * </li>
     * <li>
     * if {@code dialogStage} is already showing.
     * </li>
     * </ul>
     */
    public void show(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
        logger.fine("Showing statistics window about the application.");
        refreshData(statisticsData, otherStatisticsDetails);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the notes view window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the notes view window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the notes view window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
