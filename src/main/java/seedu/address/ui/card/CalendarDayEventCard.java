package seedu.address.ui.card;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;
import seedu.address.model.task.event.Event;
import seedu.address.ui.UiPart;

public class CalendarDayEventCard extends UiPart<Region> {
    private static final String FXML = "CalendarDayEventCard.fxml";

    public final Task event;

    @FXML
    private Label startTime;
    @FXML
    private Label title;
    @FXML
    private Label duration;

    /**
     * Create a CalendarDayEventCard that holds day events
     * @param task the task that will be displayed
     */
    public CalendarDayEventCard(Task task) {
        super(FXML);
        this.event = task;
        setStartTime(task);
        title.setText(task.getTitle().toString());
        setDuration(task);
    }

    private void setStartTime(Task task) {
        Event event = (Event) task;
        LocalDateTime dateTime = event.getStartDateTimeValue();
        startTime.setText(dateTime.toLocalTime().toString());
    }

    private void setDuration(Task task) {
        Event event = (Event) task;
        String time = "" + Duration.between(event.getStartDateTimeValue(), event.getEndDateTimeValue()).toMinutes();
        duration.setText(time);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        return false;
    }
}
