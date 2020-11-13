package seedu.address.model.deliverable.deliverable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.testutil.TypicalDeliverables.HOME_PAGE;
import static seedu.address.testutil.TypicalDeliverables.NAVIGATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.deliverable.deliverable.exceptions.DeliverableNotFoundException;
import seedu.address.model.deliverable.deliverable.exceptions.DuplicateDeliverableException;
import seedu.address.testutil.DeliverableBuilder;

public class UniqueDeliverableListTest {
    private final UniqueDeliverableList uniqueDeliverableList = new UniqueDeliverableList();

    @Test
    public void contains_nullDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeliverableList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueDeliverableList.contains(HOME_PAGE));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueDeliverableList.add(HOME_PAGE);
        assertTrue(uniqueDeliverableList.contains(HOME_PAGE));
    }


    @Test
    public void contains_differentDeliverableWithSameIdentity_returnsTrue() {
        uniqueDeliverableList.add(HOME_PAGE);
        Deliverable editedHomepage = new DeliverableBuilder(HOME_PAGE).withDescription(VALID_DESCRIPTION_B)
                .build();
        assertTrue(uniqueDeliverableList.contains(editedHomepage));
    }

    @Test
    public void add_nullDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeliverableList.add(null));
    }

    @Test
    public void add_duplicateDeliverable_throwsDuplicateDeliverableException() {
        uniqueDeliverableList.add(HOME_PAGE);
        assertThrows(DuplicateDeliverableException.class, () -> uniqueDeliverableList.add(HOME_PAGE));
    }

    @Test
    public void setDeliverable_nullTargetDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeliverableList.setDeliverable(null, HOME_PAGE));
    }

    @Test
    public void setDeliverable_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueDeliverableList.setDeliverable(HOME_PAGE, null));
    }

    @Test
    public void setDeliverable_targetMeetingNotInList_throwsDeliverableNotFoundException() {
        assertThrows(DeliverableNotFoundException.class, ()
            -> uniqueDeliverableList.setDeliverable(HOME_PAGE, HOME_PAGE));
    }

    @Test
    public void setDeliverable_editedDeliverableIsSameDeliverable_success() {
        uniqueDeliverableList.add(HOME_PAGE);
        uniqueDeliverableList.setDeliverable(HOME_PAGE, HOME_PAGE);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(HOME_PAGE);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverable_editedDeliverableHasSameIdentity_success() {
        uniqueDeliverableList.add(HOME_PAGE);
        Deliverable editedHomepage = new DeliverableBuilder(HOME_PAGE).withDescription(VALID_DESCRIPTION_B)
                .build();
        uniqueDeliverableList.setDeliverable(HOME_PAGE, editedHomepage);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(editedHomepage);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverable_editedDeliverableHasDifferentIdentity_success() {
        uniqueDeliverableList.add(HOME_PAGE);
        uniqueDeliverableList.setDeliverable(HOME_PAGE, NAVIGATION);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(NAVIGATION);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverable_editedDeliverableHasNonUniqueIdentity_throwsDuplicateDeliverableException() {
        uniqueDeliverableList.add(HOME_PAGE);
        uniqueDeliverableList.add(NAVIGATION);
        assertThrows(DuplicateDeliverableException.class, ()
            -> uniqueDeliverableList.setDeliverable(HOME_PAGE, NAVIGATION));
    }

    @Test
    public void remove_nullDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeliverableList.remove(null));
    }

    @Test
    public void remove_deliverableDoesNotExist_throwsDeliverableNotFoundException() {
        assertThrows(DeliverableNotFoundException.class, () -> uniqueDeliverableList.remove(HOME_PAGE));
    }

    @Test
    public void remove_existingDeliverable_removesDeliverable() {
        uniqueDeliverableList.add(HOME_PAGE);
        uniqueDeliverableList.remove(HOME_PAGE);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverables_nullUniqueDeliverableList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueDeliverableList.setDeliverables((UniqueDeliverableList) null));
    }

    @Test
    public void setDeliverables_uniqueDeliverableList_replacesOwnListWithProvidedUniqueDeliverableList() {
        uniqueDeliverableList.add(HOME_PAGE);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(NAVIGATION);
        uniqueDeliverableList.setDeliverables(expectedUniqueDeliverableList);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverables_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeliverableList.setDeliverables((List<Deliverable>) null));
    }

    @Test
    public void setDeliverables_list_replacesOwnListWithProvidedList() {
        uniqueDeliverableList.add(HOME_PAGE);
        List<Deliverable> meetingList = Collections.singletonList(NAVIGATION);
        uniqueDeliverableList.setDeliverables(meetingList);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(NAVIGATION);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void setDeliverables_listWithDuplicateDeliverables_throwsDuplicateDeliverableException() {
        List<Deliverable> listWithDuplicateDeliverables = Arrays.asList(HOME_PAGE, HOME_PAGE);
        assertThrows(DuplicateDeliverableException.class, ()
            -> uniqueDeliverableList.setDeliverables(listWithDuplicateDeliverables));
    }

    @Test
    public void sortList_addDeliverableInDifferentOrder_equalUniqueDeliverableList() {
        uniqueDeliverableList.add(HOME_PAGE);
        uniqueDeliverableList.add(NAVIGATION);
        UniqueDeliverableList expectedUniqueDeliverableList = new UniqueDeliverableList();
        expectedUniqueDeliverableList.add(NAVIGATION);
        expectedUniqueDeliverableList.add(HOME_PAGE);
        assertEquals(expectedUniqueDeliverableList, uniqueDeliverableList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueDeliverableList.asUnmodifiableObservableList().remove(0));
    }

}
