package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MESSAGE_DUPLICATE_DAY;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;
import seedu.address.testutil.TypicalDays;
import seedu.address.testutil.TypicalPerson;
import seedu.address.testutil.TypicalProfiles;

public class JsonAdaptedPersonTest {
    private static final Profile VALID_PROFILE = TypicalProfiles.PROFILE1;
    private static final List<JsonAdaptedDay> VALID_DAYS = TypicalDays.getTypicalDays().stream()
            .map(JsonAdaptedDay::new).collect(Collectors.toList());
    private static final Person VALID_PERSON = TypicalPerson.PERSON1;

    private static final List<JsonAdaptedDay> INVALID_DAYS = TypicalDays.getDuplicateDays().stream()
            .map(JsonAdaptedDay::new).collect(Collectors.toList());;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_PERSON);
        assertEquals(VALID_PERSON, person.toModelType());
    }

    @Test
    public void toModelType_nullProfile_throwsIllegalValueException() {

        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Profile.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDays_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(new JsonAdaptedProfile(VALID_PROFILE),
                INVALID_DAYS);
        String expectedMessage = MESSAGE_DUPLICATE_DAY;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
