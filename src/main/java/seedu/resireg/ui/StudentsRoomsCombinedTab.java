package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import seedu.resireg.ui.room.RoomListPanel;
import seedu.resireg.ui.student.StudentListPanel;

public class StudentsRoomsCombinedTab extends UiPart<Tab> implements StudentsTab, RoomsTab {

    private static final String FXML = "StudentsRoomsCombinedTab.fxml";

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane roomListPanelPlaceholder;

    public StudentsRoomsCombinedTab() {
        super(FXML);
    }

    @Override
    public void setStudentListPanel(StudentListPanel studentListPanel) {
        studentListPanelPlaceholder.getChildren().clear();
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
    }

    @Override
    public void setRoomListPanel(RoomListPanel roomListPanel) {
        roomListPanelPlaceholder.getChildren().clear();
        roomListPanelPlaceholder.getChildren().add(roomListPanel.getRoot());
    }

    @Override
    public Tab getTab() {
        return getRoot();
    }
}
