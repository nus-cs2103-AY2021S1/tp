package seedu.jarvis.model.masterycheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_ONE;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_FIVE;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_FOUR;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_ONE;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_THREE;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_TWO;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.model.consultation.ConsultationList;
import seedu.jarvis.model.masterycheck.exceptions.MasteryCheckNotFoundException;
import seedu.jarvis.testutil.MasteryCheckBuilder;

public class MasteryCheckListTest {
    private final MasteryCheckList masteryCheckList = new MasteryCheckList();
    private final MasteryCheckList masteryCheckListTwo = new MasteryCheckList();
    private final ConsultationList consultationList = new ConsultationList();

    private final List<MasteryCheck> masteryCheckListTest = new ArrayList<>();

    @Test
    void add_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> masteryCheckList.add(null));
    }

    @Test
    void add_typicalMasteryCheck_success() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        assertEquals(masteryCheckList.contains(TEST_MASTERY_CHECK_ONE), true);
    }

    @Test
    void contains_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> masteryCheckList.contains(null));
    }

    @Test
    void contains_typicalMasteryCheck_success() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        assertEquals(masteryCheckList.contains(TEST_MASTERY_CHECK_ONE), true);
    }

    @Test
    public void contains_masteryCheckWithSameIdentityFieldsInList_returnsTrue() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        MasteryCheck editedMasteryCheck = new MasteryCheckBuilder(TEST_MASTERY_CHECK_ONE).build();
        assertTrue(masteryCheckList.contains(editedMasteryCheck));
    }

    @Test
    void setMasteryChecks_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> masteryCheckList.setMasteryChecks(null));
    }

    @Test
    public void setMasteryChecks_equalLists_success() {
        masteryCheckListTest.add(TEST_MASTERY_CHECK_ONE);
        masteryCheckListTest.add(TEST_MASTERY_CHECK_TWO);
        masteryCheckListTest.add(TEST_MASTERY_CHECK_THREE);
        masteryCheckListTest.add(TEST_MASTERY_CHECK_FOUR);
        masteryCheckListTest.add(TEST_MASTERY_CHECK_FIVE);
        masteryCheckList.setMasteryChecks(masteryCheckListTest);

        MasteryCheckList equivalentMasteryCheckList = new MasteryCheckList();
        equivalentMasteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        equivalentMasteryCheckList.add(TEST_MASTERY_CHECK_TWO);
        equivalentMasteryCheckList.add(TEST_MASTERY_CHECK_THREE);
        equivalentMasteryCheckList.add(TEST_MASTERY_CHECK_FOUR);
        equivalentMasteryCheckList.add(TEST_MASTERY_CHECK_FIVE);

        assertEquals(masteryCheckList, equivalentMasteryCheckList);
    }

    @Test
    void asObservableList_masteryCheckList_success() {
        assertFalse(masteryCheckList.getClass().equals(masteryCheckList.asObservableList().getClass()));
    }

    @Test
    public void remove_nullMasteryCheck_returnsFalse() {
        assertThrows(NullPointerException.class, () -> masteryCheckList.remove(null));
    }

    @Test
    public void removes_masteryCheckNotInList_throwsMasteryCheckNotFoundException() {
        assertThrows(MasteryCheckNotFoundException.class, () -> masteryCheckList.remove(TEST_MASTERY_CHECK_ONE));
    }

    @Test
    public void contains_masteryCheckInList_returnsTrue() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        assertTrue(masteryCheckList.contains(TEST_MASTERY_CHECK_ONE));
    }

    @Test
    public void contains_masteryCheckInList_throwsMasteryCheckNotFoundException() {
        assertThrows(MasteryCheckNotFoundException.class, () -> masteryCheckList.remove(TEST_MASTERY_CHECK_ONE));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        assertTrue(masteryCheckList.equals(masteryCheckList));
    }

    @Test
    public void equals_equalMasteryCheckList_returnsTrue() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        masteryCheckListTwo.add(TEST_MASTERY_CHECK_ONE);
        assertTrue(masteryCheckList.equals(masteryCheckListTwo));
    }

    @Test
    public void equals_differentMasteryCheckList_returnsFalse() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        masteryCheckListTwo.add(TEST_MASTERY_CHECK_TWO);
        assertFalse(masteryCheckList.equals(masteryCheckListTwo));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        consultationList.add(TEST_CONSULTATION_ONE);
        assertFalse(masteryCheckList.equals(consultationList));
    }
}
