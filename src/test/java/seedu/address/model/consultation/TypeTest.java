package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    public void fromType() {
        // null attendance
        assertThrows(NullPointerException.class, () -> new Type(null));

        // invalid type
        assertThrows(IllegalArgumentException.class, () -> new Type(""));
        assertThrows(IllegalArgumentException.class, () -> new Type("hello"));

        // valid attendances
        assertEquals(new Type("personal").toString(), "Personal");
        assertEquals(new Type("group").toString(), "Group");
    }

    @Test
    public void equals_isSymmetric() {
        Type x = new Type("personal");
        Type y = new Type("personal");
        assertEquals(x, y);
        assertEquals(y, x);
        assertEquals(x.hashCode(), y.hashCode());
    }
}
