package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a dialog page
 */
public class DialogWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(DialogWindow.class);

    private static final String FXML = "DialogWindow.fxml";

    @FXML
    private Label dialogMessage;

    /**
     * Creates a new DialogWindow that displays a {@code message}
     *
     * @param root Stage to use as the root of the HelpWindow.
     * @param dialogMessage The message to be displayed.
     */
    public DialogWindow(Stage root, String dialogMessage) {
        super(FXML, root);
        this.dialogMessage.setText(dialogMessage);
    }

    /**
     * Creates a new DialogWindow that displays a {@code message}
     *
     * @param dialogMessage The message to be displayed.
     */
    public DialogWindow(String dialogMessage) {
        this(new Stage(), dialogMessage);
        this.dialogMessage.setText(dialogMessage);
    }

    /**
     * Shows the dialog window.
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
        logger.fine("Showing dialog message");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the dialog window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the dialog window.
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
