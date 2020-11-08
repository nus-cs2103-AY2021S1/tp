package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1600_1800;
import static seedu.address.testutil.TypicalSlots.MA1101R_THURSDAY_1600_1800;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SlotDayPredicateTest {

    @Test
    public void test_slotDayMatchesGivenDay_returnsTrue() {

        SlotDayPredicate predicate = new SlotDayPredicate(Collections.singletonList("wednesday"));
        assertTrue(predicate.test(LEG_DAY_WEDNESDAY_1600_1800));
    }

    @Test
    public void test_slotDayDoesNotMatchGivenDay_returnsFalse() {

        SlotDayPredicate predicate = new SlotDayPredicate(Collections.singletonList("wednesday"));
        assertFalse(predicate.test(MA1101R_THURSDAY_1600_1800));
    }

    @Test
    public void equals() {
        List<String> firstPredicateDayList = Collections.singletonList("monday");
        List<String> secondPredicateDayList = Collections.singletonList("tuesday");

        SlotDayPredicate firstPredicate = new SlotDayPredicate(firstPredicateDayList);
        SlotDayPredicate secondPredicate = new SlotDayPredicate(secondPredicateDayList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same fields -> returns true
        SlotDayPredicate firstPredicateCopy = new SlotDayPredicate(firstPredicateDayList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different day -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
