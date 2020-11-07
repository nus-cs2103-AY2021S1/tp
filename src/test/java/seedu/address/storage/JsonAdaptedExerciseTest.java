package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.SQUATS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.util.Name;

public class JsonAdaptedExerciseTest {

    private static final String INVALID_NAME = "Squats$$";
    private static final String INVALID_TAG = "#tiring";

    private static final String VALID_NAME = SQUATS.getName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = SQUATS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_nullExerciseName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidExerciseName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(INVALID_NAME, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, invalidTags);
        assertThrows(IllegalValueException.class, exercise::toModelType);
    }

    @Test
    public void toModelType_validExerciseDetails_success() throws Exception {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(SQUATS);
        assertEquals(SQUATS, exercise.toModelType());
    }
}
