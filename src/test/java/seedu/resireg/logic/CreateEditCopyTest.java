package seedu.resireg.logic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;
import seedu.resireg.testutil.RoomBuilder;
import seedu.resireg.testutil.StudentBuilder;

class CreateEditCopyTest {

    @Test
    void createCopiedStudent() {
        Student dennis = new StudentBuilder().withName("Dennis").build();
        Student dennisCopy = CreateEditCopy.createCopiedStudent(dennis);

        // not same object -> returns false
        assertFalse(dennis == dennisCopy);

        // same values -> returns true
        assertTrue(dennis.equals(dennisCopy));
    }

    @Test
    void createCopiedRoom() {
        Room nice = new RoomBuilder().withFloor("8").withRoomNumber("110").build();
        Room niceCopy = CreateEditCopy.createCopiedRoom(nice);

        // not same object -> returns false
        assertFalse(nice == niceCopy);

        // same values -> returns true
        assertTrue(nice.equals(niceCopy));
    }
}
