package seedu.address.ui.card;

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

    /**
     * Create a CalendarDayEventCard that holds day events
     * @param task the task that will be displayed
     */
    public CalendarDayEventCard(Task task) {
        super(FXML);
        this.event = task;
        setStartTime(task);
        title.setText(task.getTitle().toString());
    }

    private void setStartTime(Task task) {
        Event event = (Event) task;
        startTime.setText(event.getStartDateTimeValue().toLocalTime().toString() + " - "
            + event.getEndDateTimeValue().toLocalTime().toString());
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
