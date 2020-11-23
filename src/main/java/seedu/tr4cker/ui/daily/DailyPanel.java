package seedu.tr4cker.ui.daily;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.ui.UiPart;

/**
 * Panel containing the list of todos in Daily.
 */
public class DailyPanel extends UiPart<Region> {
    private static final String FXML = "DailyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DailyPanel.class);

    @FXML
    private ListView<Todo> dailyTodosListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public DailyPanel(ObservableList<Todo> todoList) {
        super(FXML);
        dailyTodosListView.setItems(todoList);
        dailyTodosListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Todo> {
        @Override
        protected void updateItem(Todo task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TodoCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
