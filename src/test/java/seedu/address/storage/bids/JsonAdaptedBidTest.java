package seedu.address.storage.bids;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.bids.TypicalBid.BID_A;

import org.junit.jupiter.api.Test;

import seedu.address.storage.bidstorage.JsonAdaptedBid;


public class JsonAdaptedBidTest {

    private static final String INVALID_PROPERTY_ID = "M12";
    private static final String INVALID_BIDDER_ID = "U0932";
    private static final double INVALID_BID_AMOUNT = -1395.20;


    private static final String VALID_PROPERTY_ID = "P12";
    private static final String VALID_BIDDER_ID = "B32";
    private static final double VALID_BID_AMOUNT = 1395.20;

    @Test
    public void toModelType_validBidDetails_returnsBid() throws Exception {
        JsonAdaptedBid bid = new JsonAdaptedBid(BID_A);
        assertEquals(BID_A, bid.toModelType());
    }
    /*

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBid bid =
                new JsonAdaptedBid(INVALID_NAME, VALID_PHONE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBid bid = new JsonAdaptedBid(null, VALID_BIDDER_ID, VALID_BID_AMOUNT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedBid Bid =
                new JsonAdaptedBid(VALID_NAME, INVALID_PHONE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, Bid::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedBid Bid = new JsonAdaptedBid(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimplePropertyId());
        assertThrows(IllegalValueException.class, expectedMessage, Bid::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBid bid =
                new JsonAdaptedBid(VALID_NAME, VALID_PHONE, invalidTags);
        assertThrows(IllegalValueException.class, bid::toModelType);
    }

 */
}
