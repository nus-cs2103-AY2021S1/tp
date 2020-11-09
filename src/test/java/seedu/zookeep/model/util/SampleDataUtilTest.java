package seedu.zookeep.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.zookeep.model.animal.Animal;

public class SampleDataUtilTest {

    @Test
    public void sampleAnimalsExist() {
        Animal[] sampleAnimals = SampleDataUtil.getSampleAnimals();
        assertTrue(sampleAnimals.length > 0);
    }

}
