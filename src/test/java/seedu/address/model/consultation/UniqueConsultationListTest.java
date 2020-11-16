package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsults.ALICE_DAY;
import static seedu.address.testutil.TypicalConsults.ALICE_PERSONAL_CONSULT;
import static seedu.address.testutil.TypicalConsults.ALICE_TIME;
import static seedu.address.testutil.TypicalConsults.BENSON_GROUP_CONSULT;
import static seedu.address.testutil.TypicalConsults.GROUP_CONSULT;
import static seedu.address.testutil.TypicalConsults.PERSONAL_CONSULT;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.model.consultation.exceptions.ConflictingGroupConsultationException;
import seedu.address.model.consultation.exceptions.ConflictingPersonalConsultationException;
import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;
import seedu.address.testutil.ConsultationBuilder;


public class UniqueConsultationListTest {
    private final UniqueConsultationList uniqueConsultList = new UniqueConsultationList();

    @Test
    public void contains_nullConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.contains(null));
    }

    @Test
    public void contains_consultNotInList_returnsFalse() {
        assertFalse(uniqueConsultList.contains(ALICE_PERSONAL_CONSULT));
    }

    @Test
    public void contains_personalConsultInList_returnsTrue() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        assertTrue(uniqueConsultList.contains(ALICE_PERSONAL_CONSULT));
    }

    @Test
    public void contains_groupConsultInList_returnsTrue() {
        uniqueConsultList.add(BENSON_GROUP_CONSULT);
        assertTrue(uniqueConsultList.contains(BENSON_GROUP_CONSULT));
    }

    @Test
    public void contains_multipleSameGroupConsultInList_returnsTrue() {
        uniqueConsultList.add(GROUP_CONSULT);
        Consultation amyGroupConsult = new ConsultationBuilder(GROUP_CONSULT).withName(VALID_NAME_AMY)
                .build();
        uniqueConsultList.add(amyGroupConsult);
        assertTrue(uniqueConsultList.contains(amyGroupConsult));
    }

    @Test
    public void contains_consultWithSameIdentityFieldsInList_returnsTrue() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        Consultation editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withType(VALID_TYPE_BOB)
                .build();
        assertTrue(uniqueConsultList.contains(editedConsult));
    }

    @Test
    public void add_nullConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.add(null));
    }

    @Test
    public void add_duplicateConsult_throwsDuplicateConsultationException() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList.add(ALICE_PERSONAL_CONSULT));
    }

    @Test
    public void add_personalConsultWithExistingPersonalConsult_throwsConflictingPersonalConsultationException() {
        uniqueConsultList.add(PERSONAL_CONSULT);
        Consultation personalConsult = new ConsultationBuilder(PERSONAL_CONSULT).withName(VALID_NAME_BOB).build();
        assertThrows(ConflictingPersonalConsultationException.class, () -> uniqueConsultList.add(personalConsult));
    }

    @Test
    public void add_groupConsultWithExistingPersonalConsult_throwsConflictingPersonalConsultationException() {
        uniqueConsultList.add(PERSONAL_CONSULT);
        Consultation groupConsult = new ConsultationBuilder(PERSONAL_CONSULT).withName(VALID_NAME_BOB)
                .withType(VALID_TYPE_AMY).build();
        assertThrows(ConflictingPersonalConsultationException.class, () -> uniqueConsultList.add(groupConsult));
    }

    @Test
    public void add_groupConsultWithSameGroupConsult_returnsTrue() {
        uniqueConsultList.add(GROUP_CONSULT);
        Consultation groupConsult = new ConsultationBuilder(GROUP_CONSULT).withName(VALID_NAME_AMY).build();
        uniqueConsultList.add(groupConsult);
        assertTrue(uniqueConsultList.contains(groupConsult));
    }

    @Test
    public void add_personalConsultWithExistingGroupConsult_throwsConflictingPersonalConsultationException() {
        uniqueConsultList.add(GROUP_CONSULT);
        Consultation personalConsult = new ConsultationBuilder(GROUP_CONSULT).withName(VALID_NAME_AMY)
            .withType(VALID_TYPE_AMY).build();
        assertThrows(ConflictingPersonalConsultationException.class, () -> uniqueConsultList.add(personalConsult));
    }

    @Test
    public void add_conflictingConsult_throwsConflictingPersonalConsultationException() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        Consultation conflictingConsult = new ConsultationBuilder(PERSONAL_CONSULT)
            .withDay(ALICE_DAY)
            .withTime(ALICE_TIME)
            .build();
        assertThrows(ConflictingPersonalConsultationException.class, () -> uniqueConsultList.add(conflictingConsult));
    }

    @Test
    public void add_groupConsultWithSameGroupConsultDifferentLocation_throwsConflictingGroupConsultationException() {
        uniqueConsultList.add(GROUP_CONSULT);
        Consultation groupConsult = new ConsultationBuilder(GROUP_CONSULT).withName(VALID_NAME_AMY)
                .withLocation(VALID_LOCATION_AMY).build();
        assertThrows(ConflictingGroupConsultationException.class, () -> uniqueConsultList.add(groupConsult));
    }

    @Test
    public void setConsult_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList
                .setConsultation(null, ALICE_PERSONAL_CONSULT)
        );
    }

    @Test
    public void setConsult_nullEditedConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList
                .setConsultation(ALICE_PERSONAL_CONSULT, null));
    }

    @Test
    public void setConsult_targetConsultNotInList_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultList
                .setConsultation(ALICE_PERSONAL_CONSULT, ALICE_PERSONAL_CONSULT));
    }

    @Test
    public void setConsult_editedConsultIsSameConsult_success() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        uniqueConsultList.setConsultation(ALICE_PERSONAL_CONSULT, ALICE_PERSONAL_CONSULT);
        UniqueConsultationList uniqueConsultationList = new UniqueConsultationList();
        uniqueConsultationList.add(ALICE_PERSONAL_CONSULT);
        assertEquals(uniqueConsultationList, uniqueConsultList);
        assertEquals(uniqueConsultationList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsult_editedConsultHasSameIdentity_success() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        Consultation editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withType(VALID_TYPE_BOB)
                .build();
        uniqueConsultList.setConsultation(ALICE_PERSONAL_CONSULT, editedConsult);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(editedConsult);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
        assertEquals(expectedUniqueConsultationList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsult_editedConsultHasDifferentIdentity_success() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        uniqueConsultList.setConsultation(ALICE_PERSONAL_CONSULT, BENSON_GROUP_CONSULT);
        UniqueConsultationList expectedUniquePersonList = new UniqueConsultationList();
        expectedUniquePersonList.add(BENSON_GROUP_CONSULT);
        assertEquals(expectedUniquePersonList, uniqueConsultList);
        assertEquals(expectedUniquePersonList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsult_editedConsultHasNonUniqueIdentity_throwsDuplicateConsultationException() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        uniqueConsultList.add(BENSON_GROUP_CONSULT);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList
                .setConsultation(ALICE_PERSONAL_CONSULT, BENSON_GROUP_CONSULT));
    }

    @Test
    public void remove_nullConsult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.remove(null));
    }

    @Test
    public void remove_consultDoesNotExist_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultList.remove(ALICE_PERSONAL_CONSULT));
    }

    @Test
    public void remove_existingConsult_removesConsult() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        uniqueConsultList.remove(ALICE_PERSONAL_CONSULT);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
        assertEquals(expectedUniqueConsultationList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsults_nullUniqueConsultationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList
                .setConsultations((UniqueConsultationList) null));
    }

    @Test
    public void setConsults_uniqueConsultationList_replacesOwnListWithProvidedUniqueConsultationList() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(ALICE_PERSONAL_CONSULT);
        uniqueConsultList.setConsultations(expectedUniqueConsultationList);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
        assertEquals(expectedUniqueConsultationList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsultations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultList.setConsultations((List<Consultation>) null));
    }

    @Test
    public void setConsultations_list_replacesOwnListWithProvidedList() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        List<Consultation> consultationList = Collections.singletonList(BENSON_GROUP_CONSULT);
        uniqueConsultList.setConsultations(consultationList);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(BENSON_GROUP_CONSULT);
        assertEquals(expectedUniqueConsultationList, uniqueConsultList);
        assertEquals(expectedUniqueConsultationList.hashCode(), uniqueConsultList.hashCode());
    }

    @Test
    public void setConsultations_listWithDuplicateConsultations_throwsDuplicateConsultationException() {
        List<Consultation> listWithDuplicateConsultations =
                Arrays.asList(ALICE_PERSONAL_CONSULT, ALICE_PERSONAL_CONSULT);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultList
                .setConsultations(listWithDuplicateConsultations));
    }

    @Test
    public void iterator_emptyList() {
        Iterator<Consultation> iterator = uniqueConsultList.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iterator_nonEmptyList() {
        uniqueConsultList.add(ALICE_PERSONAL_CONSULT);
        Iterator<Consultation> iterator = uniqueConsultList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(ALICE_PERSONAL_CONSULT, iterator.next());
    }

}
