package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.question.Question;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String school;
    private final String year;

    @JsonProperty("admin")
    private final JsonAdaptedAdmin jsonAdaptedAdmin;

    @JsonProperty("questions")
    private final List<JsonAdaptedQuestion> jsonAdaptedQuestions = new ArrayList<>();

    @JsonProperty("exams")
    private final ArrayList<JsonAdaptedExam> jsonAdaptedExams = new ArrayList<>();

    @JsonProperty("academic")
    private final JsonAdaptedAcademic jsonAdaptedAcademic;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("school") String school, @JsonProperty("year") String year,
                              @JsonProperty("admin") JsonAdaptedAdmin admin,
                              @JsonProperty("questions") List<JsonAdaptedQuestion> questions,
                              @JsonProperty("exams") ArrayList<JsonAdaptedExam> exams,
                              @JsonProperty("academic") JsonAdaptedAcademic academic) {
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.jsonAdaptedAdmin = admin;
        if (questions != null) {
            this.jsonAdaptedQuestions.addAll(questions);
        }
        if (exams != null) {
            this.jsonAdaptedExams.addAll(exams);
        }
        this.jsonAdaptedAcademic = academic;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        school = source.getSchool().school;
        year = String.valueOf(source.getYear().toString());
        jsonAdaptedAdmin = new JsonAdaptedAdmin(source.getAdmin());

        jsonAdaptedQuestions.addAll(source.getQuestions().stream()
                .map(JsonAdaptedQuestion::new)
                .collect(Collectors.toList()));

        jsonAdaptedExams.addAll(source.getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));

        jsonAdaptedAcademic = new JsonAdaptedAcademic(source.getAcademic());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {

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

        if (school == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    School.class.getSimpleName()));
        }
        if (!School.isValidSchool(school)) {
            throw new IllegalValueException(School.MESSAGE_CONSTRAINTS);
        }
        final School modelSchool = new School(school);

        if (year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Year.class.getSimpleName()));
        }
        if (!Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        final Year modelYear = ParserUtil.parseYear(year);

        Admin admin = jsonAdaptedAdmin.toModelType();

        List<Question> questions = new ArrayList<>();
        for (JsonAdaptedQuestion question : jsonAdaptedQuestions) {
            questions.add(question.toModelType());
        }

        ArrayList<Exam> exams = new ArrayList<>();
        for (JsonAdaptedExam exam : jsonAdaptedExams) {
            exams.add(exam.toModelType());
        }

        Academic academic = jsonAdaptedAcademic.toModelType();
        return new Student(modelName, modelPhone, modelSchool, modelYear, admin, questions, exams, academic);
    }

}
