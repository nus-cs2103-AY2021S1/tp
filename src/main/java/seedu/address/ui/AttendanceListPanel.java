package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of students.
 */
public class AttendanceListPanel extends UiPart<Region> {
    private static final String FXML = "AttendanceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<String> attendanceListView; //change this to a list of attendance when ready

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public AttendanceListPanel(ObservableList<String> attendanceList) {
        super(FXML);
        attendanceListView.setItems(attendanceList);
        attendanceListView.setCellFactory(listView -> new AttendanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class AttendanceListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String attendance, boolean empty) {
            super.updateItem(attendance, empty);

            if (empty || attendance == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AttendanceCard(attendance, getIndex() + 1).getRoot());
            }
        }
    }

}
