package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CLIENTSOURCE = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private static final String INVALID_NOTE = " ";
    private static final String INVALID_PRIORITY = "X";
    private static final String INVALID_POLICY_NAME = "L!fe Pl@n";
    private static final String INVALID_POLICY_DESCRIPTION = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedClientSource> VALID_CLIENTSOURCES = BENSON.getClientSources().stream()
            .map(JsonAdaptedClientSource::new)
            .collect(Collectors.toList());
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final boolean VALID_IS_ARCHIVE = false;
    private static final String VALID_PRIORITY = BENSON.getPriority().toString();
    private static final String VALID_POLICY_NAME = BENSON.getPolicy().getPolicyName().value;
    private static final String VALID_POLICY_DESCRIPTION = BENSON.getPolicy().getDescription().value;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_nullPhone_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                null,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        Person bensonWithoutPhone = new PersonBuilder(BENSON).withoutPhone().build();
        assertEquals(bensonWithoutPhone, person.toModelType());
    }

    @Test
    public void toModelType_nullEmail_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                null,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        Person bensonWithoutEmail = new PersonBuilder(BENSON).withoutEmail().build();
        assertEquals(bensonWithoutEmail, person.toModelType());
    }

    @Test
    public void toModelType_nullAddress_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                null,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        Person bensonWithoutAddress = new PersonBuilder(BENSON).withoutAddress().build();
        assertEquals(bensonWithoutAddress, person.toModelType());
    }

    @Test
    public void toModelType_nullNote_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                null,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        Person bensonWithoutNote = new PersonBuilder(BENSON).withoutNote().build();
        assertEquals(bensonWithoutNote, person.toModelType());
    }

    @Test
    public void toModelType_nullPriority_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                null,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        Person bensonWithoutPriority = new PersonBuilder(BENSON).withoutPriority().build();
        assertEquals(bensonWithoutPriority, person.toModelType());
    }

    @Test
    public void toModelType_nullPolicy_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                null);
        Person bensonWithoutPriority = new PersonBuilder(BENSON).withoutPolicy().build();
        assertEquals(bensonWithoutPriority, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                INVALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                INVALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                INVALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                INVALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidClientSources_throwsIllegalValueException() {
        List<JsonAdaptedClientSource> invalidClientSources = new ArrayList<>(VALID_CLIENTSOURCES);
        invalidClientSources.add(new JsonAdaptedClientSource(INVALID_CLIENTSOURCE));
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                invalidClientSources,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidNote_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                INVALID_NOTE,
                VALID_IS_ARCHIVE,
                VALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_CLIENTSOURCES,
                VALID_NOTE,
                VALID_IS_ARCHIVE,
                INVALID_PRIORITY,
                new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_POLICY_DESCRIPTION));
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
