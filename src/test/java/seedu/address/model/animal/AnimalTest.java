package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.ALICE;
import static seedu.address.testutil.TypicalAnimals.BOB;

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
        assertTrue(ALICE.isSameAnimal(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameAnimal(null));

        // different ID and species -> returns false
        Animal editedAlice = new AnimalBuilder(ALICE).withId(VALID_ID_BOB).withSpecies(VALID_SPECIES_BOB).build();
        assertFalse(ALICE.isSameAnimal(editedAlice));

        // different name -> returns false
        editedAlice = new AnimalBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameAnimal(editedAlice));

        // same name, same ID, different attributes -> returns true
        editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_HUSBAND).build();
        assertTrue(ALICE.isSameAnimal(editedAlice));

        // same name, different ID, same attributes -> returns false
        editedAlice = new AnimalBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameAnimal(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Animal aliceCopy = new AnimalBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different animal -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Animal editedAlice = new AnimalBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ID -> returns false
        editedAlice = new AnimalBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different species -> returns false
        editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different medicalConditions -> returns false
        editedAlice = new AnimalBuilder(ALICE).withMedicalConditions(VALID_MEDICAL_CONDITION_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
