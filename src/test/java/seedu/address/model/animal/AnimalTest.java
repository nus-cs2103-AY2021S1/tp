package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.AHMENG;
import static seedu.address.testutil.TypicalAnimals.BAILEY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AnimalBuilder;

public class AnimalTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Animal animal = new AnimalBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> animal.getMedicalConditions().remove(0));
    }

    @Test
    public void isSameAnimal() {
        // same object -> returns true
        assertTrue(AHMENG.isSameAnimal(AHMENG));

        // null -> returns false
        assertFalse(AHMENG.isSameAnimal(null));

        // different ID and species -> returns false
        Animal editedAlice = new AnimalBuilder(AHMENG).withId(VALID_ID_BAILEY).withSpecies(VALID_SPECIES_BAILEY).build();
        assertFalse(AHMENG.isSameAnimal(editedAlice));

        // different name -> returns false
        editedAlice = new AnimalBuilder(AHMENG).withName(VALID_NAME_BAILEY).build();
        assertFalse(AHMENG.isSameAnimal(editedAlice));

        // same name, same ID, different attributes -> returns true
        editedAlice = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();
        assertTrue(AHMENG.isSameAnimal(editedAlice));

        // same name, different ID, same attributes -> returns false
        editedAlice = new AnimalBuilder(AHMENG).withId(VALID_ID_BAILEY).build();
        assertFalse(AHMENG.isSameAnimal(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Animal aliceCopy = new AnimalBuilder(AHMENG).build();
        assertTrue(AHMENG.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AHMENG.equals(AHMENG));

        // null -> returns false
        assertFalse(AHMENG.equals(null));

        // different type -> returns false
        assertFalse(AHMENG.equals(5));

        // different animal -> returns false
        assertFalse(AHMENG.equals(BAILEY));

        // different name -> returns false
        Animal editedAlice = new AnimalBuilder(AHMENG).withName(VALID_NAME_BAILEY).build();
        assertFalse(AHMENG.equals(editedAlice));

        // different ID -> returns false
        editedAlice = new AnimalBuilder(AHMENG).withId(VALID_ID_BAILEY).build();
        assertFalse(AHMENG.equals(editedAlice));

        // different species -> returns false
        editedAlice = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY).build();
        assertFalse(AHMENG.equals(editedAlice));

        // different medicalConditions -> returns false
        editedAlice = new AnimalBuilder(AHMENG).withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();
        assertFalse(AHMENG.equals(editedAlice));
    }
}
