package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a help page
 */
public class TimelineWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(TimelineWindow.class);
    private static final String FXML = "TimelineWindow.fxml";

    private Logic logic;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox container;

    /**
     * Creates a new TimelineWindow.
     *
     * @param root Stage to use as the root of the TimelineWindow.
     */
    public TimelineWindow(Stage root, Logic logic) {
        super(FXML, root);

        this.logic = logic;

        for (int i = 0; i < logic.getFilteredMeetingList().size(); i++) {
            if (i % 2 == 0) {
                container.getChildren().addAll(new TimelineSection(
                        new TimelineMeetingCard(logic.getFilteredMeetingList().get(i))).getRoot());
            } else {
                container.getChildren().addAll(TimelineSection.getFlipped(new TimelineSection(
                        new TimelineMeetingCard(logic.getFilteredMeetingList().get(i)))).getRoot());
            }
        }
    }

    /**
     * Creates a new TimelineWindow.
     */
    public TimelineWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}