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
public class CompletedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "CompletedTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpiredTaskListPanel.class);

    @FXML
    private ListView<Task> completedTaskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public CompletedTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        completedTaskListView.setItems(taskList);
        completedTaskListView.setCellFactory(listView -> new TaskListViewCell());
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
