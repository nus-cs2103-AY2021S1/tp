package seedu.address.model.exercise;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {

    private static final Calories CALORIES_1000 = new Calories("1000");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }
}
