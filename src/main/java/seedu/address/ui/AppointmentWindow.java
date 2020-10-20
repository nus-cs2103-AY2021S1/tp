package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class AppointmentWindow extends UiPart<Stage>{

    private final Logger logger = LogsCenter.getLogger(getClass());
    private static final String FXML = "AppointmentWindow.fxml";
    @FXML
    private Label name;

    @FXML
    private Label description;

    public AppointmentWindow(Stage root){
        super(FXML, root);
    }

    public void show() {
        logger.fine("Showing appointment of patient");
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
