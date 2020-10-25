package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.PropertyId;


class PropertyIdTest {

    @Test
    void testToString() {
        PropertyId propertyId = new PropertyId("P1");
        String propertyIdTest = "P1";
        assert (propertyId.toString().equals("P1"));
        assertTrue(propertyIdTest.equals(propertyId.toString()));

        PropertyId propertyIdError = new PropertyId("P1");
        String propertyIdDiff = "P2";
        assertFalse(propertyIdDiff.equals(propertyIdError.toString()));
    }

    @Test
    void testEquals() {
        PropertyId propertyId = new PropertyId("P1");
        PropertyId propertyIdTest = new PropertyId("P1");
        assertTrue(propertyIdTest.equals(propertyId));

        PropertyId propertyIdError = new PropertyId("P1");
        PropertyId propertyIdDiff = new PropertyId("P2");
        assertFalse(propertyIdDiff.equals(propertyIdError));
    }
}
