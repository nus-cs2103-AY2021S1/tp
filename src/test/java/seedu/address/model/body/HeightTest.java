package seedu.address.model.body;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertFalse(Height.isValidHeight(-1));
        assertFalse(Height.isValidHeight(-1.0));
        assertFalse(Height.isValidHeight(0));
        assertFalse(Height.isValidHeight(-0));
        assertFalse(Height.isValidHeight(-1000));
        assertFalse(Height.isValidHeight(1000));
        assertFalse(Height.isValidHeight(90));
        assertFalse(Height.isValidHeight(251));

        assertTrue(Height.isValidHeight(170));
        assertTrue(Height.isValidHeight(190));
        assertTrue(Height.isValidHeight("170"));
        assertTrue(Height.isValidHeight("190"));
    }

    @Test
    public void toStringTest() {
        assertTrue(sampleHeight.toString().equals(sampleHeight.toString()));
        assertTrue(sampleHeight.toString().equals(sampleHeight2.toString()));
        assertFalse(sampleHeight.toString().equals(sampleHeight3.toString()));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(sampleHeight.hashCode() == sampleHeight.hashCode());
        assertFalse(sampleHeight.hashCode() == sampleHeight3.hashCode());
    }
}
