package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * An utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_STUDENT_ID = "A1234567X";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private StudentId studentId;
    private Attendance attendance;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        attendance = new Attendance();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        tags = new HashSet<>(studentToCopy.getTags());
        studentId = studentToCopy.getStudentId();
        attendance = studentToCopy.getAttendance();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String... weekNumbers) {
        Attendance newAttendance = new Attendance();
        for (String weekNumber : weekNumbers) {
            newAttendance.addAttendance(weekNumber);
        }
        newAttendance.setParticipation(attendance.getParticipationScoreAsString());
        attendance = newAttendance;
        return this;
    }

    /**
     * Sets the participation score of the {@code Student} that we are building.
     */
    public StudentBuilder withParticipation(String participationScoreAsString) {
        attendance.setParticipation(participationScoreAsString);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, tags, studentId, attendance);
    }
}
