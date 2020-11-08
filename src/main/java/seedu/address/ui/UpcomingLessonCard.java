package seedu.address.ui;

import static seedu.address.model.task.Time.TIME_DATE_TIME_FORMAT;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Time;


/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class UpcomingLessonCard extends UiPart<Region> {

    private static final String FXML = "UpcomingLessonListCard.fxml";
    private static final long MIN_PER_HOUR = 60;
    private static final long HOUR_PER_DAY = 24;
    private static final long DAY_PER_WEEK = 7;
    private static final long MIN_DAY_PER_MONTH = 28;
    private static final String START_SOON_STYLE_CLASS = "start-soon";
    private static final String START_IN_A_WEEK_STYLE_CLASS = "start-in-a-week";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;

    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    //@FXML
    //private Label moduleCode;
    @FXML
    private Label time;
    @FXML
    private Label startIn;

    /**
     * Creates a {@code LessonCode} with the given {@code Lesson} and index to display.
     */
    public UpcomingLessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        name.setText(lesson.getName().fullName);
        time.setText(formatTime(lesson.getTime(), lesson.getEndTime()));
        getStartDate(startIn, lesson.getTime());
        //moduleCode.setText("Module: " + lesson.getModuleCode().moduleCode);
    }

    private String formatTime(Time startDate, Time endDate) {
        String date = LocalDateTime.parse(startDate.value, inputFormat).toLocalDate().format(
                DateTimeFormatter.ofPattern("dd MMM yyyy"));
        LocalTime startTime = LocalDateTime.parse(startDate.value, inputFormat).toLocalTime();
        LocalTime endTime = LocalDateTime.parse(endDate.value, inputFormat).toLocalTime();
        return date + " " + startTime + "-" + endTime;
    }

    public void getStartDate(Label label, Time deadline) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime due = LocalDateTime.parse(deadline.value, inputFormat);
        String formattedDue = due.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        Duration duration = Duration.between(now, due);
        if (duration.toMinutes() < MIN_PER_HOUR) {
            setStyleToIndicateStartSoon(label);
            label.setText("Start in " + duration.toMinutes() + " minutes");
        } else if (duration.toHours() < HOUR_PER_DAY) {
            setStyleToIndicateStartSoon(label);
            label.setText("Start in " + duration.toHours() + " hours");
        } else if (duration.toDays() < DAY_PER_WEEK) {
            setStyleToIndicateStartInAWeek(label);
            label.setText("Start in " + duration.toDays() + " days");
        } else if (duration.toDays() < MIN_DAY_PER_MONTH) {
            label.setText("Start in " + duration.toDays() + " days");
        } else {
            label.setText("Start in months");
        }
    }

    public void setStyleToIndicateStartSoon(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(START_SOON_STYLE_CLASS)) {
            return;
        }

        styleClass.add(START_SOON_STYLE_CLASS);
    }

    public void setStyleToIndicateStartInAWeek(Label label) {
        ObservableList<String> styleClass = label.getStyleClass();

        if (styleClass.contains(START_IN_A_WEEK_STYLE_CLASS)) {
            return;
        }

        styleClass.add(START_IN_A_WEEK_STYLE_CLASS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpcomingLessonCard)) {
            return false;
        }

        // state check
        UpcomingLessonCard card = (UpcomingLessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }
}
