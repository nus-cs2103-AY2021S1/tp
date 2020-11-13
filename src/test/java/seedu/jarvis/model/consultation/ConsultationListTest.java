package seedu.jarvis.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_FIVE;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_FOUR;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_ONE;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_THREE;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_TWO;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_ONE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.jarvis.model.masterycheck.MasteryCheckList;
import seedu.jarvis.testutil.ConsultationBuilder;

public class ConsultationListTest {

    private final ConsultationList consultationList = new ConsultationList();
    private final ConsultationList consultationListTwo = new ConsultationList();
    private final MasteryCheckList masteryCheckList = new MasteryCheckList();

    private final List<Consultation> consultationListTest = new ArrayList<>();

    @Test
    void add_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consultationList.add(null));
    }

    @Test
    void add_typicalConsultation_success() {
        consultationList.add(TEST_CONSULTATION_ONE);
        assertEquals(consultationList.contains(TEST_CONSULTATION_ONE), true);
    }

    @Test
    void contains_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consultationList.contains(null));
    }

    @Test
    void contains_typicalConsultation_success() {
        consultationList.add(TEST_CONSULTATION_ONE);
        assertEquals(consultationList.contains(TEST_CONSULTATION_ONE), true);
    }

    @Test
    public void contains_consultationWithSameIdentityFieldsInList_returnsTrue() {
        consultationList.add(TEST_CONSULTATION_ONE);
        Consultation editedConsultation = new ConsultationBuilder(TEST_CONSULTATION_ONE).build();
        assertTrue(consultationList.contains(editedConsultation));
    }

    @Test
    void setConsultations_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consultationList.setConsultations(null));
    }

    @Test
    public void setConsultations_equalLists_success() {
        consultationListTest.add(TEST_CONSULTATION_ONE);
        consultationListTest.add(TEST_CONSULTATION_TWO);
        consultationListTest.add(TEST_CONSULTATION_THREE);
        consultationListTest.add(TEST_CONSULTATION_FOUR);
        consultationListTest.add(TEST_CONSULTATION_FIVE);
        consultationList.setConsultations(consultationListTest);

        ConsultationList equivalentConsultationList = new ConsultationList();
        equivalentConsultationList.add(TEST_CONSULTATION_ONE);
        equivalentConsultationList.add(TEST_CONSULTATION_TWO);
        equivalentConsultationList.add(TEST_CONSULTATION_THREE);
        equivalentConsultationList.add(TEST_CONSULTATION_FOUR);
        equivalentConsultationList.add(TEST_CONSULTATION_FIVE);

        assertEquals(consultationList, equivalentConsultationList);
    }

    @Test
    void asObservableList_consultationList_success() {
        assertFalse(consultationList.getClass().equals(consultationList.asObservableList().getClass()));
    }

    @Test
    public void remove_nullConsultation_returnsFalse() {
        assertThrows(NullPointerException.class, () -> consultationList.remove(null));
    }

    @Test
    public void remove_consultationNotInList_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> consultationList.remove(TEST_CONSULTATION_ONE));
    }

    @Test
    public void contains_consultationInList_returnsTrue() {
        consultationList.add(TEST_CONSULTATION_ONE);
        assertTrue(consultationList.contains(TEST_CONSULTATION_ONE));
    }

    @Test
    public void contains_consultationInList_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> consultationList.remove(TEST_CONSULTATION_ONE));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        consultationList.add(TEST_CONSULTATION_ONE);
        assertTrue(consultationList.equals(consultationList));
    }

    @Test
    public void equals_equalConsultationList_returnsTrue() {
        consultationList.add(TEST_CONSULTATION_ONE);
        consultationListTwo.add(TEST_CONSULTATION_ONE);
        assertTrue(consultationList.equals(consultationListTwo));
    }

    @Test
    public void equals_differentConsultationList_returnsFalse() {
        consultationList.add(TEST_CONSULTATION_ONE);
        consultationListTwo.add(TEST_CONSULTATION_TWO);
        assertFalse(consultationList.equals(consultationListTwo));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        consultationList.add(TEST_CONSULTATION_ONE);
        masteryCheckList.add(TEST_MASTERY_CHECK_ONE);
        assertFalse(consultationList.equals(masteryCheckList));
    }
}
