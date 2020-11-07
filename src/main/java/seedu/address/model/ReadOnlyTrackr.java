package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Unmodifiable view of Track.
 */
public interface ReadOnlyTrackr<T> {

    /**
     * Returns an unmodifiable view of the {@code Module} objects list.
     * This list will not contain any duplicate objects.
     */
    ObservableList<T> getList();

    /**
     * Returns an unmodifiable view of the {@code Student} objects list.
     * This list will not contain any duplicate objects.
     */
    ObservableList<Student> getStudentList(Module targetModule, TutorialGroup targetTg);
}
