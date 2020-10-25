package seedu.medmoriser.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medmoriser.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.medmoriser.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.medmoriser.testutil.Assert.assertThrows;
import static seedu.medmoriser.testutil.TypicalQAndA.ALICE;
import static seedu.medmoriser.testutil.TypicalQAndA.getTypicalMedmoriser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.medmoriser.model.qanda.QAndA;
import seedu.medmoriser.model.qanda.exceptions.DuplicateQAndAException;
import seedu.medmoriser.testutil.QAndABuilder;

public class MedmoriserTest {

    private final Medmoriser medmoriser = new Medmoriser();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), medmoriser.getQAndAList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medmoriser.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMedmoriser_replacesData() {
        Medmoriser newData = getTypicalMedmoriser();
        medmoriser.resetData(newData);
        assertEquals(newData, medmoriser);
    }

    @Test
    public void resetData_withDuplicateQAndAs_throwsDuplicateQAndAException() {
        // Two qAndAs with the same identity fields
        QAndA editedAlice = new QAndABuilder(ALICE).withAnswer(VALID_ANSWER_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<QAndA> newQAndAs = Arrays.asList(ALICE, editedAlice);
        MedmoriserStub newData = new MedmoriserStub(newQAndAs);

        assertThrows(DuplicateQAndAException.class, () -> medmoriser.resetData(newData));
    }

    @Test
    public void hasQAndA_nullQAndA_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medmoriser.hasQAndA(null));
    }

    @Test
    public void hasQAndA_qAndANotInMedmoriser_returnsFalse() {
        assertFalse(medmoriser.hasQAndA(ALICE));
    }

    @Test
    public void hasQAndA_qAndAInMedmoriser_returnsTrue() {
        medmoriser.addQAndA(ALICE);
        assertTrue(medmoriser.hasQAndA(ALICE));
    }

    @Test
    public void hasQAndA_qAndAWithSameIdentityFieldsInMedmoriser_returnsTrue() {
        medmoriser.addQAndA(ALICE);
        QAndA editedAlice = new QAndABuilder(ALICE).withAnswer(VALID_ANSWER_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(medmoriser.hasQAndA(editedAlice));
    }

    @Test
    public void getQAndAList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> medmoriser.getQAndAList().remove(0));
    }


    /**
     * A stub ReadOnlyAddressBook whose qAndAs list can violate interface constraints.
     */
    private static class MedmoriserStub implements ReadOnlyMedmoriser {
        private final ObservableList<QAndA> qAndAs = FXCollections.observableArrayList();

        MedmoriserStub(Collection<QAndA> qAndAs) {
            this.qAndAs.setAll(qAndAs);
        }

        @Override
        public ObservableList<QAndA> getQAndAList() {
            return qAndAs;
        }
    }

}
