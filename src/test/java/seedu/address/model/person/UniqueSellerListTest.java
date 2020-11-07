package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.seller.TypicalSeller.ALICE;
import static seedu.address.testutil.seller.TypicalSeller.BOB;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.person.seller.UniqueSellerList;
import seedu.address.testutil.seller.SellerBuilder;

public class UniqueSellerListTest {

    private final UniqueSellerList uniqueSellerList = new UniqueSellerList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.contains(null));
    }

    @Test
    public void contains_bidderNotInList_returnsFalse() {
        assertFalse(uniqueSellerList.contains(ALICE));
    }

    @Test
    public void contains_bidderInList_returnsTrue() {
        uniqueSellerList.add(ALICE);
        assertTrue(uniqueSellerList.contains(ALICE));
    }

    @Test
    public void contains_bidderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueSellerList.add(ALICE);
        Seller editedAlice = new SellerBuilder(ALICE).build();
        assertTrue(uniqueSellerList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.add(null));
    }

    @Test
    public void add_duplicateSeller_throwsDuplicatePersonException() {
        uniqueSellerList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueSellerList.add(ALICE));
    }

    @Test
    public void setSeller_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setSeller(null, ALICE));
    }

    @Test
    public void setSeller_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setSeller(ALICE, null));
    }

    @Test
    public void setSeller_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSellerList.setSeller(ALICE, ALICE));
    }

    @Test
    public void setSeller_editedPersonIsSamePerson_success() {
        uniqueSellerList.add(ALICE);
        uniqueSellerList.setSeller(ALICE, ALICE);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        expectedUniqueSellerList.add(ALICE);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSeller_editedPersonHasSameIdentity_success() {
        uniqueSellerList.add(ALICE);
        Seller editedAlice = new SellerBuilder(ALICE).build();
        uniqueSellerList.setSeller(ALICE, editedAlice);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        expectedUniqueSellerList.add(editedAlice);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSeller_editedSellerHasDifferentIdentity_success() {
        uniqueSellerList.add(ALICE);
        uniqueSellerList.setSeller(ALICE, BOB);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        expectedUniqueSellerList.add(BOB);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSeller_editedSellerHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueSellerList.add(ALICE);
        uniqueSellerList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueSellerList.setSeller(ALICE, BOB));
    }

    @Test
    public void remove_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.remove(null));
    }

    @Test
    public void remove_sellerDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSellerList.remove(ALICE));
    }

    @Test
    public void remove_existingSeller_removesSeller() {
        uniqueSellerList.add(ALICE);
        uniqueSellerList.remove(ALICE);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSellers_nullUniqueSellerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setSellers((UniqueSellerList) null));
    }

    @Test
    public void setSellers_uniqueSellerList_replacesOwnListWithProvidedUniqueSellerList() {
        uniqueSellerList.add(ALICE);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        expectedUniqueSellerList.add(BOB);
        uniqueSellerList.setSellers(expectedUniqueSellerList);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSellers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setSellers((List<Seller>) null));
    }

    @Test
    public void setSellers_list_replacesOwnListWithProvidedList() {
        uniqueSellerList.add(ALICE);
        List<Seller> personList = Collections.singletonList(BOB);
        uniqueSellerList.setSellers(personList);
        UniqueSellerList expectedUniqueSellerList = new UniqueSellerList();
        expectedUniqueSellerList.add(BOB);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }

    @Test
    public void setSellers_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Seller> listWithDuplicateSellers = Arrays.asList(SELLER_ALICE, SELLER_ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueSellerList.setSellers(listWithDuplicateSellers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> uniqueSellerList.asUnmodifiableObservableList().remove(0));
    }
}
