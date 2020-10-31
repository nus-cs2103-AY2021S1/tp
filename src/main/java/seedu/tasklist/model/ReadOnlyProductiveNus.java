package seedu.tasklist.model;

import javafx.collections.ObservableList;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.model.lesson.Lesson;
import seedu.tasklist.model.task.Task;
import seedu.tasklist.timetable.TimetableData;

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
