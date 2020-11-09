package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

//@@author SQwQ-reused
//Reused from
//https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/ui/EmptyVisitList.java
//with modifications
/**
 * Controller for a help page
 */
public class EmptyVisitHistory extends UiPart<Stage> {

    public static final String EMPTY_MESSAGE = "Patient has no past visitation records.";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "EmptyVisitHistoryWindow.fxml";

    @FXML
    private Button button;

    @FXML
    private Label text;

    /**
     * Instantiates new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public EmptyVisitHistory(Stage root) {
        super(FXML, root);
        text.setText(EMPTY_MESSAGE);
        setEscAsClose();
    }

    /**
     * Instantiates new HelpWindow.
     */
    public EmptyVisitHistory() {
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

    /**
     * Sets the escape key to close the No Past Visitation Records Window.
     */
    private void setEscAsClose() {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Stage stage = ((Stage) getRoot().getScene().getWindow());
                stage.close();
            }
        });
    }
}
