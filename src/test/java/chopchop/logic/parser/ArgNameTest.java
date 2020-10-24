// ArgNameTest.java

package chopchop.logic.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static chopchop.testutil.Assert.assertThrows;

public class ArgNameTest {

    @Test
    void test_argName() {
        // check that this stuff throws
        assertThrows(AssertionError.class, () -> new ArgName(null));
        assertThrows(AssertionError.class, () -> new ArgName(""));
        assertThrows(AssertionError.class, () -> new ArgName("/foo"));

        assertEquals(new ArgName("foo").name(), "foo");
        assertEquals(new ArgName("foo").toString(), "/foo");

        assertNotEquals(new ArgName("foo"), "foo");

        assertEquals(new ArgName("foo"), new ArgName("foo"));
        assertNotEquals(new ArgName("FOO"), new ArgName("foo"));

        assertEquals(new ArgName("foo").hashCode(), new ArgName("foo").hashCode());
        assertNotEquals(new ArgName("FOO").hashCode(), new ArgName("foo").hashCode());
    }
}
