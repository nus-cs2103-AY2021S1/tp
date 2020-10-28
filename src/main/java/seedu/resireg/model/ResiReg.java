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
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.UniqueBinItemList;
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
    private AppMode appMode;
    private final UniqueStudentList students;
    private final UniqueRoomList rooms;
    private final UniqueAllocationList allocations;
    private final UniqueBinItemList binItems;
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
        binItems = new UniqueBinItemList();
        appMode = AppMode.NEW;
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
        newResiReg.setBinItems(toBeCopied.getBinItemList());

        Semester currentSemester = toBeCopied.getSemester();
        newResiReg.semester = currentSemester.getNextSemester();

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
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms
     */
    public void setBinItems(List<BinItem> binItems) {
        this.binItems.setBinItems(binItems);
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
        setBinItems(newData.getBinItemList());
        setAppMode(newData.getAppMode());
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
    public void addStudent(Student student) {
        assert !hasStudent(student) : "Student must not already exist in ResiReg!";
        students.add(student);
        indicateModified();
    }

    /**
     * Adds a student to ResiReg.
     * The student must not already exist in ResiReg.
     */
    public void addStudent(Student student, boolean isFront) {
        assert !hasStudent(student) : "Student must not already exist in ResiReg!";
        if (isFront) {
            students.add(0, student);
            indicateModified();
        } else {
            addStudent(student);
        }
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


    /**
     * Adds a room to ResiReg.
     * The room must not already exist in ResiReg.
     */
    public void addBinItem(BinItem b) {
        binItems.add(b);
    }

    /**
     * Returns true if a room with the same identity as {@code room} exists in ResiReg.
     */
    public boolean hasBinItem(BinItem binItem) {
        requireNonNull(binItem);
        return binItems.contains(binItem);
    }

    /**
     * Removes {@code key} from this {@code ResiReg}.
     * {@code key} must exist in ResiReg.
     */
    public void removeBinItem(BinItem key) {
        binItems.remove(key);
    }

    /**
     * Removes {@code key} from this {@code ResiReg}.
     * {@code key} must exist in ResiReg.
     */
    public void setBinItem(BinItem target, BinItem editedItem) {
        requireNonNull(editedItem);
        binItems.setBinItem(target, editedItem);
    }

    /**
     * Deletes all items older than {@code daysStoredInBin} in bin from this {@code ResiReg}.
     * {@code daysStoredInBin} must be a positive integer.
     */
    public void deleteExpiredBinItems(int daysStoredInBin) {
        assert daysStoredInBin > 0 : "Days Stored in bin should be a positive integer";
        for (BinItem binItem : binItems) {
            if (binItem.isExpired(daysStoredInBin)) {
                binItems.remove(binItem);
            }
        }
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

    //// methods related to AppMode
    // AppMode can only be set internally

    @Override
    public AppMode getAppMode() {
        return appMode;
    }

    /**
     * Switch the {@code AppMode} from new mode to normal mode.
     */
    public void finalizeRooms() {
        assert appMode != AppMode.NORMAL
                : "Rooms have already been finalized. The user shouldn't be able to call the FinalizeRoomsCommand.";
        setAppMode(AppMode.NORMAL);
    }

    private void setAppMode(AppMode appMode) {
        requireNonNull(appMode);
        if (appMode != this.appMode) {
            this.appMode = appMode;
            indicateModified();
        }
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students, "
            + rooms.asUnmodifiableObservableList().size() + " rooms, "
            + allocations.asUnmodifiableObservableList().size() + " allocs, "
            + binItems.asUnmodifiableObservableList().size() + " bin items";
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
    public ObservableList<BinItem> getBinItemList() {
        return binItems.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResiReg // instanceof handles nulls
                && students.equals(((ResiReg) other).students)
                && rooms.equals(((ResiReg) other).rooms)
                && allocations.equals(((ResiReg) other).allocations))
                && binItems.equals(((ResiReg) other).binItems)
                && appMode.equals(((ResiReg) other).appMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, rooms, allocations, binItems);
    }
}
