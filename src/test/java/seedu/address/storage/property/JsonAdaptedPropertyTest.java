package seedu.address.storage.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_ID_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_SELLER_ID_ANCHORVALE;
import static seedu.address.storage.property.JsonAdaptedProperty.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

public class JsonAdaptedPropertyTest {

    public static final String INVALID_PROPERTY_PROPERTY_ID = "D1";
    public static final String INVALID_PROPERTY_NAME = "abc*&";
    public static final String INVALID_PROPERTY_IS_RENTAL = "blah";
    public static final String INVALID_PROPERTY_PROPERTY_TYPE = "abc&*";
    public static final String INVALID_PROPERTY_ASKING_PRICE = "-20";
    public static final String INVALID_PROPERTY_SELLER_ID = "D1";
    public static final String INVALID_PROPERTY_IS_CLOSED_DEAL = "hey";
    // no invalid address

    @Test
    public void toModelType_validPropertyDetails_returnsProperty() throws Exception {
        JsonAdaptedProperty property = new JsonAdaptedProperty(PROPERTY_A);
        assertEquals(PROPERTY_A, property.toModelType());
    }

    @Test
    public void toModelType_invalidPropertyId_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        INVALID_PROPERTY_PROPERTY_ID,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = PropertyId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPropertyId_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        null,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PropertyId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidPropertyName_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        INVALID_PROPERTY_NAME,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = PropertyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPropertyName_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        null,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PropertyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        "",
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        null,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidSellerId_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        INVALID_PROPERTY_SELLER_ID,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = SellerId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullSellerId_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        null,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SellerId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidPropertyType_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        INVALID_PROPERTY_PROPERTY_TYPE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = PropertyType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPropertyType_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        null,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PropertyType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidAskingPrice_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        INVALID_PROPERTY_ASKING_PRICE,
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_notNumberAskingPrice_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        "invalid price",
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }


    @Test
    public void toModelType_nullAskingPrice_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        null,
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidIsRental_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        INVALID_PROPERTY_IS_RENTAL,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = IsRental.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullIsRental_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        null,
                        VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IsRental.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidIsClosedDeal_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        INVALID_PROPERTY_IS_CLOSED_DEAL
                );
        String expectedMessage = IsClosedDeal.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullIsClosedDeal_throwsIllegalValueException() {
        JsonAdaptedProperty property =
                new JsonAdaptedProperty(
                        VALID_PROPERTY_PROPERTY_ID_ANCHORVALE,
                        VALID_PROPERTY_NAME_ANCHORVALE,
                        VALID_PROPERTY_ADDRESS_ANCHORVALE,
                        VALID_PROPERTY_SELLER_ID_ANCHORVALE,
                        VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE,
                        String.valueOf(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE),
                        VALID_PROPERTY_IS_RENTAL_ANCHORVALE,
                        null
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IsClosedDeal.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }





}
