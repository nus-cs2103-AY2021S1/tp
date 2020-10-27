package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE_PAULINE_ASSAULT;
import static seedu.pivot.testutil.TypicalCases.BOB_CHOO_SALON_THEFT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.investigationcase.exceptions.CaseNotFoundException;
import seedu.pivot.model.investigationcase.exceptions.DuplicateCaseException;
import seedu.pivot.testutil.CaseBuilder;

public class UniqueCaseListTest {

    private final UniqueCaseList uniqueCaseList = new UniqueCaseList();

    @Test
    public void contains_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.contains(null));
    }

    @Test
    public void contains_caseNotInList_returnsFalse() {
        assertFalse(uniqueCaseList.contains(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void contains_caseInList_returnsTrue() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertTrue(uniqueCaseList.contains(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void contains_caseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueCaseList.contains(editedAlice));
    }

    @Test
    public void add_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.add(null));
    }

    @Test
    public void add_duplicateCase_throwsDuplicateCaseException() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertThrows(DuplicateCaseException.class, () -> uniqueCaseList.add(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setCase_nullTargetCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCase(null, ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setCase_nullEditedCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, null));
    }

    @Test
    public void setCase_targetCaseNotInList_throwsCaseNotFoundException() {
        assertThrows(CaseNotFoundException.class, () ->
                uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setCase_editedCaseIsSameCase_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCase_editedCaseHasSameIdentity_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, editedAlice);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(editedAlice);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCase_editedCaseHasDifferentIdentity_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, BOB_CHOO_SALON_THEFT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCase_editedCaseHasNonUniqueIdentity_throwsDuplicateCaseException() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertThrows(DuplicateCaseException.class, () ->
                uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, BOB_CHOO_SALON_THEFT));
    }

    @Test
    public void remove_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.remove(null));
    }

    @Test
    public void remove_caseDoesNotExist_throwsCaseNotFoundException() {
        assertThrows(CaseNotFoundException.class, () -> uniqueCaseList.remove(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void remove_existingCase_removesCase() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.remove(ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCases_nullUniqueCaseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCases((UniqueCaseList) null));
    }

    @Test
    public void setCases_uniqueCaseList_replacesOwnListWithProvidedUniqueCaseList() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        uniqueCaseList.setCases(expectedUniqueCaseList);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCases_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCases((List<Case>) null));
    }

    @Test
    public void setCases_list_replacesOwnListWithProvidedList() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        List<Case> caseList = Collections.singletonList(BOB_CHOO_SALON_THEFT);
        uniqueCaseList.setCases(caseList);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setCases_listWithDuplicateCases_throwsDuplicateCaseException() {
        List<Case> listWithDuplicateCases = Arrays.asList(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT);
        assertThrows(DuplicateCaseException.class, () -> uniqueCaseList.setCases(listWithDuplicateCases));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCaseList.asUnmodifiableObservableList().remove(0));
    }
}
