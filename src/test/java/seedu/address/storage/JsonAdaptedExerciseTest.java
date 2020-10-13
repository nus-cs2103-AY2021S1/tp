package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Name;

/*
Test for invalid input are commented out due to the use of stubs
 */
public class JsonAdaptedExerciseTest {
    private static final String INVALID_NAME = "P@ss Fail";
    /*
    private static final String INVALID_DESCRIPTION = "+651234";
    private static final String INVALID_DATE = " ";
    private static final String INVALID_CALORIES = "@@@@@";
     */

    private static final String VALID_NAME = PUSH_UP.getName().toString();
    private static final String VALID_DESCRIPTION = PUSH_UP.getDescription().toString();
    private static final String VALID_DATE = PUSH_UP.getDate().toString();
    private static final String VALID_CALORIES = PUSH_UP.getCalories().toString();

    /* Unable to return due to use of Exercise Stub
    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedExercise person = new JsonAdaptedExercise(PUSH_UP);
        assertEquals(PUSH_UP, person.toModelType());
    }
     */

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExercise person =
                new JsonAdaptedExercise(INVALID_NAME, VALID_DESCRIPTION, VALID_DATE, VALID_CALORIES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExercise person = new JsonAdaptedExercise(null, VALID_DESCRIPTION, VALID_DATE, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /*
    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExercise person =
                new JsonAdaptedExercise(VALID_NAME, INVALID_DESCRIPTION, VALID_DATE, VALID_CALORIES);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
     */

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExercise person = new JsonAdaptedExercise(VALID_NAME, null, VALID_DATE, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    /*
    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExercise person =
                new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION, INVALID_DATE, VALID_CALORIES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
     */

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExercise person = new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION, null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /*
    @Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedExercise person =
                new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION, VALID_DATE, INVALID_CALORIES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

     */

    @Test
    public void toModelType_nullCalories_throwsIllegalValueException() {
        JsonAdaptedExercise person = new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION, VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Calories.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
