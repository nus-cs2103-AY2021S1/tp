package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BmiTest {

    @Test
    public void constructor_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bmi(null, new Height(VALID_HEIGHT_BOB)));
    }

    @Test
    public void constructor_nullHeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bmi(new Weight(VALID_WEIGHT_BOB), null));
    }

    @Test
    public void constructor_nullWeightAndNullHeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bmi(null, null));
    }

    @Test
    public void constructor_validWeightAndValidHeight_correctBmiValue() {
        assertEquals("23.6", new Bmi(new Weight(VALID_WEIGHT_BOB), new Height(VALID_HEIGHT_BOB)).value);
        assertEquals("16.9", new Bmi(new Weight(VALID_WEIGHT_AMY), new Height(VALID_HEIGHT_AMY)).value);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bmi(null));
    }

    @Test
    public void constructor_invalidBmi_throwsIllegalArgumentException() {
        String invalidBmi = "-20.5";
        assertThrows(IllegalArgumentException.class, () -> new Bmi(invalidBmi));
    }

    @Test
    public void isValidBmi() {
        // invalid bmi
        assertFalse(Bmi.isValidBmi(null)); // null input
        assertFalse(Bmi.isValidBmi("")); // empty string
        assertFalse(Bmi.isValidBmi("-10.5")); // negative bmi
        assertFalse(Bmi.isValidBmi("20.49")); // 2 decimal point bmi
        assertFalse(Bmi.isValidBmi("30.y")); // non-numeric character

        // valid bmi
        assertTrue(Bmi.isValidBmi("20.9"));
        assertTrue(Bmi.isValidBmi("30.0"));
        assertTrue(Bmi.isValidBmi("19.2"));
    }
}
