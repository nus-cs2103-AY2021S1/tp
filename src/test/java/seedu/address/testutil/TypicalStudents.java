package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Student;

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
            .build();

    public static final Student BENG = new StudentBuilder()
            .withName("Ah Beng")
            .withEmail("abeng@u.nus.edu")
            .withPhone("81234567")
            .withTags("CS2103T")
            .withStudentId("A7654321B")
            .build();

    public static final Student CHARLIE = new StudentBuilder()
            .withName("CHARLIE CHEN")
            .withEmail("ccharlie@u.nus.edu")
            .withPhone("82223333")
            .withTags("CS2103T")
            .withStudentId("A1928835B")
            .build();

    public static final Student DAVID = new StudentBuilder()
            .withName("David Ong")
            .withEmail("dong@u.nus.edu")
            .withPhone("81320987")
            .withTags("CS2103T")
            .withStudentId("A1837538N")
            .build();

    public static final Student ELIZABETH = new StudentBuilder()
            .withName("Elizabeth Teo")
            .withEmail("eteo@u.nus.edu")
            .withPhone("89993333")
            .withTags("CS2103T")
            .withStudentId("A1938563M")
            .build();

    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Chan")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .build();

    private TypicalStudents() {} // prevents instantiation

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALEX, BENG, CHARLIE, DAVID, ELIZABETH));
    }
}
