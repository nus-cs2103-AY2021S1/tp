package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.timetable.TimetableData;

/**
 * Unmodifiable view of ProductiveNus
 */
public interface ReadOnlyProductiveNus {

    /**
     * Returns an unmodifiable view of the Assignments list.
     * This list will not contain any duplicate assignments.
     */
    ObservableList<Assignment> getAssignmentList();

    ObservableList<Task> getTaskList();

    ObservableList<Lesson> getLessonList();
    void importTimetable(TimetableData data);
}
