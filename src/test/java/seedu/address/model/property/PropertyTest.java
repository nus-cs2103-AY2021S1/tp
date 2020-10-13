package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_ID_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_SELLER_ID_BEDOK;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

class PropertyTest {

    @Test
    void isSameProperty() {
        // same object -> returns true
        assertTrue(PROPERTY_A.isSameProperty(PROPERTY_A));

        // null -> returns false
        assertFalse(PROPERTY_A.isSameProperty(null));

        // different id and address -> return false
        Property editedProperty = new PropertyBuilder(PROPERTY_A)
                .withPropertyId(VALID_PROPERTY_PROPERTY_ID_BEDOK)
                .withAddress(VALID_PROPERTY_ADDRESS_BEDOK)
                .build();
        assertFalse(PROPERTY_A.isSameProperty(editedProperty));

        // same id, same address, different attributes -> return true
        editedProperty = new PropertyBuilder(PROPERTY_A)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsClosedDeal(VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .build();
        assertTrue(PROPERTY_A.isSameProperty(editedProperty));

        // same id only -> return true
        editedProperty = new PropertyBuilder(PROPERTY_A)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAddress(VALID_PROPERTY_ADDRESS_BEDOK)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsClosedDeal(VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .build();
        assertTrue(PROPERTY_A.isSameProperty(editedProperty));

        // same address only -> return true
        editedProperty = new PropertyBuilder(PROPERTY_A)
                .withPropertyId(VALID_PROPERTY_PROPERTY_ID_BEDOK)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsClosedDeal(VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .build();
        assertTrue(PROPERTY_A.isSameProperty(editedProperty));

    }

    @Test
    void testEquals() {

        // same values -> return true
        Property propertyACopy = new PropertyBuilder(PROPERTY_A).build();
        assertTrue(PROPERTY_A.equals(propertyACopy));

        // same object -> return true
        assertTrue(PROPERTY_A.equals(PROPERTY_A));

        // null -> return false;
        assertFalse(PROPERTY_A.equals(null));

        // different type -> return false
        assertFalse(PROPERTY_A.equals(5));

        // different property -> return false
        assertFalse(PROPERTY_A.equals(PROPERTY_B));

        // different property id -> return false
        Property editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withPropertyId(VALID_PROPERTY_PROPERTY_ID_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different property name -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different address -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withAddress(VALID_PROPERTY_ADDRESS_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different property type -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different seller id -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different asking price -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different isRental -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

        // different isClosedDeal -> return false
        editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withAddress(VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK)
                .build();
        assertFalse(PROPERTY_A.equals(editedPropertyA));

    }
}