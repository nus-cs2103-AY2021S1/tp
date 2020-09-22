package seedu.address.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.stock.exceptions.DuplicateStockException;
import seedu.address.model.stock.exceptions.StockNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueStockListTest {

    private final UniqueStockList uniqueStockList = new UniqueStockList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueStockList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueStockList.add(ALICE);
        assertTrue(uniqueStockList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStockList.add(ALICE);
        Stock editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStockList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueStockList.add(ALICE);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(StockNotFoundException.class, () -> uniqueStockList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueStockList.add(ALICE);
        uniqueStockList.setPerson(ALICE, ALICE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(ALICE);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueStockList.add(ALICE);
        Stock editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStockList.setPerson(ALICE, editedAlice);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(editedAlice);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueStockList.add(ALICE);
        uniqueStockList.setPerson(ALICE, BOB);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BOB);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueStockList.add(ALICE);
        uniqueStockList.add(BOB);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(StockNotFoundException.class, () -> uniqueStockList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueStockList.add(ALICE);
        uniqueStockList.remove(ALICE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setPersons((UniqueStockList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueStockList.add(ALICE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BOB);
        uniqueStockList.setPersons(expectedUniqueStockList);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setPersons((List<Stock>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueStockList.add(ALICE);
        List<Stock> stockList = Collections.singletonList(BOB);
        uniqueStockList.setPersons(stockList);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BOB);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Stock> listWithDuplicateStocks = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.setPersons(listWithDuplicateStocks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStockList.asUnmodifiableObservableList().remove(0));
    }
}
