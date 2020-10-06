package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CarbohydrateTest {


    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidCarb = "";
        assertThrows(NumberFormatException.class, () -> new Carbohydrate(Integer.parseInt(invalidCarb)));
    }

    @Test
    public void isValidCarbohydrate() {
        // null address
        assertThrows(NumberFormatException.class, () -> Carbohydrate.isValid(null));

        // invalid addresses
        assertFalse(Carbohydrate.isValid("")); // empty string
        assertFalse(Carbohydrate.isValid(" ")); // spaces only

        // valid addresses
        assertTrue(Carbohydrate.isValid("1234"));
        assertTrue(Carbohydrate.isValid("123456")); // one character
        assertTrue(Carbohydrate.isValid("132454897")); // long address
    }
}
