package seedu.address.storage.bidder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.bidderstorage.JsonAdaptedBidder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.storage.bidderstorage.JsonAdaptedBidder;

public class JsonAdaptedBidderTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_BIDDER_ID = "B-1";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_BIDDER_ID = "B1";

    @Test
    public void toModelType_validBidderDetails_returnsBidder() throws Exception {
        JsonAdaptedBidder bidder = new JsonAdaptedBidder(BENSON);
        assertEquals(BENSON, bidder.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBidder bidder =
                new JsonAdaptedBidder(INVALID_NAME, VALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bidder::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBidder bidder = new JsonAdaptedBidder(null, VALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bidder::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedBidder bidder =
                new JsonAdaptedBidder(VALID_NAME, INVALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bidder::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedBidder bidder = new JsonAdaptedBidder(VALID_NAME, null, VALID_BIDDER_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bidder::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedBidder bidder = new JsonAdaptedBidder(VALID_NAME, VALID_PHONE, INVALID_BIDDER_ID);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, bidder::toModelType);
    }

}
