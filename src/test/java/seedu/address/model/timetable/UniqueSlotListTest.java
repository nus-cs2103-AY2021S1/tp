package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.MA1101R;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1630_1730;
import static seedu.address.testutil.TypicalSlots.MA1101R_THURSDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.UPPER_BODY_WEDNESDAY_1600_1800;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.timetable.exceptions.DuplicateSlotException;
import seedu.address.model.timetable.exceptions.SlotOverlapDurationException;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.SlotBuilder;

public class UniqueSlotListTest {

    private final UniqueSlotList uniqueSlotList = new UniqueSlotList();

    @Test
    public void contains_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.contains(null));
    }

    @Test
    public void contains_slotNotInList_returnsFalse() {
        assertFalse(uniqueSlotList.contains(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void contains_slotInList_returnsTrue() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertTrue(uniqueSlotList.contains(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void contains_slotInListWithDifferentActivity_returnsTrue() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertTrue(uniqueSlotList.contains(UPPER_BODY_WEDNESDAY_1600_1800));
    }

    @Test
    public void hasOverlapDuration_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.hasOverlapDuration(null));
    }

    @Test
    public void hasOverlapDuration_noOverlap_returnsFalse() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertFalse(uniqueSlotList.hasOverlapDuration(MA1101R_THURSDAY_1600_1800));
    }

    @Test
    public void hasOverlapDuration_overlap_returnsTrue() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertTrue(uniqueSlotList.hasOverlapDuration(LEG_DAY_WEDNESDAY_1630_1730));
    }

    @Test
    public void add_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.add(null));
    }

    @Test
    public void add_duplicateSlot_throwsDuplicateSlotException() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertThrows(DuplicateSlotException.class, () -> uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void add_overlapSlot_throwsSlotOverlapDurationException() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertThrows(SlotOverlapDurationException.class, () -> uniqueSlotList.add(LEG_DAY_WEDNESDAY_1630_1730));
    }

    @Test
    public void remove_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.remove((Slot) null));
    }

    @Test
    public void remove_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.remove((Activity) null));
    }

    @Test
    public void remove_existingSlot_removesSlot() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        uniqueSlotList.remove(LEG_DAY_WEDNESDAY_1600_1800);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void remove_existingActivity_removesActivity() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        uniqueSlotList.remove(LEG_DAY);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void remove_activityDoesNotExist_success() {
        uniqueSlotList.add(MA1101R_THURSDAY_1600_1800);
        uniqueSlotList.remove(LEG_DAY);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(MA1101R_THURSDAY_1600_1800);
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlot_nullTargetActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.setSlot(null, GES1028));
    }

    @Test
    public void setSlot_nullEditedActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.setSlot(GES1028, null));
    }

    @Test
    public void setSlot_editedActivityIsSameActivity_success() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        uniqueSlotList.setSlot(LEG_DAY, LEG_DAY);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlot_editedActivityHasSameIdentity_success() {
        uniqueSlotList.add(MA1101R_THURSDAY_1600_1800);
        Lesson editedMa1101R = new LessonBuilder(MA1101R).withTags(VALID_LESSON_TAG_EASY).build();
        uniqueSlotList.setSlot(MA1101R, editedMa1101R);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(new SlotBuilder(MA1101R_THURSDAY_1600_1800).withActivity(editedMa1101R).build());
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlot_editedActivityHasDifferentIdentity_success() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        uniqueSlotList.setSlot(LEG_DAY, UPPER_BODY);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(UPPER_BODY_WEDNESDAY_1600_1800);
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlots_nullUniqueSlotList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.setSlots((UniqueSlotList) null));
    }

    @Test
    public void setSlots_uniqueSlotList_replacesPreviousList() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(MA1101R_THURSDAY_1600_1800);
        uniqueSlotList.setSlots(expectedUniqueSlotList);
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlots_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSlotList.setSlots((List<Slot>) null));
    }

    @Test
    public void setSlots_list_replacesOwnListWithProvidedList() {
        uniqueSlotList.add(LEG_DAY_WEDNESDAY_1600_1800);
        List<Slot> slotList = Collections.singletonList(UPPER_BODY_WEDNESDAY_1600_1800);
        uniqueSlotList.setSlots(slotList);
        UniqueSlotList expectedUniqueSlotList = new UniqueSlotList();
        expectedUniqueSlotList.add(UPPER_BODY_WEDNESDAY_1600_1800);
        assertEquals(expectedUniqueSlotList, uniqueSlotList);
    }

    @Test
    public void setSlots_listWithDuplicateSlots_throwsDuplicateSlotException() {
        List<Slot> listWithDuplicateSlots = Arrays.asList(LEG_DAY_WEDNESDAY_1600_1800, UPPER_BODY_WEDNESDAY_1600_1800);
        assertThrows(DuplicateSlotException.class, () -> uniqueSlotList.setSlots(listWithDuplicateSlots));
    }

    @Test
    public void setSlots_listWithOverlappingSlots_throwsSlotOverlapDurationException() {
        List<Slot> listWithOverlappingSlots = Arrays.asList(LEG_DAY_WEDNESDAY_1600_1800, LEG_DAY_WEDNESDAY_1630_1730);
        assertThrows(SlotOverlapDurationException.class, () -> uniqueSlotList.setSlots(listWithOverlappingSlots));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueSlotList.asUnmodifiableObservableList().remove(0));
    }
}
