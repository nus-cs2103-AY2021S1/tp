package seedu.address.model.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WeightTest {
    private final Weight sampleWeight = new Weight(70.0);
    private final Weight sampleWeight2 = new Weight(70.0);
    private final Weight sampleWeight3 = new Weight(80.0);


    @Test
    public void isSameWeight() {

        assertTrue(sampleWeight.equals(sampleWeight2));

        assertFalse(sampleWeight.equals(sampleWeight3));

        assertTrue(sampleWeight.getWeight() == 70.0);
        assertTrue(sampleWeight2.getWeight() == 70.0);
        assertTrue(sampleWeight3.getWeight() == 80.0);

    }

    @Test
    public void validityChecks() {
        // invalid format checks -> returns false
        assertFalse(Weight.isValidWeight("-1"));
        assertFalse(Weight.isValidWeight("50.000"));
        assertFalse(Weight.isValidWeight("50.0.0"));

        // valid format checks -> returns true
        assertTrue(Weight.isValidWeight("70"));
        assertTrue(Weight.isValidWeight("70.0"));
        assertTrue(Weight.isValidWeight("70.00"));

        // invalid range checks -> returns false
        assertFalse(Weight.isValidWeight(-1));
        assertFalse(Weight.isValidWeight(-1.0));
        assertFalse(Weight.isValidWeight(0));
        assertFalse(Weight.isValidWeight(-0));
        assertFalse(Weight.isValidWeight(-1000));
        assertFalse(Weight.isValidWeight(1000));
        assertFalse(Weight.isValidWeight(200));
        assertFalse(Weight.isValidWeight(30));

        // valid range checks -> returns true
        assertTrue(Weight.isValidWeight(70));
        assertTrue(Weight.isValidWeight(31));
        assertTrue(Weight.isValidWeight(199));
    }

    @Test
    public void toStringTest() {
        assertEquals(sampleWeight.toString(), sampleWeight.toString());
        assertEquals(sampleWeight2.toString(), sampleWeight.toString());
        assertNotEquals(sampleWeight3.toString(), sampleWeight.toString());

    }

    @Test
    public void hashCodeTest() {
        assertEquals(sampleWeight.hashCode(), sampleWeight.hashCode());
        assertNotEquals(sampleWeight3.hashCode(), sampleWeight.hashCode());
    }
}
