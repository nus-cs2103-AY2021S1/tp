package seedu.address.storage.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.sellerstorage.JsonAdaptedSeller.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.storage.sellerstorage.JsonAdaptedSeller;

public class JsonAdaptedSellerTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_BIDDER_ID = "B-1";

    private static final String VALID_NAME = SELLER_BENSON.getName().toString();
    private static final String VALID_PHONE = SELLER_BENSON.getPhone().toString();
    private static final String VALID_BIDDER_ID = "B1";

    @Test
    public void toModelType_validSellerDetails_returnsSeller() throws Exception {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(SELLER_BENSON);
        assertEquals(SELLER_BENSON, seller.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(INVALID_NAME, VALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(null, VALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, INVALID_PHONE, VALID_BIDDER_ID);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, null, VALID_BIDDER_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, INVALID_BIDDER_ID);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, seller::toModelType);
    }
}
