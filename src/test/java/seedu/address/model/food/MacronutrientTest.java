package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MacronutrientTest {

    private static class MacronutrientStub extends Macronutrient {
        MacronutrientStub(String name, int amount, int caloricMultiplier) {
            super(name, amount, caloricMultiplier);
        }
    }

    private static final Fat DEFAULT_FAT_1 = new Fat(1);
    private static final Fat DEFAULT_FAT_2 = new Fat(1);
    private static final Fat DEFAULT_FAT_3 = new Fat(2);


    private static final Protein DEFAULT_PROTEIN_1 = new Protein(1);

    private static final Macronutrient MACRONUTRIENT_1 = new MacronutrientStub("dummy", 4, 9);
    private static final Macronutrient MACRONUTRIENT_2 = new MacronutrientStub("dummy", 9, 4);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new MacronutrientStub(null, 1, 1));
    }

    @Test
    public void amount_lesserThanZero_throwIllegalArgumentException() {

    }

    @Test
    public void equals() {
        // identical -> return true
        assertTrue(DEFAULT_FAT_1.equals(DEFAULT_FAT_1)); // identical

        // same type same amount -> return true
        assertTrue(DEFAULT_FAT_1.equals(DEFAULT_FAT_2)); // same type same amount

        // same type differnet amount -> return false
        assertFalse(DEFAULT_FAT_1.equals(DEFAULT_FAT_3));

        // different type -> return false
        assertFalse(DEFAULT_FAT_1.equals(DEFAULT_PROTEIN_1));

        // same type, same totalCalories, different amount -> return false
        assertFalse(MACRONUTRIENT_1.equals(MACRONUTRIENT_2));
    }
}
