package seedu.resireg.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.student.*;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String faculty;
    private final String studentId;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private JsonAdaptedRoom room;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("faculty") String faculty,
                              @JsonProperty("studentId") String studentId,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("room") JsonAdaptedRoom room) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
        this.studentId = studentId;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (room != null) {
            this.room = room;
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        faculty = source.getFaculty().value;
        studentId = source.getStudentId().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        if (source.hasRoom()) {
            room = new JsonAdaptedRoom(source.getRoom());
        }
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }

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

        if (faculty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName()));
        }
        if (!Faculty.isValidFaculty(faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        final Faculty modelFaculty = new Faculty(faculty);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        final Set<Tag> modelTags = new HashSet<>(studentTags);

        Student newStudent = new Student(modelName, modelPhone, modelEmail, modelFaculty, modelStudentId, modelTags);
        if (room != null) {
            newStudent.setRoom(room.toModelType());
        }
        return newStudent;
    }

}
