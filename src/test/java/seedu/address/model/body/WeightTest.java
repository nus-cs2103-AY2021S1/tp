package seedu.address.model.body;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertFalse(Weight.isValidWeight(-1));
        assertFalse(Weight.isValidWeight(-1.0));
        assertFalse(Weight.isValidWeight(0));
        assertFalse(Weight.isValidWeight(-0));
        assertFalse(Weight.isValidWeight(-1000));
        assertFalse(Weight.isValidWeight(1000));
        assertFalse(Weight.isValidWeight(251));
        assertFalse(Weight.isValidWeight(25));

        assertTrue(Weight.isValidWeight(70));
        assertTrue(Weight.isValidWeight(110));
        assertTrue(Weight.isValidWeight("70"));
        assertTrue(Weight.isValidWeight("110"));
    }

    @Test
    public void toStringTest() {
        assertTrue(sampleWeight.toString().equals(sampleWeight.toString()));
        assertTrue(sampleWeight.toString().equals(sampleWeight2.toString()));
        assertFalse(sampleWeight.toString().equals(sampleWeight3.toString()));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(sampleWeight.hashCode() == sampleWeight.hashCode());
        assertFalse(sampleWeight.hashCode() == sampleWeight3.hashCode());
    }
}
