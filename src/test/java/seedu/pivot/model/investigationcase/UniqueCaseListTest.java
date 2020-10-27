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
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueCaseList.contains(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertTrue(uniqueCaseList.contains(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueCaseList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertThrows(DuplicateCaseException.class, () -> uniqueCaseList.add(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCase(null, ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(CaseNotFoundException.class, () ->
                uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        Case editedAlice = new CaseBuilder(ALICE_PAULINE_ASSAULT).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, editedAlice);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(editedAlice);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, BOB_CHOO_SALON_THEFT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertThrows(DuplicateCaseException.class, () ->
                uniqueCaseList.setCase(ALICE_PAULINE_ASSAULT, BOB_CHOO_SALON_THEFT));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(CaseNotFoundException.class, () -> uniqueCaseList.remove(ALICE_PAULINE_ASSAULT));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        uniqueCaseList.remove(ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCases((UniqueCaseList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        uniqueCaseList.setCases(expectedUniqueCaseList);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCaseList.setCases((List<Case>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueCaseList.add(ALICE_PAULINE_ASSAULT);
        List<Case> caseList = Collections.singletonList(BOB_CHOO_SALON_THEFT);
        uniqueCaseList.setCases(caseList);
        UniqueCaseList expectedUniqueCaseList = new UniqueCaseList();
        expectedUniqueCaseList.add(BOB_CHOO_SALON_THEFT);
        assertEquals(expectedUniqueCaseList, uniqueCaseList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Case> listWithDuplicateCases = Arrays.asList(ALICE_PAULINE_ASSAULT, ALICE_PAULINE_ASSAULT);
        assertThrows(DuplicateCaseException.class, () -> uniqueCaseList.setCases(listWithDuplicateCases));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCaseList.asUnmodifiableObservableList().remove(0));
    }
}
