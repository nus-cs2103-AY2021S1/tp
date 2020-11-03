package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.TimeSlot;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.event.Event;

/**
 * Overlap class contains methods to check if TimeSlots overlap with one another in PlaNus.
 */
public class Overlap {
    /**
     * Checks if 2 tasks or lessons overlap with one another.
     */
    public static boolean isOverlappingTimePeriod(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB,
                                                  LocalDateTime endB) {
        return (startA.isBefore(endB)) && (endA.isAfter(startB));
    }
    /**
     * Returns true if date and time of this timeslot will overlap with another timeslot in PlaNus.
     */
    public static boolean isSameTimeSlot(TimeSlot first, TimeSlot second) {
        if (first == second) {
            return true;
        }
        return first != null
                && second != null
                && first.getDayOfWeek().equals(second.getDayOfWeek())
                && Overlap.isOverlappingTimePeriod(first.getStartDateTimeValue(), first.getEndDateTimeValue(),
                first.getStartDateTimeValue(), second.getStartDateTimeValue());
    }
    /**
     * Checks if a time slot overlaps with an existing lesson in PlaNus.
     */
    public static boolean overlapWithOtherLessons(Model model, TimeSlot timeSlot) {
        requireNonNull(model);
        ObservableList<Lesson> existingLessons = model.getFilteredLessonList();
        for (Lesson lesson: existingLessons) {
            if (Overlap.isSameTimeSlot(lesson, timeSlot)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a time slot overlaps with an existing event in PlaNus.
     */
    public static boolean overlapWithOtherEvents(Model model, TimeSlot timeSlot) {
        requireNonNull(model);
        ObservableList<Task> existingTasks = model.getFilteredTaskList();
        for (Task task: existingTasks) {
            if (task instanceof Event && Overlap.isSameTimeSlot((Event) task, timeSlot)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a time slot overlaps with an existing lesson or event in PlaNus.
     */
    public static boolean overlapWithOtherTimeSlots(Model model, TimeSlot timeSlot) {
        return overlapWithOtherLessons(model, timeSlot) && overlapWithOtherEvents(model, timeSlot);
    }
}
