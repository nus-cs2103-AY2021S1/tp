package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModularCreditsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModularCredits(null));
    }

    @Test
    public void hashCodeTest() {
        ModularCredits mc1 = new ModularCredits("1");
        ModularCredits mc2 = new ModularCredits("2");
        assertEquals(mc1.hashCode(), mc1.hashCode());
        assertNotEquals(mc1.hashCode(), mc2.hashCode());
    }
}
