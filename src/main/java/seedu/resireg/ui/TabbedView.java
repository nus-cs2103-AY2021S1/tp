package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import seedu.resireg.logic.commands.TabView;

public class TabbedView extends MainPanel {
    private static final String FXML = "TabbedView.fxml";

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab studentsTab;

    @FXML
    private Tab roomsTab;

    public TabbedView() {
        super(FXML);
    }

    private void showStudentPanel() {
        tabPane.getSelectionModel().select(studentsTab);
    }

    private void showRoomsPanel() {
        tabPane.getSelectionModel().select(roomsTab);
    }

    @Override
    void handleToggle(TabView toggleView) {
        if (toggleView == TabView.ROOMS) {
            showRoomsPanel();
        } else {
            showStudentPanel();
        }
    }
}
