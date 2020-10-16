package seedu.taskmaster.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.model.session.StudentRecord;

/**
 * Panel containing the list of attendance.
 */
public class StudentRecordListPanel extends UiPart<Region> {
    private static final String FXML = "StudentRecordListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentRecordListPanel.class);

    @FXML
    private ListView<StudentRecord> studentRecordListView;

    /**
     * Creates a {@code StudentRecordListPanel} with the given {@code ObservableList}.
     * @param studentRecordList
     */
    public StudentRecordListPanel(ObservableList<StudentRecord> studentRecordList) {
        super(FXML);
        studentRecordListView.setItems(studentRecordList);
        studentRecordListView.setCellFactory(listView -> new StudentRecordListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code StudentRecord} using a
     * {@code StudentRecordListCard}.
     */
    class StudentRecordListViewCell extends ListCell<StudentRecord> {
        @Override
        protected void updateItem(StudentRecord studentRecord, boolean empty) {
            super.updateItem(studentRecord, empty);

            if (empty || studentRecord == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentRecordListCard(studentRecord, getIndex() + 1).getRoot());
            }
        }
    }

}
