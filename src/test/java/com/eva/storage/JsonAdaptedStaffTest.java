package com.eva.storage;

import static com.eva.storage.JsonAdaptedStaff.MISSING_FIELD_MESSAGE_FORMAT;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;


public class JsonAdaptedStaffTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_COMMENT = "";

    private static final Staff STAFF_BENSON = new Staff(BENSON, new HashSet<>());

    private static final String VALID_NAME = STAFF_BENSON.getName().toString();
    private static final String VALID_PHONE = STAFF_BENSON.getPhone().toString();
    private static final String VALID_EMAIL = STAFF_BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = STAFF_BENSON.getAddress().toString();
    private static final String VALID_LEAVE_TAKEN = STAFF_BENSON.getLeaveTaken().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS =
            STAFF_BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedComment> VALID_COMMENT =
            STAFF_BENSON.getComments().stream()
                    .map(JsonAdaptedComment::new)
                    .collect(Collectors.toList());
    private static final List<JsonAdaptedLeave> VALID_LEAVES =
            STAFF_BENSON.getLeaves().stream()
            .map(JsonAdaptedLeave::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStaffDetails_returnsStaff() throws Exception {
        JsonAdaptedStaff person = new JsonAdaptedStaff(STAFF_BENSON);
        assertEquals(STAFF_BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStaff person = new JsonAdaptedStaff(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStaff person = new JsonAdaptedStaff(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStaff person = new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStaff person = new JsonAdaptedStaff(VALID_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_LEAVE_TAKEN, VALID_TAGS, VALID_LEAVES, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_LEAVE_TAKEN, invalidTags, VALID_LEAVES, VALID_COMMENT);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
