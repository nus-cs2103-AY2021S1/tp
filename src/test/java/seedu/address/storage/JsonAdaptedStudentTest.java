package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SCHOOL = "Ros#th Primary";

    private static final String VALID_NAME = BOB.getName().toString();
    private static final String VALID_PHONE = BOB.getPhone().toString();
    private static final String VALID_SCHOOL = BOB.getSchool().toString();
    private static final String VALID_YEAR = BOB.getYear().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(BOB);
        assertEquals(BOB, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_SCHOOL, VALID_YEAR);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_PHONE, VALID_SCHOOL, VALID_YEAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_SCHOOL, VALID_YEAR);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, null, VALID_SCHOOL, VALID_YEAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSchool_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_SCHOOL, VALID_YEAR);
        String expectedMessage = School.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSchool_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_YEAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, School.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
