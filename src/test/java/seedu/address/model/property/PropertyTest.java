package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;

import org.junit.jupiter.api.Test;

class PropertyTest {

    @Test
    void isSameProperty() {
        // same object -> returns true
        assertTrue(PROPERTY_A.isSameProperty(PROPERTY_A));

        // null -> returns false
        assertFalse(PROPERTY_A.isSameProperty(null));


    }

    @Test
    void testEquals() {
    }
}