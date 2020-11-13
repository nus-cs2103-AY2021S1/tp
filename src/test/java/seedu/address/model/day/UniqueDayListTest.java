package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.DAY1;
import static seedu.address.testutil.TypicalDays.MDAY2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.exceptions.DayNotFoundException;
import seedu.address.model.day.exceptions.DuplicateDayException;

public class UniqueDayListTest {

    private final UniqueDayList uniqueDayList = new UniqueDayList();

    @Test
    public void contains_nullDay_throwsNullPointerException() {
        Day day = null;
        assertThrows(NullPointerException.class, () -> uniqueDayList.contains(day));
    }

    @Test
    public void contains_dayNotInList_returnsFalse() {
        assertFalse(uniqueDayList.contains(DAY1));
    }

    @Test
    public void contains_dayInList_returnsTrue() {
        uniqueDayList.add(DAY1);
        assertTrue(uniqueDayList.contains(DAY1));
    }

    /*
    @Test
    public void contains_dayWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDayList.add(ALICE);
        Day editedAlice = new DayBuilder(ALICE).withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueDayList.contains(editedAlice));
    }
     */

    @Test
    public void add_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.add(null));
    }
    /*
    @Test
    public void add_duplicateDay_throwsDuplicateDayException() {
        uniqueDayList.add(ALICE);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.add(ALICE));
    }
    */

    @Test
    public void setDay_nullTargetDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDay(null, DAY1));
    }

    @Test
    public void setDay_nullEditedDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDay(DAY1, null));
    }

    @Test
    public void setDay_targetDayNotInList_throwsDayNotFoundException() {
        assertThrows(DayNotFoundException.class, () -> uniqueDayList.setDay(DAY1, DAY1));
    }

    @Test
    public void setDay_editedDayIsSameDay_success() {
        uniqueDayList.add(DAY1);
        uniqueDayList.setDay(DAY1, DAY1);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(DAY1);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    /*
    @Test
    public void setDay_editedDayHasSameIdentity_success() {
        uniqueDayList.add(ALICE);
        Day editedAlice = new DayBuilder(ALICE).withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueDayList.setDay(ALICE, editedAlice);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(editedAlice);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }
     */

    @Test
    public void setDay_editedDayHasDifferentIdentity_success() {
        uniqueDayList.add(DAY1);
        uniqueDayList.setDay(DAY1, MDAY2);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(MDAY2);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }
    /*
    @Test
    public void setDay_editedDayHasNonUniqueIdentity_throwsDuplicateDayException() {
        uniqueDayList.add(ALICE);
        uniqueDayList.add(BOB);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.setDay(ALICE, BOB));
    }
    */
    @Test
    public void remove_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.remove(null));
    }

    @Test
    public void remove_dayDoesNotExist_throwsDayNotFoundException() {
        assertThrows(DayNotFoundException.class, () -> uniqueDayList.remove(DAY1));
    }

    @Test
    public void remove_existingDay_removesDay() {
        uniqueDayList.add(DAY1);
        uniqueDayList.remove(DAY1);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_nullUniqueDayList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDays((UniqueDayList) null));
    }

    @Test
    public void setDays_uniqueDayList_replacesOwnListWithProvidedUniqueDayList() {
        uniqueDayList.add(DAY1);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(MDAY2);
        uniqueDayList.setDays(expectedUniqueDayList);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDays((List<Day>) null));
    }

    @Test
    public void setDays_list_replacesOwnListWithProvidedList() {
        uniqueDayList.add(DAY1);
        List<Day> dayList = Collections.singletonList(MDAY2);
        uniqueDayList.setDays(dayList);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(MDAY2);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_listWithDuplicateDays_throwsDuplicateDayException() {
        List<Day> listWithDuplicateDays = Arrays.asList(DAY1, DAY1);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.setDays(listWithDuplicateDays));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDayList.asUnmodifiableObservableList().remove(0));
    }
}
