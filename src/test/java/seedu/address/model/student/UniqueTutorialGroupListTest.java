package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialGroups.B06;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.DuplicateShowableException;
import seedu.address.model.exceptions.ShowableNotFoundException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;
import seedu.address.testutil.TutorialGroupBuilder;

public class UniqueTutorialGroupListTest {

    private final UniqueTutorialGroupList uniqueTutorialGroupList = new UniqueTutorialGroupList();

    @Test
    public void contains_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.contains(null));
    }

    @Test
    public void contains_tutorialGroupNotInList_returnsFalse() {
        assertFalse(uniqueTutorialGroupList.contains(T05));
    }

    @Test
    public void contains_tutorialGroupInList_returnsTrue() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        assertTrue(uniqueTutorialGroupList.contains(T05));
    }

    @Test
    public void contains_tutorialGroupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        TutorialGroup editedT05 = new TutorialGroupBuilder().withTutorialGroupId("T05").build();
        assertTrue(uniqueTutorialGroupList.contains(editedT05));
    }

    @Test
    public void add_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.addTutorialGroup(null));
    }

    @Test
    public void add_duplicateTutorialGroup_throwsDuplicateShowableException() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        assertThrows(DuplicateShowableException.class, () -> uniqueTutorialGroupList.addTutorialGroup(T05));
    }

    @Test
    public void setTutorialGroup_nullTargetTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.setTutorialGroup(null, T05));
    }

    @Test
    public void setTutorialGroup_nullEditedTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.setTutorialGroup(T05, null));
    }

    @Test
    public void setTutorialGroup_targetTutorialGroupNotInList_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueTutorialGroupList.setTutorialGroup(T05, T05));
    }

    @Test
    public void setTutorialGroup_editedTutorialGroupIsSameTutorialGroup_success() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.setTutorialGroup(T05, T05);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        expectedUniqueTutorialGroupList.addTutorialGroup(T05);
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void setTutorialGroup_editedTutorialGroupHasSameIdentity_success() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        TutorialGroup editedT05 = new TutorialGroupBuilder().withTutorialGroupId("T05").withStartTime("10:00")
            .withEndTime("17:00").build();
        uniqueTutorialGroupList.setTutorialGroup(T05, editedT05);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        expectedUniqueTutorialGroupList.addTutorialGroup(editedT05);
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void getSize_sizeOfTutorialGroupsExpected_success() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.addTutorialGroup(B06);
        int listSize = uniqueTutorialGroupList.size();
        assertEquals(listSize, 2);
    }

    @Test
    public void setTutorialGroup_editedTutorialGroupHasDifferentIdentity_success() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.setTutorialGroup(T05, B06);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        expectedUniqueTutorialGroupList.addTutorialGroup(B06);
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void setTutorialGroup_editedTutorialGroupHasNonUniqueIdentity_throwsDuplicateShowableException() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.addTutorialGroup(B06);
        assertThrows(DuplicateShowableException.class, () -> uniqueTutorialGroupList.setTutorialGroup(T05, B06));
    }

    @Test
    public void remove_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.removeTutorialGroup(null));
    }

    @Test
    public void remove_tutorialGroupDoesNotExist_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueTutorialGroupList.removeTutorialGroup(T05));
    }

    @Test
    public void remove_existingTutorialGroup_removesTutorialGroup() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.removeTutorialGroup(T05);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void setTutorialGroupList_nullUniqueTutorialGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.setTutorialGroupList(
            (UniqueTutorialGroupList) null));
    }

    @Test
    public void setTutorialGroupList_uniqueTutorialGroupList_replacesOwnListWithProvidedUniqueTutoralGroupList() {
        uniqueTutorialGroupList.addTutorialGroup(T05);

        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        expectedUniqueTutorialGroupList.addTutorialGroup(T05);
        uniqueTutorialGroupList.setTutorialGroupList(expectedUniqueTutorialGroupList);
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void setTutorialGroupList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.setTutorialGroupList(
            (List<TutorialGroup>) null));
    }

    @Test
    public void setTutorialGroupList_list_replacesOwnListWithProvidedList() {
        uniqueTutorialGroupList.addTutorialGroup(T05);
        List<TutorialGroup> tutorialGroupList = Collections.singletonList(T05);
        uniqueTutorialGroupList.setTutorialGroupList(tutorialGroupList);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        expectedUniqueTutorialGroupList.addTutorialGroup(T05);
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void setTutorialGroupList_listWithDuplicateTutorialGroups_throwsDuplicateShowableException() {
        List<TutorialGroup> listWithDuplicatedTutorialGroup = Arrays.asList(T05, T05);
        assertThrows(DuplicateShowableException.class, ()
            -> uniqueTutorialGroupList.setTutorialGroupList(listWithDuplicatedTutorialGroup)
        );
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTutorialGroupList.asUnmodifiableObservableList().remove(0)
        );
    }
}
