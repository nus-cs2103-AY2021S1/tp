package seedu.address.ui.panel;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;
import seedu.address.ui.card.CalendarDayEventCard;
import seedu.address.ui.schedule.UpcomingSchedule;


public class CalendarDayPanel extends UiPart<Region> {
    private static final String FXML = "panel/CalendarDayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarDayEventCard.class);

    private ObservableList<Task> schedule;
    private LocalDateTime dateTime;
    private UpcomingSchedule upcomingSchedule;

    @FXML
    private VBox placeholder;
    @FXML
    private Label infoLesson;
    @FXML
    private Label infoEvent;
    @FXML
    private Label date;

    /**
     * Create a CalendarDayPanel object to hold day event information.
     * @param schedule the filtered tasks that will be displayed in the calendar view
     * @param dateTime the date time of the day cell in the calendar view
     */
    public CalendarDayPanel(UpcomingSchedule upcomingSchedule, ObservableList<Task> schedule,
                            LocalDateTime dateTime) {
        super(FXML);
        this.schedule = schedule;
        this.dateTime = dateTime;
        this.upcomingSchedule = upcomingSchedule;
        displayDate(dateTime);
        displayInfo(schedule);
        placeholder.addEventHandler(MouseEvent.MOUSE_CLICKED, upcomingSchedule);
    }

    private void displayDate(LocalDateTime dateTime) {
        if (dateTime.getDayOfMonth() == 1) {
            date.setText(dateTime.getMonth().toString().substring(0, 3) + " " + dateTime.getDayOfMonth());
        } else {
            date.setText("" + dateTime.getDayOfMonth());
        }
    }

    private void displayInfo(ObservableList<Task> schedule) {
        int[] count = countLessonEvent(schedule);
        infoLesson.setText(count[0] + " Lessons");
        infoEvent.setText(count[1] + " Events");
    }

    private int[] countLessonEvent(ObservableList<Task> schedule) {
        int[] count = new int[2];

        for (Task task : schedule) {
            if (task.isLesson()) {
                count[0]++;
            } else {
                count[1]++;
            }
        }

        return count;
    }
}
