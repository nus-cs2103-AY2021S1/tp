package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFriend.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalFriends.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;

public class JsonAdaptedFriendTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PASSPORT = "fddf934734";
    private static final String INVALID_PHONE = "+39347384";

    private static final String VALID_NAME = ALICE.getName().getValue();
    private static final String VALID_PASSPORT = ALICE.getPassport().getValue();
    private static final String VALID_PHONE = ALICE.getMobile().getValue();

    @Test
    public void toModelType_validFriendDetails_returnsFriend() throws Exception {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(ALICE);
        assertEquals(ALICE, friend.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(INVALID_NAME, VALID_PASSPORT, VALID_PHONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(null, VALID_PASSPORT, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }

    @Test
    public void toModelType_invalidPassport_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(VALID_NAME, INVALID_PASSPORT, VALID_PHONE);
        String expectedMessage = Passport.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }

    @Test
    public void toModelType_nullPassport_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(VALID_NAME, null, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Passport.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(VALID_NAME, VALID_PASSPORT, INVALID_PHONE);
        String expectedMessage = Mobile.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedFriend friend = new JsonAdaptedFriend(VALID_NAME, VALID_PASSPORT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Mobile.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, friend::toModelType);
    }
}
