package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class HeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    void isValidHeight() {
        assertThrows(NullPointerException.class, () -> Height.isValidHeight(null));

        assertTrue(Height.isValidHeight("170"));
        assertTrue(Height.isValidHeight("200"));
        assertFalse(Height.isValidHeight("1700"));
        assertFalse(Height.isValidHeight("17"));
        assertFalse(Height.isValidHeight(" "));
    }

}
