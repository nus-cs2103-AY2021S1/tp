package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.model.animal.Animal;


public class SampleDataUtilTest {

    @Test
    public void sampleAnimalsExist() {
        Animal[] sampleAnimals = SampleDataUtil.getSampleAnimals();
        assertTrue(sampleAnimals.length > 0);
    }

}
