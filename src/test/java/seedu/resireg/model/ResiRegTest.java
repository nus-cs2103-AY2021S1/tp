package seedu.resireg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_RENOVATED;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalRooms.ROOM_ONE;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.exceptions.DuplicateRoomException;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.exceptions.DuplicateStudentException;
import seedu.resireg.testutil.RoomBuilder;
import seedu.resireg.testutil.StudentBuilder;
import seedu.resireg.testutil.TypicalSemesters;

public class ResiRegTest {

    private final ResiReg resiReg = new ResiReg();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), resiReg.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resiReg.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyResiReg_replacesData() {
        ResiReg newData = getTypicalResiReg();
        resiReg.resetData(newData);
        assertEquals(newData, resiReg);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        ResiRegStub newData = new ResiRegStub(newStudents, new ArrayList<>());

        assertThrows(DuplicateStudentException.class, () -> resiReg.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resiReg.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInResiReg_returnsFalse() {
        assertFalse(resiReg.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInResiReg_returnsTrue() {
        resiReg.addStudent(ALICE);
        assertTrue(resiReg.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInResiReg_returnsTrue() {
        resiReg.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(resiReg.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> resiReg.getStudentList().remove(0));
    }


    // Rooms
    @Test
    public void resetData_withDuplicateRooms_throwsDuplicateRoomException() {
        // Two rooms with the same identity fields
        Room editedOne = new RoomBuilder(ROOM_ONE).withTags(VALID_TAG_RENOVATED).build();
        List<Room> newRooms = Arrays.asList(ROOM_ONE, editedOne);
        ResiRegStub newData = new ResiRegStub(new ArrayList<>(), newRooms);

        assertThrows(DuplicateRoomException.class, () -> resiReg.resetData(newData));
    }

    @Test
    public void hasRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resiReg.hasRoom(null));
    }

    @Test
    public void hasRoom_roomNotInResiReg_returnsFalse() {
        assertFalse(resiReg.hasRoom(ROOM_ONE));
    }

    @Test
    public void hasRoom_roomInResiReg_returnsTrue() {
        resiReg.addRoom(ROOM_ONE);
        assertTrue(resiReg.hasRoom(ROOM_ONE));
    }

    @Test
    public void hasRoom_roomWithSameIdentityFieldsInResiReg_returnsTrue() {
        resiReg.addRoom(ROOM_ONE);
        Room editedRoom = new RoomBuilder(ROOM_ONE).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_DAMAGED)
            .build();
        assertTrue(resiReg.hasRoom(editedRoom));
    }

    @Test
    public void getRoomList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> resiReg.getRoomList().remove(0));
    }

    /**
     * A stub ReadOnlyResiReg whose students list can violate interface constraints.
     */
    private static class ResiRegStub implements ReadOnlyResiReg {
        private final Semester semester = TypicalSemesters.AY2020_SEM_2;
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();
        private final ObservableList<Allocation> allocations = FXCollections.observableArrayList();

        ResiRegStub(Collection<Student> students, Collection<Room> rooms) {
            this.students.setAll(students); // todo: set rooms
            this.rooms.setAll(rooms);
        }

        @Override
        public Semester getSemester() {
            return semester;
        }

        @Override
        public String getSemesterString() {
            return semester.getShortRepresentation();
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Room> getRoomList() {
            return rooms;
        }

        @Override
        public ObservableList<Allocation> getAllocationList() {
            return allocations;
        }

        @Override
        public ObservableList<BinItem> getBinItemList() {
            return null;
        }

        @Override
        public void addListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
