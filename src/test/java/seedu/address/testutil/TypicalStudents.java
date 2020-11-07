package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALEX = new StudentBuilder()
            .withName("Alex Tan")
            .withEmail("alextan@u.nus.edu")
            .withPhone("91234567")
            .withTags("CS2103T")
            .withStudentId("A1234567X")
            .withAttendance("1", "2", "3", "6")
            .withParticipation("96")
            .build();

    public static final Student BENG = new StudentBuilder()
            .withName("Ah Beng")
            .withEmail("abeng@u.nus.edu")
            .withPhone("81234567")
            .withTags("CS2103T")
            .withStudentId("A7654321B")
            .withAttendance("1", "2", "5", "13")
            .build();

    // don't add attendance to this student
    public static final Student CHARLIE = new StudentBuilder()
            .withName("CHARLIE CHEN")
            .withEmail("charlie@hi.com")
            .withPhone("82223333")
            .withTags("CS2103T")
            .withStudentId("A1928835B")
            .build();

    public static final Student DAVID = new StudentBuilder()
            .withName("David Ong")
            .withEmail("dong@u.nus.edu")
            .withPhone("81320987")
            .withTags("CS2103T", "UTCP")
            .withStudentId("A1837538N")
            .withAttendance("1", "4", "3", "12")
            .withParticipation("24")
            .build();

    public static final Student ELIZABETH = new StudentBuilder()
            .withName("Elizabeth Teo")
            .withEmail("eteo@u.nus.edu")
            .withPhone("89993333")
            .withTags("CS2103T", "DDP")
            .withStudentId("A1938563M")
            .withParticipation("100")
            .build();

    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Chan")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .withAttendance("1", "2", "4", "10")
            .build();

    public static final Student ATTENDANCE_TEST = new StudentBuilder()
            .withName("Attendance Test")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .build();

    public static final Student ATTENDANCE_TEST_WEEK_2 = new StudentBuilder()
            .withName("Attendance Test")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .withAttendance("2")
            .build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static UniqueStudentList getTypicalStudentList() {
        UniqueStudentList sl = new UniqueStudentList();
        for (Student student : getTypicalStudents()) {
            sl.addStudent(student);
        }
        return sl;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALEX, BENG, CHARLIE, DAVID, ELIZABETH, FIONA));
    }
}
