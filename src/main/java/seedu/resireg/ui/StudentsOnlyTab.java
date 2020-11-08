package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import seedu.resireg.ui.student.StudentListPanel;

public class StudentsOnlyTab extends UiPart<Tab> implements StudentsTab {
    private static final String FXML = "StudentsOnlyTab.fxml";

    @FXML
    private StackPane studentListPanelPlaceholder;

    public StudentsOnlyTab() {
        super(FXML);
    }

    @Override
    public void setStudentListPanel(StudentListPanel studentListPanel) {
        studentListPanelPlaceholder.getChildren().clear();
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
    }

    @Override
    public Tab getTab() {
        return getRoot();
    }
}
