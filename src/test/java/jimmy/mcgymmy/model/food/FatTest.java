package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FatTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NumberFormatException.class, () -> new Fat(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidFat = "12@123";
        assertThrows(NumberFormatException.class, () -> new Fat(Integer.parseInt(invalidFat)));
    }

    @Test
    public void isValid() {
        // null fat
        assertThrows(NullPointerException.class, () -> Fat.isValid(null));

        // blank fat
        Assertions.assertFalse(Fat.isValid("")); // empty string
        Assertions.assertFalse(Fat.isValid(" ")); // spaces only

        // invalid parts
        Assertions.assertFalse(Fat.isValid("peterjack@-")); // invalid fat name
        Assertions.assertFalse(Fat.isValid("peterjack@exam_ple.com")); // underscore in fat name
        Assertions.assertFalse(Fat.isValid("peter jack@example.com")); // spaces in fat part
        Assertions.assertFalse(Fat.isValid("peterjack@exam ple.com")); // spaces in fat name
        Assertions.assertFalse(Fat.isValid(" 1234")); // leading space
        Assertions.assertFalse(Fat.isValid("1234 ")); // trailing space
        Assertions.assertFalse(Fat.isValid("-1234")); // negative value

        // valid fat
        Assertions.assertTrue(Fat.isValid("123412"));
        Assertions.assertTrue(Fat.isValid("911"));
        Assertions.assertTrue(Fat.isValid("123456789"));
    }
}
