package seedu.address.storage;

// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.Admin;
// import seedu.address.model.student.admin.AdditionalDetail;
// import seedu.address.model.student.admin.ClassTime;
// import seedu.address.model.student.admin.ClassVenue;


/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String school;
    private final String year;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("school") String school, @JsonProperty("year") String year) {
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        school = source.getSchool().school;
        year = String.valueOf(source.getYear().year);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        //   final List<AdditionalDetail> personTags = new ArrayList<>();

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
        final Year modelYear = new Year(year);

        // Please implement Admin
        return new Student(modelName, modelPhone, modelSchool, modelYear, Admin.getPlaceholder(), new ArrayList<>());
    }

}
