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

    @FXML
    private Tab binsTab;

    public TabbedView() {
        super(FXML);
    }

    private void showStudentPanel() {
        tabPane.getSelectionModel().select(studentsTab);
    }

    private void showRoomsPanel() {
        tabPane.getSelectionModel().select(roomsTab);
    }

    private void showBinItemsPanel() {
        tabPane.getSelectionModel().select(binsTab);
    }


    @Override
    void handleToggle(TabView toggleView) {
        switch(toggleView) {
        case STUDENTS:
            showStudentPanel();
            break;
        case ROOMS:
            showRoomsPanel();
            break;
        case BIN_ITEMS:
            showBinItemsPanel();
            break;
        default:
            assert false : "Add another instance to TabView";
        }
    }
}
