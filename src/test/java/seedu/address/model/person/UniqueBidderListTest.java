package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.BIDDER_ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.bidder.UniqueBidderList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.bidder.BidderBuilder;

public class UniqueBidderListTest {

    private final UniqueBidderList uniqueBidderList = new UniqueBidderList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.contains(null));
    }

    @Test
    public void contains_bidderNotInList_returnsFalse() {
        assertFalse(uniqueBidderList.contains(ALICE));
    }

    @Test
    public void contains_bidderInList_returnsTrue() {
        uniqueBidderList.add(ALICE);
        assertTrue(uniqueBidderList.contains(ALICE));
    }

    @Test
    public void contains_bidderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBidderList.add(ALICE);
        Bidder editedAlice = new BidderBuilder(ALICE).build();
        assertTrue(uniqueBidderList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.add(null));
    }

    @Test
    public void add_duplicateBidder_throwsDuplicatePersonException() {
        uniqueBidderList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBidderList.add(ALICE));
    }

    @Test
    public void setBidder_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.setBidder(null, ALICE));
    }

    @Test
    public void setBidder_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.setBidder(ALICE, null));
    }

    @Test
    public void setBidder_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBidderList.setBidder(ALICE, ALICE));
    }

    @Test
    public void setBidder_editedBidderIsSameBidder_success() {
        uniqueBidderList.add(ALICE);
        uniqueBidderList.setBidder(ALICE, ALICE);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        expectedUniqueBidderList.add(ALICE);
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidder_editedBidderHasSameIdentity_success() {
        uniqueBidderList.add(ALICE);
        Bidder editedAlice = new BidderBuilder(ALICE).build();
        uniqueBidderList.setBidder(ALICE, editedAlice);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        expectedUniqueBidderList.add(editedAlice);
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidder_editedBidderHasDifferentIdentity_success() {
        uniqueBidderList.add(ALICE);
        uniqueBidderList.setBidder(ALICE, BOB);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        expectedUniqueBidderList.add(BOB);
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidder_editedBidderHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBidderList.add(ALICE);
        uniqueBidderList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueBidderList.setBidder(ALICE, BOB));
    }

    @Test
    public void remove_nullBidder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.remove(null));
    }

    @Test
    public void remove_bidderDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBidderList.remove(ALICE));
    }

    @Test
    public void remove_existingBidder_removesBidder() {
        uniqueBidderList.add(ALICE);
        uniqueBidderList.remove(ALICE);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidders_nullUniqueBidderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.setBidders((UniqueBidderList) null));
    }

    @Test
    public void setBidders_uniqueBidderList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueBidderList.add(ALICE);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        expectedUniqueBidderList.add(BOB);
        uniqueBidderList.setBidders(expectedUniqueBidderList);
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidderList.setBidders((List<Bidder>) null));
    }

    @Test
    public void setBidders_list_replacesOwnListWithProvidedList() {
        uniqueBidderList.add(ALICE);
        List<Bidder> personList = Collections.singletonList(BOB);
        uniqueBidderList.setBidders(personList);
        UniqueBidderList expectedUniqueBidderList = new UniqueBidderList();
        expectedUniqueBidderList.add(BOB);
        assertEquals(expectedUniqueBidderList, uniqueBidderList);
    }

    @Test
    public void setBidders_listWithDuplicateBidders_throwsDuplicatePersonException() {
        List<Bidder> listWithDuplicateBidders = Arrays.asList(BIDDER_ALICE, BIDDER_ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBidderList.setBidders(listWithDuplicateBidders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> uniqueBidderList.asUnmodifiableObservableList().remove(0));
    }
}
