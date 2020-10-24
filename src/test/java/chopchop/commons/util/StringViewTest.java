// StringViewTest.java

package chopchop.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static chopchop.testutil.Assert.assertThrows;

public class StringViewTest {

    @Test
    public void test_stringViews() {

        var sv1 = new StringView("abcdef ghi jklmno ");
        var sv2 = new StringView("owo uwu AYAYA");

        assertEquals(3, sv1.find("def"));
        assertEquals(7, sv1.find(new StringView("ghi")));
        assertEquals(1, sv1.find('b'));

        assertTrue(sv2.startsWith("owo"));
        assertTrue(sv2.startsWith(new StringView("owo")));

        assertEquals('o', sv2.at(2));
        assertEquals('a', sv1.front());
        assertEquals('A', sv2.back());

        assertThrows(AssertionError.class, () -> new StringView("").front());
        assertThrows(AssertionError.class, () -> new StringView("").back());

        assertEquals("owo", sv2.take(3).toString());
        assertEquals("AYAYA", sv2.takeLast(5).toString());

        assertEquals("owo uwu AYAYA", sv2.take(400).toString());
        assertEquals("owo uwu AYAYA", sv2.takeLast(400).toString());

        assertEquals(new StringView("ayaya").uppercase().toString(), "AYAYA");
        assertEquals(new StringView("AYAYA").lowercase().toString(), "ayaya");

        assertTrue(new StringView("asdf").equals("asdf"));
        assertEquals(sv1, sv1);
        assertEquals(sv1, new StringView(sv1.toString()));
        assertNotEquals(sv1, 3);

        assertFalse(sv1.startsWith("owo"));
        assertFalse(sv2.startsWith(new StringView("kekw")));

        assertEquals("asdf".hashCode(), new StringView("asdf").hashCode());
    }
}
