package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void optionalConstructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDes = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Description(Optional.of(invalidDes)));
    }

    @Test
    public void stringConstructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDes = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDes));
    }

    @Test
    public void isValidDescription() {
        // Empty -> true
        assertTrue(Description.isValidDescription(""));

        // alphabets -> true
        assertTrue(Description.isValidDescription("description"));

        // numbers -> true
        assertTrue(Description.isValidDescription("12345"));

        // special characters -> true
        assertTrue(Description.isValidDescription("!@#$%.,?"));
    }

    public void descriptionToString() {

        // empty description -> "-"
        Description desc = new Description("");
        assertEquals(desc.toString(), "-");

        // non-empty description -> description
        desc = new Description("contacting");
        assertEquals(desc.toString(), "contacting");
    }
}
