package seedu.resireg.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.resireg.commons.util.InvalidationListenerList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.allocation.UniqueAllocationList;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.UniqueRoomList;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.academicyear.AcademicYear;
import seedu.resireg.model.semester.semesternumber.SemesterNumber;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.UniqueStudentList;

/**
 * Wraps all data at the base level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class ResiReg implements ReadOnlyResiReg {

    private Semester semester;
    private final UniqueStudentList students;
    private final UniqueRoomList rooms;
    private final UniqueAllocationList allocations;
    private final InvalidationListenerList listenerList = new InvalidationListenerList();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        semester = new Semester(
                new AcademicYear(LocalDate.now().getYear()),
                new SemesterNumber(1)
        );
        students = new UniqueStudentList();
        rooms = new UniqueRoomList();
        allocations = new UniqueAllocationList();
    }

    public ResiReg() {}

    /**
     * Creates an ResiReg using the Students and Rooms in the {@code toBeCopied}
     */
    public ResiReg(ReadOnlyResiReg toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     *
     * @param semester
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
        indicateModified();
    }

    public static ResiReg getNextSemesterResiReg(ReadOnlyResiReg toBeCopied) {
        ResiReg newResiReg = new ResiReg();
        newResiReg.setStudents(toBeCopied.getStudentList());
        newResiReg.setRooms(toBeCopied.getRoomList());

        Semester currentSemester = toBeCopied.getSemester();
        newResiReg.semester = Semester.getNextSemester(currentSemester);

        return newResiReg;
    }

    /**
     * @return the shortened representation of the semester.
     */
    public String getSemesterString() {
        return semester.getShortRepresentation();
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
        indicateModified();
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
        indicateModified();
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms
     */
    public void setAllocations(List<Allocation> allocations) {
        this.allocations.setAllocations(allocations);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code ResiReg} with {@code newData}.
     */
    public void resetData(ReadOnlyResiReg newData) {
        requireNonNull(newData);

        setSemester(newData.getSemester());
        setStudents(newData.getStudentList());
        setRooms(newData.getRoomList());
        setAllocations(newData.getAllocationList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in ResiReg.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to ResiReg.
     * The student must not already exist in ResiReg.
     */
    public void addStudent(Student p) {
        students.add(p);
        indicateModified();
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in ResiReg.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in ResiReg.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code ResiReg}.
     * {@code key} must exist in ResiReg.
     */
    public void removeStudent(Student key) {
        students.remove(key);
        indicateModified();
    }

    /**
     * Returns true if an allocation relating to {@code student} exists in ResiReg.
     */
    public boolean isAllocated(Student student) {
        requireNonNull(student);
        return allocations.contains(student);
    }

    /**
     * Returns true if an allocation relating to {@code room} exists in ResiReg.
     */
    public boolean isAllocated(Room room) {
        requireNonNull(room);
        return allocations.contains(room);
    }


    //// room-level operations

    /**
     * Adds a room to ResiReg.
     * The room must not already exist in ResiReg.
     */
    public void addRoom(Room r) {
        rooms.add(r);
        indicateModified();
    }

    /**
     * Returns true if a room with the same identity as {@code room} exists in ResiReg.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return rooms.contains(room);
    }

    /**
     * Replaces the given room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in ResiReg.
     * The room identity of {@code editedStudent} must not be the same as another existing room
     * in ResiReg.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireNonNull(editedRoom);
        rooms.setRoom(target, editedRoom);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code ResiReg}.
     * {@code key} must exist in ResiReg.
     */
    public void removeRoom(Room key) {
        rooms.remove(key);
        indicateModified();
    }

    //// allocation-level operations

    /**
     * Adds an allocation to ResiReg.
     * The allocation must not already exist in ResiReg.
     */
    public void addAllocation(Allocation allocation) {
        requireNonNull(allocation);
        allocations.add(allocation);
        indicateModified();
    }

    /**
     * Returns true if an allocation with the same identity as {@code allocation} exists in ResiReg.
     */
    public boolean hasAllocation(Allocation allocation) {
        requireNonNull(allocation);
        return allocations.contains(allocation);
    }

    /**
     * Replaces the given allocation {@code target} in the list with {@code editedAllocation}.
     * {@code target} must exist in ResiReg.
     * The allocation identity of {@code editedALlocation} must not be the same as another existing allocation
     * in ResiReg.
     */
    public void setAllocation(Allocation target, Allocation editedAllocation) {
        requireNonNull(editedAllocation);
        allocations.setAllocation(target, editedAllocation);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code ResiReg}.
     * {@code key} must exist in ResiReg.
     */
    public void removeAllocation(Allocation key) {
        allocations.remove(key);
        indicateModified();
    }

    //// methods related to listeners
    public void addListener(InvalidationListener listener) {
        listenerList.addListener(listener);
    }

    public void removeListener(InvalidationListener listener) {
        listenerList.removeListener(listener);
    }

    /**
     * Notifies listeners that there are modifications to resireg.
     */
    protected void indicateModified() {
        listenerList.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public Semester getSemester() {
        return semester;
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Allocation> getAllocationList() {
        return allocations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResiReg // instanceof handles nulls
                && students.equals(((ResiReg) other).students)
                && rooms.equals(((ResiReg) other).rooms)
                && allocations.equals(((ResiReg) other).allocations));
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, rooms, allocations);
    }
}
