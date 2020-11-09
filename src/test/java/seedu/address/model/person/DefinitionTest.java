package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DefinitionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Definition(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDefinition = "";
        assertThrows(IllegalArgumentException.class, () -> new Definition(invalidDefinition));
    }

    @Test
    public void isValidDefinition() {
        // null address
        assertThrows(NullPointerException.class, () -> Definition.isValidDefinition(null));

        // invalid addresses
        assertFalse(Definition.isValidDefinition("")); // empty string
        assertFalse(Definition.isValidDefinition(" ")); // spaces only

        // valid addresses
        assertTrue(Definition.isValidDefinition("OOP stands for Object-Oriented Programming."));
        assertTrue(Definition.isValidDefinition("-")); // one character
        assertTrue(Definition
                .isValidDefinition("A dictionary is a collection of (key,value) pairs.")); // long definition
    }
}
