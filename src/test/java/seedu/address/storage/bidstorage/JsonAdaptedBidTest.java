package seedu.address.storage.bidstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BIDDER_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BID_AMOUNT_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_PROPERTY_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_AMOUNT_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_A;
import static seedu.address.storage.bidstorage.JsonAdaptedBid.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bids.TypicalBid.BID_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;


public class JsonAdaptedBidTest {

    @Test
    public void toModelType_validBidDetails_returnsBid() throws Exception {
        JsonAdaptedBid bid = new JsonAdaptedBid(BID_A);
        assertEquals(BID_A, bid.toModelType());
    }


    @Test
    public void toModelType_invalidBidDetails_throwsIllegalValueException() {
        JsonAdaptedBid bid =
                new JsonAdaptedBid(INVALID_PROPERTY_ID_BID_A, INVALID_BIDDER_ID_BID_A, INVALID_BID_AMOUNT_BID_A);
        String expectedMessage = PropertyId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_nullPropertyId_throwsIllegalValueException() {
        JsonAdaptedBid bid = new JsonAdaptedBid(null, VALID_BIDDER_ID_BID_A, VALID_BID_AMOUNT_BID_A);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "propertyId");
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_nullBidderId_throwsIllegalValueException() {
        JsonAdaptedBid bid = new JsonAdaptedBid(VALID_PROPERTY_ID_BID_A, null, VALID_BID_AMOUNT_BID_A);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "bidderId");
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_invalidBidderId_throwsIllegalValueException() {
        JsonAdaptedBid bid =
                new JsonAdaptedBid(VALID_PROPERTY_ID_BID_A, INVALID_BIDDER_ID_BID_A, VALID_BID_AMOUNT_BID_A);
        String expectedMessage = BidderId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }

    @Test
    public void toModelType_invalidBidAmount_throwsIllegalValueException() {
        JsonAdaptedBid bid = new JsonAdaptedBid(VALID_PROPERTY_ID_BID_A,
                VALID_BIDDER_ID_BID_A, INVALID_BID_AMOUNT_BID_A);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "bidAmount");
        assertThrows(IllegalValueException.class, expectedMessage, bid::toModelType);
    }
}
