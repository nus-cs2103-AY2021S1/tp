package seedu.resireg.model;

import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns the semester that this view is mapped to.
     */
    Semester getSemester();

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the rooms list.
     * This list will not contain any duplicate rooms.
     */
    ObservableList<Room> getRoomList();

    /**
     * Returns an unmodifiable view of the allocations list.
     * This list will not contain any duplicate allocations.
     */
    ObservableList<Allocation> getAllocationList();
}
