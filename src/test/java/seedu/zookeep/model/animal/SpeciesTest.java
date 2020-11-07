package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpeciesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Species(null));
    }

    @Test
    public void constructor_invalidSpecies_throwsIllegalArgumentException() {
        String invalidSpecies = "";
        assertThrows(IllegalArgumentException.class, () -> new Species(invalidSpecies));
    }

    @Test
    public void isValidSpecies() {
        // null species
        assertThrows(NullPointerException.class, () -> Species.isValidSpecies(null));

        // invalid species
        assertFalse(Species.isValidSpecies("")); // empty string
        assertFalse(Species.isValidSpecies(" ")); // spaces only

        // valid species
        assertTrue(Species.isValidSpecies("Ailuropoda melanoleuca"));
        assertTrue(Species.isValidSpecies("-")); // one character
        assertTrue(Species.isValidSpecies("Parastratiosphecomyia stratiosphecomyioides")); // long species
    }

    @Test
    public void hashcode() {
        Species testSpecies = new Species("Python");

        // same values -> returns same hashcode
        assertEquals(testSpecies.hashCode(), new Species("Python").hashCode());

        // different values -> returns different hashcode
        assertNotEquals(testSpecies.hashCode(), new Species("Chihuahua").hashCode());
    }
}
