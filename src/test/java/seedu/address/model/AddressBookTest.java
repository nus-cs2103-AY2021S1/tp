package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.ALICE;
import static seedu.address.testutil.TypicalAnimals.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;
import seedu.address.testutil.AnimalBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getAnimalList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateAnimals_throwsDuplicateAnimalException() {
        // Two animals with the same identity fields
        Animal editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_HUSBAND)
                .build();
        List<Animal> newAnimals = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newAnimals);

        assertThrows(DuplicateAnimalException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasAnimal_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAnimal(null));
    }

    @Test
    public void hasAnimal_animalNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasAnimal(ALICE));
    }

    @Test
    public void hasAnimal_animalInAddressBook_returnsTrue() {
        addressBook.addAnimal(ALICE);
        assertTrue(addressBook.hasAnimal(ALICE));
    }

    @Test
    public void hasAnimal_animalWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addAnimal(ALICE);
        Animal editedAlice = new AnimalBuilder(ALICE).withSpecies(VALID_SPECIES_BOB)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_HUSBAND)
                .build();
        assertTrue(addressBook.hasAnimal(editedAlice));
    }

    @Test
    public void getAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAnimalList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose animals list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Animal> animals = FXCollections.observableArrayList();

        AddressBookStub(Collection<Animal> animals) {
            this.animals.setAll(animals);
        }

        @Override
        public ObservableList<Animal> getAnimalList() {
            return animals;
        }
    }

}
