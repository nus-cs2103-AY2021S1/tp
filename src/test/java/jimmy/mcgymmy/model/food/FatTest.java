package jimmy.mcgymmy.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.food.Fat;
import jimmy.mcgymmy.testutil.Assert;

public class FatTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Fat(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidFat = "12@123";
        Assert.assertThrows(NumberFormatException.class, () -> new Fat(Integer.parseInt(invalidFat)));
    }

    @Test
    public void isValid() {
        // null fat
        Assert.assertThrows(NullPointerException.class, () -> Fat.isValid(null));

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

        // valid fat
        Assertions.assertTrue(Fat.isValid("PeterJack_1190@example.com"));
        Assertions.assertTrue(Fat.isValid("a@bc")); // minimal
        Assertions.assertTrue(Fat.isValid("test@fathost")); // alphabets only
        Assertions.assertTrue(Fat.isValid("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters fat part
        Assertions.assertTrue(Fat.isValid("123@145")); // numeric fat part and fat name
        Assertions.assertTrue(Fat.isValid("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        Assertions.assertTrue(Fat.isValid("peter_jack@very-very-very-long-example.com")); // long fat name
        Assertions.assertTrue(Fat.isValid("if.you.dream.it_you.can.do.it@example.com")); // long fat part
    }
}
