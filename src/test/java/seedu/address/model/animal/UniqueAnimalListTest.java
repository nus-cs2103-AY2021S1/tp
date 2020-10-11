package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICALCONDITION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.ALICE;
import static seedu.address.testutil.TypicalAnimals.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.animal.exceptions.AnimalNotFoundException;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;
import seedu.address.testutil.AnimalBuilder;

public class UniqueAnimalListTest {

    private final UniqueAnimalList uniqueAnimalList = new UniqueAnimalList();

    @Test
    public void contains_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.contains(null));
    }

    @Test
    public void contains_animalNotInList_returnsFalse() {
        assertFalse(uniqueAnimalList.contains(ALICE));
    }

    @Test
    public void contains_animalInList_returnsTrue() {
        uniqueAnimalList.add(ALICE);
        assertTrue(uniqueAnimalList.contains(ALICE));
    }

    @Test
    public void contains_animalWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAnimalList.add(ALICE);
        Animal editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB)
                .withMedicalConditions(VALID_MEDICALCONDITION_HUSBAND)
                .build();
        assertTrue(uniqueAnimalList.contains(editedAlice));
    }

    @Test
    public void add_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.add(null));
    }

    @Test
    public void add_duplicateAnimal_throwsDuplicateAnimalException() {
        uniqueAnimalList.add(ALICE);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.add(ALICE));
    }

    @Test
    public void setAnimal_nullTargetAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(null, ALICE));
    }

    @Test
    public void setAnimal_nullEditedAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(ALICE, null));
    }

    @Test
    public void setAnimal_targetAnimalNotInList_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.setAnimal(ALICE, ALICE));
    }

    @Test
    public void setAnimal_editedAnimalIsSameAnimal_success() {
        uniqueAnimalList.add(ALICE);
        uniqueAnimalList.setAnimal(ALICE, ALICE);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(ALICE);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasSameIdentity_success() {
        uniqueAnimalList.add(ALICE);
        Animal editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB)
                .withMedicalConditions(VALID_MEDICALCONDITION_HUSBAND)
                .build();
        uniqueAnimalList.setAnimal(ALICE, editedAlice);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(editedAlice);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasDifferentIdentity_success() {
        uniqueAnimalList.add(ALICE);
        uniqueAnimalList.setAnimal(ALICE, BOB);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BOB);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasNonUniqueIdentity_throwsDuplicateAnimalException() {
        uniqueAnimalList.add(ALICE);
        uniqueAnimalList.add(BOB);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimal(ALICE, BOB));
    }

    @Test
    public void remove_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.remove(null));
    }

    @Test
    public void remove_animalDoesNotExist_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.remove(ALICE));
    }

    @Test
    public void remove_existingAnimal_removesAnimal() {
        uniqueAnimalList.add(ALICE);
        uniqueAnimalList.remove(ALICE);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_nullUniqueAnimalList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((UniqueAnimalList) null));
    }

    @Test
    public void setAnimals_uniqueAnimalList_replacesOwnListWithProvidedUniqueAnimalList() {
        uniqueAnimalList.add(ALICE);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BOB);
        uniqueAnimalList.setAnimals(expectedUniqueAnimalList);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((List<Animal>) null));
    }

    @Test
    public void setAnimals_list_replacesOwnListWithProvidedList() {
        uniqueAnimalList.add(ALICE);
        List<Animal> animalList = Collections.singletonList(BOB);
        uniqueAnimalList.setAnimals(animalList);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BOB);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_listWithDuplicateAnimals_throwsDuplicateAnimalException() {
        List<Animal> listWithDuplicateAnimals = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimals(listWithDuplicateAnimals));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAnimalList.asUnmodifiableObservableList().remove(0));
    }
}
