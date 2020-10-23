package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MacronutrientTest {

    private static final Fat DEFAULT_FAT_1 = new Fat(1);
    private static final Fat DEFAULT_FAT_2 = new Fat(1);
    private static final Fat DEFAULT_FAT_3 = new Fat(2);
    private static final Protein DEFAULT_PROTEIN_1 = new Protein(1);
    private static final Carbohydrate DEFAULT_CARBOHYDRATE_1 = new Carbohydrate(1);
    private static final Macronutrient MACRONUTRIENT_1 = new MacronutrientStub(4, 9);
    private static final Macronutrient MACRONUTRIENT_2 = new MacronutrientStub(9, 4);
    private static int CARBOHYDRATEMULTIPLIER = 4;
    private static int PROTEINMULTIPLIER = 4;
    private static int FATSMULTIPLIER=9;

    @Test
    public void amount_lesserThanZero_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new MacronutrientStub(-1, 4));
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

        // not instanceof Macronutrient -> return false
        assertFalse(DEFAULT_FAT_1.equals("dummy string object"));

        // same type, same totalCalories, different amount -> return false
        assertFalse(MACRONUTRIENT_1.equals(MACRONUTRIENT_2));
    }

    @Test
    public void getTotalCalories() {
        assertEquals(new MacronutrientStub(4, 4).getTotalCalories(), 16);
    }

    @Test
    public void getCaloricMultiplier() {
        assertEquals(new MacronutrientStub(4, PROTEINMULTIPLIER).getCaloricMultiplier(),PROTEINMULTIPLIER);
        assertEquals(DEFAULT_FAT_1.getCaloricMultiplier(), FATSMULTIPLIER);
        assertEquals(DEFAULT_CARBOHYDRATE_1.getCaloricMultiplier(), CARBOHYDRATEMULTIPLIER);
        assertEquals(DEFAULT_PROTEIN_1.getCaloricMultiplier(),PROTEINMULTIPLIER);
    }

    private static class MacronutrientStub extends Macronutrient {
        MacronutrientStub(int amount, int caloricMultiplier) {
            super(amount, caloricMultiplier);
        }
    }
}
