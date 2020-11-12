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
    private static boolean isOverlappingTimePeriod(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB,
                                                  LocalDateTime endB) {
        boolean dateOverlap = !((startA.toLocalDate().isAfter(endB.toLocalDate()))
                || (endA.toLocalDate().isBefore(startB.toLocalDate())));
        boolean timeOfDayOverlap = (startA.toLocalTime().isBefore(endB.toLocalTime()))
                && (endA.toLocalTime().isAfter(startB.toLocalTime()));
        return timeOfDayOverlap && dateOverlap;
    }
    /**
     * Returns true if date and time of this time slot will overlap with another time slot in PlaNus.
     */
    public static boolean isSameTimeSlot(TimeSlot first, TimeSlot second) {
        if (first == second) {
            return true;
        }
        return first != null
                && second != null
                && first.getDayOfWeek().equals(second.getDayOfWeek())
                && Overlap.isOverlappingTimePeriod(first.getStartDateTimeValue(), first.getEndDateTimeValue(),
                second.getStartDateTimeValue(), second.getEndDateTimeValue());
    }

    /**
     * Checks if a time slot overlaps with an existing lesson in PlaNus.
     * Ignores the {@code timeSlotNotToCheck} time slot. Null if all time slots are to be checked.
     */

    private static boolean overlapWithOtherLessons(Model model, TimeSlot timeSlot, TimeSlot timeSlotNotToCheck) {
        requireNonNull(model);
        ObservableList<Lesson> existingLessons = model.getFilteredLessonList();
        for (Lesson lesson: existingLessons) {
            if (lesson != timeSlotNotToCheck && isSameTimeSlot(lesson, timeSlot)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a time slot overlaps with an existing event in PlaNus.
     * Ignores the {@code timeSlotNotToCheck} time slot. Null if all time slots are to be checked.
     */
    private static boolean overlapWithOtherEvents(Model model, TimeSlot timeSlot, TimeSlot timeSlotNotToCheck) {
        requireNonNull(model);
        ObservableList<Task> existingTasks = model.getFilteredTaskList();
        for (Task task: existingTasks) {
            if (task instanceof Event && task != timeSlotNotToCheck && isSameTimeSlot((Event) task, timeSlot)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if a time slot overlaps with an existing lesson or event in PlaNus.
     */
    public static boolean overlapWithOtherTimeSlots(Model model, TimeSlot timeSlot) {
        return overlapWithOtherLessons(model, timeSlot, null)
                || overlapWithOtherEvents(model, timeSlot, null);
    }

    /**
     * Checks if an edited time slot overlaps with an existing lesson or event in PlaNus.
     */
    public static boolean overlapWithOtherTimeSlots(Model model, TimeSlot timeSlotToEdit, TimeSlot editedTimeSlot) {
        return overlapWithOtherLessons(model, editedTimeSlot, timeSlotToEdit)
                || overlapWithOtherEvents(model, editedTimeSlot, timeSlotToEdit);
    }
}
