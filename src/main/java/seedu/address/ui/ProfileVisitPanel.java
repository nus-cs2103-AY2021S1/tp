package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.visit.Visit;

/**
 * Panel containing the list of Visit Reports.
 */
public class ProfileVisitPanel extends UiPart<Stage> {
    private static final String FXML = "ProfileVisitPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private ListView<Visit> visitHistoryView;

    /**
     * Creates a VisitListPanel object.
     */
    public ProfileVisitPanel(Stage root) {
        super(FXML, root);
        setup(FXCollections.observableArrayList());
    }

    /**
     * Creates a new visit history panel.
     */
    public ProfileVisitPanel() {
        this(new Stage());

    }

    public void setup(ObservableList<Visit> visitHistory) {
        visitHistoryView.setItems(visitHistory);
        visitHistoryView.setCellFactory(listView -> new VisitHistoryViewCell());
    }


    /**
     * Class to display {@code Visit} using {@code VisitHistoryViewCell}.
     */
    class VisitHistoryViewCell extends ListCell<Visit> {
        @Override
        protected void updateItem(Visit visit, boolean empty) {
            super.updateItem(visit, empty);

            if (visit == null || empty) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileVisitCard(visit, String.valueOf(getIndex() + 1)).getRoot());
            }
        }
    }

    /**
     * Displays the VisitListPanel window.
     */
    public void show() {
        logger.fine("Displaying visitation log.");
        getRoot().show();
        getRoot().setAlwaysOnTop(true);
        getRoot().centerOnScreen();
        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            KeyCode userInput = event.getCode();
            if (userInput == KeyCode.ESCAPE) {
                this.hide();
            }
        });

    }

    /**
     * Checks if the VisitListPanel window is being displayed.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the VisitListPanel window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the VisitListPanel window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
