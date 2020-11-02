package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class EmptyVisitList extends UiPart<Stage> {

    public static final String EMPTY_MESSAGE = "Patient has no past visitation records.";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "EmptyVisitListWindow.fxml";

    @FXML
    private Button button;

    @FXML
    private Label text;

    /**
     * Instantiates new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public EmptyVisitList(Stage root) {
        super(FXML, root);
        text.setText(EMPTY_MESSAGE);
    }

    /**
     * Instantiates new HelpWindow.
     */
    public EmptyVisitList() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Displaying help window for CliniCal.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Checks if help window is being displayed.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies CliniCal's user guide link to clipboard.
     */
    @FXML
    private void copyUrl() {
        this.hide();
    }
}
