package seedu.zookeep.model.medicalcondition;

import static seedu.zookeep.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalCondition(null));
    }

    @Test
    public void constructor_invalidMedicalConditionName_throwsIllegalArgumentException() {
        String invalidMedicalConditionName = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalCondition(invalidMedicalConditionName));
    }

    @Test
    public void isValidMedicalConditionName() {
        // null medicalCondition name
        assertThrows(NullPointerException.class, () -> MedicalCondition.isValidMedicalConditionName(null));
    }

}
