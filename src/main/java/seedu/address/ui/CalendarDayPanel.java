package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.ui.card.CalendarDayEventCard;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class CalendarDayPanel extends UiPart<Region> {
    private static final String FXML = "CalendarDayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarDayEventCard.class);

    @FXML
    private ListView<Task> scheduleView;
    @FXML
    private Label date;

    public CalendarDayPanel(ObservableList<Task> schedule, LocalDateTime dateTime) {
        super(FXML);
        displayDate(dateTime);
        scheduleView.setItems(schedule);
        scheduleView.setCellFactory(listView -> new CalendarDayPanelCell());
    }

    private void displayDate(LocalDateTime dateTime) {
        date.setText(dateTime.getMonth().toString() + " " + dateTime.getDayOfMonth());
    }

    class CalendarDayPanelCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarDayEventCard(task).getRoot());
            }
        }
    }
}
