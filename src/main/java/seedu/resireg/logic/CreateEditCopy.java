package seedu.resireg.logic;

import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

import java.util.Set;

/**
 * Contains functionality for "copy-constructing" {@code Student} and {@code Room}
 * to deal with persistence of student and room allocation statuses.
 */
public class CreateEditCopy {
    public static Student createCopiedStudent(Student studentToCopy) {
        assert studentToCopy != null;

        Name copiedName = studentToCopy.getName();
        Phone copiedPhone = studentToCopy.getPhone();
        Email copiedEmail = studentToCopy.getEmail();
        Faculty copiedFaculty = studentToCopy.getFaculty();
        StudentId copiedStudentId = studentToCopy.getStudentId();
        Set<Tag> copiedTags = studentToCopy.getTags();

        Student copy = new Student(copiedName, copiedPhone,
                copiedEmail, copiedFaculty, copiedStudentId, copiedTags);
        copy.setRoom(studentToCopy.getRoom());

        return copy;
    }

    public static Room createCopiedRoom(Room roomToCopy) {
        assert roomToCopy != null;

        Floor copiedFloor = roomToCopy.getFloor();
        RoomNumber copiedRoomNumber = roomToCopy.getRoomNumber();
        RoomType copiedRoomType = roomToCopy.getRoomType();
        Set<Tag> copiedTags = roomToCopy.getTags();

        Room copy = new Room(copiedFloor, copiedRoomNumber, copiedRoomType, copiedTags);
        copy.setStudent(roomToCopy.getStudent());

        return copy;
    }
}
