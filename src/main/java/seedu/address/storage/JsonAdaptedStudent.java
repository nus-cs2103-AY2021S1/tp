package seedu.address.storage;

import static seedu.address.model.person.Attendance.MAX_WEEK;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String studentId;
    private final List<JsonAdaptedAttendance> attendance = new ArrayList<>();
    private final String participationScore;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("studentId") String studentId,
                              @JsonProperty("attendance") List<JsonAdaptedAttendance> attendance,
                              @JsonProperty("participationScore") String participationScore) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.studentId = studentId;
        this.participationScore = participationScore;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (attendance != null) {
            this.attendance.addAll(attendance);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        studentId = source.getStudentId().toString();
        participationScore = source.getAttendance().getParticipationScoreAsString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        boolean[] attendanceRecords = source.getAttendance().getIsPresent();
        for (int i = 0; i < attendanceRecords.length; i++) {
            JsonAdaptedAttendance jsonAdaptedAttendance;
            if (attendanceRecords[i]) {
                String weekNum = String.valueOf(i + 1);
                jsonAdaptedAttendance = new JsonAdaptedAttendance(weekNum);
                attendance.add(jsonAdaptedAttendance);
            }
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        boolean[] attendanceRecords = new boolean[MAX_WEEK];
        for (JsonAdaptedAttendance attendanceRecord : attendance) {
            int weekNumToArrIndex = Integer.parseInt(attendanceRecord.getWeekNum()) - 1;
            attendanceRecords[weekNumToArrIndex] = true;
        }
        if (participationScore == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName())
            );
        }
        if (!Attendance.isValidParticipation(participationScore)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        Attendance modelAttendance = new Attendance();
        modelAttendance.setIsPresent(attendanceRecords);
        modelAttendance.editParticipation(participationScore);


        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelPhone, modelEmail, modelTags, modelStudentId, modelAttendance);
    }

}
