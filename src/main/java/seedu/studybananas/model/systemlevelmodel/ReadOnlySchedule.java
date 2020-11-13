package seedu.studybananas.model.systemlevelmodel;

import javafx.collections.ObservableList;
import seedu.studybananas.model.task.Task;


/**
 * Unmodifiable view of a schedule
  */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
