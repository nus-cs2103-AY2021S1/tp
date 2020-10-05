package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAnimal.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Email;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;

public class JsonAdaptedAnimalTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "+651234";
    private static final String INVALID_SPECIES = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_SPECIES = BENSON.getSpecies().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAnimalDetails_returnsAnimal() throws Exception {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(BENSON);
        assertEquals(BENSON, animal.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(INVALID_NAME, VALID_ID, VALID_EMAIL, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(null, VALID_ID, VALID_EMAIL, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, INVALID_ID, VALID_EMAIL, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, null, VALID_EMAIL, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, INVALID_EMAIL, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, VALID_ID, null, VALID_SPECIES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_EMAIL, INVALID_SPECIES, VALID_TAGS);
        String expectedMessage = Species.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_EMAIL, VALID_SPECIES, invalidTags);
        assertThrows(IllegalValueException.class, animal::toModelType);
    }

}
