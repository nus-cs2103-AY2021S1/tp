package seedu.tr4cker.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.task.Task;

/**
 * Panel containing the list of expired tasks.
 */
public class ExpiredTaskListPanel extends UiPart<Region> {
    private static final String FXML = "ExpiredTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpiredTaskListPanel.class);

    @FXML
    private ListView<Task> expiredTaskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public ExpiredTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        expiredTaskListView.setItems(taskList);
        expiredTaskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
