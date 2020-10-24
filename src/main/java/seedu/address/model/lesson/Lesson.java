package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;

/**
 * Lesson class to store information about a module's lessons.
 */
public class Lesson {
    private final Title title;
    private final Description description;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private ArrayList<Task> associatedTasks = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Lesson(Title title, Description description, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
                  LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(title, description, dayOfWeek, startTime, endTime, startDate, endDate);
        this.title = title;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Title getTitle() {
        return title;
    }
    public Description getDescription() {
        return description;
    }
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * Creates recurring event tasks based on the lesson's details.
     * @return a list of recurring tasks to add.
     */
    public ArrayList<Task> createRecurringTasks() {
        LocalDate currentDate = getStartDate();
        while (!(currentDate.getDayOfWeek()).equals(this.dayOfWeek)) {
            currentDate = currentDate.plusDays(1);
        }
        ArrayList<Task> tasksToAdd = new ArrayList<>();
        while (currentDate.isBefore(this.endDate) || currentDate.isEqual(this.endDate)) {
            LocalDateTime localDateTime = LocalDateTime.of(currentDate, getStartTime());
            String dateTime = localDateTime.format(DateTime.FORMATTER);
            DateTime eventDateTime = new DateTime(dateTime);
            Task taskToAdd = new Task(title, eventDateTime, description, new Type("lesson"), new HashSet<>());
            tasksToAdd.add(taskToAdd);
            currentDate = currentDate.plusDays(7);
        }
        associatedTasks = tasksToAdd;
        return tasksToAdd;
    }

    /**
     * Returns the set of tasks the lesson created.
     * @return an array list of tasks created by the lesson.
     */
    public ArrayList<Task> getAssociatedTasks() {
        return associatedTasks;
    }

    /**
     * Returns true if both lessons of the same title have the same start and end date, and the same start and end time.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }
        return otherLesson != null
                && otherLesson.getTitle().equals(getTitle())
                && otherLesson.getDayOfWeek().equals(getDayOfWeek())
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getStartDate().equals(getStartDate())
                && otherLesson.getEndDate().equals(getEndDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, dayOfWeek, startTime, endTime, startDate, endDate);
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Description: ")
                .append(getDescription())
                .append(" Day: ")
                .append(getDayOfWeek())
                .append(" Start Time: ")
                .append(getStartTime())
                .append(" End Time: ")
                .append(getEndTime())
                .append(" Start Date: ")
                .append(getStartDate())
                .append(" End Date: ")
                .append(getEndDate());
        return builder.toString();
    }
}
