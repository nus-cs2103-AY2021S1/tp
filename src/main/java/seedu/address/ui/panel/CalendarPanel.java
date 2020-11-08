package seedu.address.ui.panel;

import static seedu.address.ui.util.ScheduleUiUtil.IN_THE_DAY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;
import seedu.address.ui.schedule.UpcomingSchedule;

public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "panel/CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    private UpcomingSchedule upcomingSchedule;
    private ObservableList<Task> calendarList;
    private ArrayList<Node> dayPanels;
    private final ListChangeListener<Task> taskListener = new ListChangeListener<>() {
        @Override
        public void onChanged(Change<? extends Task> c) {
            updateCalendar();
        }
    };

    @FXML
    private GridPane calendar;

    /**
     * Create a CalendarPanel that holds the calendar view
     * @param calendarList
     */
    public CalendarPanel(ObservableList<Task> calendarList, UpcomingSchedule upcomingSchedule) {
        super(FXML);
        this.calendarList = calendarList;
        this.upcomingSchedule = upcomingSchedule;
        dayPanels = new ArrayList<>();
        loadCalendar(calendarList);
        calendarList.addListener(taskListener);
    }

    private void loadCalendar(ObservableList<Task> calendarList) {
        LocalDateTime currentDate = findCalendarStart();
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                FilteredList<Task> dayList = filterDayList(calendarList, currentDate);
                CalendarDayPanel calendarDayPanel = loadDay(dayList, currentDate);
                dayPanels.add(calendarDayPanel.getRoot());
                calendar.add(calendarDayPanel.getRoot(), j, i);
                if (currentDate.toLocalDate().isEqual(LocalDate.now())) {
                    calendarDayPanel.getRoot().setStyle("-fx-background-color: #F0F3BD");
                }
                if (currentDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                    calendarDayPanel.getRoot().setDisable(true);
                    calendarDayPanel.getRoot().setStyle("-fx-background-color: grey");
                }
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private void updateCalendar() {
        calendar.getChildren().removeAll(dayPanels);
        dayPanels.clear();
        loadCalendar(calendarList);
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
