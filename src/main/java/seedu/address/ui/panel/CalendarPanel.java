package seedu.address.ui.panel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;
import seedu.address.ui.schedule.UpcomingSchedule;

import static seedu.address.ui.util.ScheduleUiUtil.IN_THE_DAY;

public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "panel/CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    private UpcomingSchedule upcomingSchedule;

    @FXML
    private GridPane calendar;

    /**
     * Create a CalendarPanel that holds the calendar view
     * @param calendarList
     */
    public CalendarPanel(ObservableList<Task> calendarList, UpcomingSchedule upcomingSchedule) {
        super(FXML);
        this.upcomingSchedule = upcomingSchedule;
        loadCalendar(calendarList);
    }

    private void loadCalendar(ObservableList<Task> calendarList) {
        LocalDateTime startDate = findCalendarStart();
        LocalDateTime currentDate = startDate;
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                FilteredList<Task> dayList = filterDayList(calendarList, currentDate);
                CalendarDayPanel calendarDayPanel = loadDay(dayList, currentDate);
                calendar.add(calendarDayPanel.getRoot(), j, i);
                if (currentDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                    calendarDayPanel.getRoot().setDisable(true);
                }
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private CalendarDayPanel loadDay(ObservableList<Task> dayList, LocalDateTime startDateTime) {
        return new CalendarDayPanel(upcomingSchedule, dayList, startDateTime);
    }

    private FilteredList<Task> filterDayList(ObservableList<Task> calendarList, LocalDateTime startDate) {
        Predicate<Task> predicate = task -> IN_THE_DAY.test(task, startDate);
        return calendarList.filtered(predicate);
    }

    private LocalDateTime findCalendarStart() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
        LocalDateTime startOfCalendar = startOfMonth.minusDays(startOfMonth.getDayOfWeek().getValue())
                .atStartOfDay();
        if (startOfMonth.getDayOfWeek().getValue() == 7) {
            startOfCalendar = startOfMonth.atStartOfDay();
        }
        return startOfCalendar;
    }
}
