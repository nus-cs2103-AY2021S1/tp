package seedu.resireg.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

/**
 * Unmodifiable view of ResiReg
 */
public interface ReadOnlyResiReg extends Observable {

    /**
     * Returns the semester that this view is mapped to.
     */
    Semester getSemester();

    /**
     * Returns a String representation of the semester that this view is mapped to.
     */
    String getSemesterString();

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

    /**
     * Returns an unmodifiable view of the bin items list.
     * This list will not contain any duplicate bin items.
     */
    ObservableList<BinItem> getBinItemList();
}
