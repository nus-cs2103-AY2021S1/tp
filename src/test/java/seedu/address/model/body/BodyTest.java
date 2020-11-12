package seedu.address.model.body;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BodyTest {

    private final Body sampleBody = new Body();
    private final Body sampleBody2 = new Body();
    private final Height sampleHeight = new Height(170);
    private final Height sampleHeight2 = new Height(170);
    private final Height sampleHeight3 = new Height(190);
    private final Weight sampleWeight = new Weight(70);
    private final Weight sampleWeight2 = new Weight(70);
    private final Weight sampleWeight3 = new Weight(80);

    @Test
    public void isSameBody() {

        // Body with empty fields --> return True
        assertTrue(sampleBody.equals(sampleBody2));
        assertTrue(sampleBody.equals(sampleBody));
        assertFalse(sampleBody.equals(null));
        assertTrue(sampleBody.hashCode() == sampleBody.hashCode());
        assertFalse(sampleBody.hashCode() != sampleBody2.hashCode());

        // 1 body has height set, the other does not --> return False
        sampleBody.setHeight(sampleHeight);

        assertFalse(sampleBody.equals(sampleBody2));

        // Both body set same height --> return True
        sampleBody2.setHeight(sampleHeight);

        assertTrue(sampleBody.equals(sampleBody2));

        // 1 body has weight set, the other does not --> return False
        sampleBody.setWeight(sampleWeight);

        assertFalse(sampleBody.equals(sampleBody2));

        // Both body set same weight --> return True
        sampleBody2.setWeight(sampleWeight);

        assertTrue(sampleBody.equals(sampleBody2));

        // Both body set different weight, same height --> return False
        sampleBody2.setWeight(sampleWeight3);
        assertFalse(sampleBody.equals(sampleBody2));

        // Both body set different height, same weight --> return False
        sampleBody2.setWeight(sampleWeight);
        sampleBody2.setHeight(sampleHeight3);
        assertFalse(sampleBody.equals(sampleBody2));

    }

    @Test
    public void bodyGetterMethods() {
        sampleBody.setHeight(sampleHeight);
        sampleBody.setWeight(sampleWeight);
        sampleBody2.setHeight(sampleHeight2);
        sampleBody2.setWeight(sampleWeight2);

        assertTrue(sampleBody.getWeight().equals(sampleWeight));
        assertTrue(sampleBody.getHeight().equals(sampleHeight));
        assertTrue(sampleBody2.getWeight().equals(sampleWeight2));
        assertTrue(sampleBody2.getHeight().equals(sampleHeight2));

    }

    @Test
    public void bmiTest() {
        double bmi = sampleWeight.getWeight() / Math.pow((sampleHeight.getHeight() / 100.0), 2);
        sampleBody.setWeight(sampleWeight);
        sampleBody.setHeight(sampleHeight);
        assertTrue(bmi == sampleBody.getBmi());
    }

    @Test
    public void toStringTest() {
        assertTrue(sampleBody.toString().equals(sampleBody2.toString()));
        sampleBody.setHeight(sampleHeight);
        sampleBody.setWeight(sampleWeight);
        assertFalse(sampleBody.toString().equals(sampleBody2.toString()));
    }
}
