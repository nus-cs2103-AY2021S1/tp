package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AnimalComparatorTest {

    @Test
    public void equals() {
        AnimalComparator animalNameComparator = AnimalComparator.createAnimalNameComparator();
        AnimalComparator animalIdComparator = AnimalComparator.createAnimalIdComparator();

        // same object -> returns true
        assertTrue(animalNameComparator.equals(animalNameComparator));

        // different types -> returns false
        assertFalse(animalNameComparator.equals(1));

        // null -> returns false
        assertFalse(animalNameComparator.equals(null));

        // different comparator -> returns false
        assertFalse(animalNameComparator.equals(animalIdComparator));
    }
}
