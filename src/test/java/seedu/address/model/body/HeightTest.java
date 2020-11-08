package seedu.address.model.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HeightTest {

    private final Height sampleHeight = new Height(170.0);
    private final Height sampleHeight2 = new Height(170.0);
    private final Height sampleHeight3 = new Height(180.0);


    @Test
    public void isSameHeight() {

        assertTrue(sampleHeight.equals(sampleHeight2));

        assertFalse(sampleHeight.equals(sampleHeight3));

        assertTrue(sampleHeight.getHeight() == 170.0);
        assertTrue(sampleHeight2.getHeight() == 170.0);
        assertTrue(sampleHeight3.getHeight() == 180.0);

    }

    @Test
    public void validityChecks() {
        // invalid format checks -> returns false
        assertFalse(Height.isValidHeight("-1"));
        assertFalse(Height.isValidHeight("170.000"));
        assertFalse(Height.isValidHeight("170.00.0"));

        // valid format checks -> returns true
        assertTrue(Height.isValidHeight("170"));
        assertTrue(Height.isValidHeight("170.0"));
        assertTrue(Height.isValidHeight("170.00"));

        // invalid range checks -> returns false
        assertFalse(Height.isValidHeight(-1));
        assertFalse(Height.isValidHeight(-1.0));
        assertFalse(Height.isValidHeight(0));
        assertFalse(Height.isValidHeight(-0));
        assertFalse(Height.isValidHeight(-1000));
        assertFalse(Height.isValidHeight(1000));
        assertFalse(Height.isValidHeight(100));
        assertFalse(Height.isValidHeight(250));

        // valid range checks -> returns true
        assertTrue(Height.isValidHeight(101));
        assertTrue(Height.isValidHeight(190));
        assertTrue(Height.isValidHeight(249));
    }

    @Test
    public void toStringTest() {
        assertEquals(sampleHeight.toString(), sampleHeight.toString());
        assertEquals(sampleHeight2.toString(), sampleHeight.toString());
        assertNotEquals(sampleHeight3.toString(), sampleHeight.toString());

    }

    @Test
    public void hashCodeTest() {
        assertEquals(sampleHeight.hashCode(), sampleHeight.hashCode());
        assertNotEquals(sampleHeight3.hashCode(), sampleHeight.hashCode());
    }
}
