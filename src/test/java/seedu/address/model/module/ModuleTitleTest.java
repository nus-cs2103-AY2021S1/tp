package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleTitle(null));
    }
    @Test
    public void hashCodeTest() {
        ModuleTitle title1 = new ModuleTitle("cs2100");
        ModuleTitle title2 = new ModuleTitle("CS2100");
        assertEquals(title1.hashCode(), title1.hashCode());
        assertNotEquals(title1.hashCode(), title2.hashCode());
    }
}
