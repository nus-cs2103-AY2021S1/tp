package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.event.Event;

public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private GridPane calendar;
    @FXML
    private Label column1;
    @FXML
    private Label column2;
    @FXML
    private Label column3;
    @FXML
    private Label column4;
    @FXML
    private Label column5;
    @FXML
    private Label column6;
    @FXML
    private Label column7;

    /**
     * Create a CalendarPanel that holds the calendar view
     * @param calendarList a list containing all tasks in the timeline
     */
    public CalendarPanel(ObservableList<Task> calendarList) {
        super(FXML);
        loadDays();
        loadCalendar(calendarList);
    }

    private void loadCalendar(ObservableList<Task> calendarList) {
        LocalDateTime startDate = findCalendarStart();
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                FilteredList<Task> dayList = filterDayList(calendarList, startDate);
                calendar.add(loadDay(dayList, startDate).getRoot(), j, i);
                startDate = startDate.plusDays(1);
            }
        }
    }

    private FilteredList<Task> filterDayList(ObservableList<Task> calendarList, LocalDateTime startDate) {
        Predicate<Task> inTheDay = task -> {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                return (deadline.getDeadlineDateTimeValue().isEqual(startDate)
                        || deadline.getDeadlineDateTimeValue().isAfter(startDate))
                        && deadline.getDeadlineDateTimeValue().isBefore(startDate.plusDays(1));
            } else {
                Event event = (Event) task;
                return (event.getStartDateTimeValue().isEqual(startDate)
                        || event.getStartDateTimeValue().isAfter(startDate))
                        && event.getStartDateTimeValue().isBefore(startDate.plusDays(1));
            }
        };
        return calendarList.filtered(inTheDay);
    }

    private CalendarDayPanel loadDay(ObservableList<Task> dayList, LocalDateTime startDateTime) {
        return new CalendarDayPanel(dayList, startDateTime);
    }

    private LocalDateTime findCalendarStart() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime start = today.minusDays(7 + today.getDayOfWeek().getValue());
        return start;
    }

    private void loadDays() {
        column1.setText("Sunday");
        column2.setText("Monday");
        column3.setText("Tuesday");
        column4.setText("Wednesday");
        column5.setText("Thursday");
        column6.setText("Friday");
        column7.setText("Saturday");
    }
}
