package seedu.address.model.person.hairdresser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BENJAMIN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalHairdressers.AMELIA;
import static seedu.address.testutil.TypicalHairdressers.BENJAMIN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.hairdresser.exception.DuplicateHairdresserException;
import seedu.address.model.person.hairdresser.exception.HairdresserNotFoundException;
import seedu.address.testutil.HairdresserBuilder;

public class UniqueHairdresserListTest {
    private final UniqueHairdresserList uniqueHairdresserList = new UniqueHairdresserList();

    @Test
    public void contains_nullHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueHairdresserList.contains(AMELIA));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueHairdresserList.add(AMELIA);
        assertTrue(uniqueHairdresserList.contains(AMELIA));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueHairdresserList.add(AMELIA);
        Hairdresser editedAlice = new HairdresserBuilder(AMELIA).withTitle(VALID_TITLE_BENJAMIN)
                .withSpecs(VALID_SPECIALISATION_COLOR).build();
        assertTrue(uniqueHairdresserList.contains(editedAlice));
    }

    @Test
    public void add_nullHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.add(null));
    }

    @Test
    public void add_duplicateHairdresser_throwsDuplicateHairdresserException() {
        uniqueHairdresserList.add(AMELIA);
        assertThrows(DuplicateHairdresserException.class, () -> uniqueHairdresserList.add(AMELIA));
    }

    @Test
    public void setHairdresser_nullTargetHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.setHairdresser(null, AMELIA));
    }

    @Test
    public void setHairdresser_nullEditedHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.setHairdresser(AMELIA, null));
    }

    @Test
    public void setHairdresser_targetHairdresserNotInList_throwsHairdresserNotFoundException() {
        assertThrows(HairdresserNotFoundException.class, () -> uniqueHairdresserList.setHairdresser(AMELIA, AMELIA));
    }

    @Test
    public void setHairdresser_editedHairdresserIsSameHairdresser_success() {
        uniqueHairdresserList.add(AMELIA);
        uniqueHairdresserList.setHairdresser(AMELIA, AMELIA);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        expectedUniqueHairdresserList.add(AMELIA);
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdresser_editedHairdresserHasSameIdentity_success() {
        uniqueHairdresserList.add(AMELIA);
        Hairdresser editedAlice = new HairdresserBuilder(AMELIA).withTitle(VALID_TITLE_BENJAMIN)
                .withSpecs(VALID_SPECIALISATION_COLOR).build();
        uniqueHairdresserList.setHairdresser(AMELIA, editedAlice);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        expectedUniqueHairdresserList.add(editedAlice);
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdresser_editedHairdresserHasDifferentIdentity_success() {
        uniqueHairdresserList.add(AMELIA);
        uniqueHairdresserList.setHairdresser(AMELIA, BENJAMIN);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        expectedUniqueHairdresserList.add(BENJAMIN);
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdresser_editedHairdresserHasNonUniqueIdentity_throwsDuplicateHairdresserException() {
        uniqueHairdresserList.add(AMELIA);
        uniqueHairdresserList.add(BENJAMIN);
        assertThrows(DuplicateHairdresserException.class, () -> uniqueHairdresserList.setHairdresser(AMELIA, BENJAMIN));
    }

    @Test
    public void remove_nullHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsHairdresserNotFoundException() {
        assertThrows(HairdresserNotFoundException.class, () -> uniqueHairdresserList.remove(AMELIA));
    }

    @Test
    public void remove_existingHairdresser_removesHairdresser() {
        uniqueHairdresserList.add(AMELIA);
        uniqueHairdresserList.remove(AMELIA);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdressers_nullUniqueHairdresserList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueHairdresserList.setHairdressers((UniqueHairdresserList) null));
    }

    @Test
    public void setHairdressers_uniqueHairdresserList_replacesOwnListWithProvidedUniqueHairdresserList() {
        uniqueHairdresserList.add(AMELIA);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        expectedUniqueHairdresserList.add(BENJAMIN);
        uniqueHairdresserList.setHairdressers(expectedUniqueHairdresserList);
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdressers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueHairdresserList.setHairdressers((List<Hairdresser>) null));
    }

    @Test
    public void setHairdressers_list_replacesOwnListWithProvidedList() {
        uniqueHairdresserList.add(AMELIA);
        List<Hairdresser> personList = Collections.singletonList(BENJAMIN);
        uniqueHairdresserList.setHairdressers(personList);
        UniqueHairdresserList expectedUniqueHairdresserList = new UniqueHairdresserList();
        expectedUniqueHairdresserList.add(BENJAMIN);
        assertEquals(expectedUniqueHairdresserList, uniqueHairdresserList);
    }

    @Test
    public void setHairdressers_listWithDuplicateHairdressers_throwsDuplicateHairdresserException() {
        List<Hairdresser> listWithDuplicateHairdressers = Arrays.asList(AMELIA, AMELIA);
        assertThrows(DuplicateHairdresserException.class, ()
            -> uniqueHairdresserList.setHairdressers(listWithDuplicateHairdressers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueHairdresserList.asUnmodifiableObservableList().remove(0));
    }
}
