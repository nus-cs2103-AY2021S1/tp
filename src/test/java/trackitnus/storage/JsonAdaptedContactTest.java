package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackitnus.storage.JsonAdaptedContact.MISSING_FIELD_MESSAGE_FORMAT;
import static trackitnus.testutil.typical.TypicalContacts.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.testutil.Assert;

public class JsonAdaptedContactTest {
    private static final String INVALID_NAME = "R/chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().orElseThrow(NullPointerException::new).toString();
    private static final String VALID_EMAIL = BENSON.getEmail().orElseThrow(NullPointerException::new).toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validContactDetails_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(BENSON);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedContact contact =
            new JsonAdaptedContact(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact =
            new JsonAdaptedContact(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

//    @Test TODO: modify this to be a positive test
//    public void toModelType_nullPhone_success() {
//        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, null, VALID_EMAIL, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
//    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact =
            new JsonAdaptedContact(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

//    @Test TODO: modify this to be a positive test
//    public void toModelType_nullEmail_throwsIllegalValueException() {
//        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, VALID_PHONE, null, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
//    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedContact contact =
            new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags);
        Assert.assertThrows(IllegalValueException.class, contact::toModelType);
    }

}
