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
import static seedu.resireg.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.exceptions.DuplicateRoomException;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.exceptions.DuplicateStudentException;
import seedu.resireg.testutil.RoomBuilder;
import seedu.resireg.testutil.StudentBuilder;
import seedu.resireg.testutil.TypicalSemesters;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents, new ArrayList<>());

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }


    // Rooms
    @Test
    public void resetData_withDuplicateRooms_throwsDuplicateRoomException() {
        // Two rooms with the same identity fields
        Room editedOne = new RoomBuilder(ROOM_ONE).withTags(VALID_TAG_RENOVATED).build();
        List<Room> newRooms = Arrays.asList(ROOM_ONE, editedOne);
        AddressBookStub newData = new AddressBookStub(new ArrayList<>(), newRooms);

        assertThrows(DuplicateRoomException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRoom(null));
    }

    @Test
    public void hasRoom_roomNotInResiReg_returnsFalse() {
        assertFalse(addressBook.hasRoom(ROOM_ONE));
    }

    @Test
    public void hasRoom_roomInResiReg_returnsTrue() {
        addressBook.addRoom(ROOM_ONE);
        assertTrue(addressBook.hasRoom(ROOM_ONE));
    }

    @Test
    public void hasRoom_roomWithSameIdentityFieldsInResiReg_returnsTrue() {
        addressBook.addRoom(ROOM_ONE);
        Room editedRoom = new RoomBuilder(ROOM_ONE).withRoomType(VALID_ROOM_TYPE_B).withTags(VALID_TAG_DAMAGED)
            .build();
        assertTrue(addressBook.hasRoom(editedRoom));
    }

    @Test
    public void getRoomList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRoomList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final Semester semester = TypicalSemesters.AY2020_SEM_2;
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();
        private final ObservableList<Allocation> allocations = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students, Collection<Room> rooms) {
            this.students.setAll(students);
            this.rooms.setAll(rooms);
        }

        @Override
        public Semester getSemester() {
            return semester;
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
    }

}
