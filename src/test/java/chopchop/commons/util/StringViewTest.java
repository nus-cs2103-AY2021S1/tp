// StringViewTest.java

package chopchop.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StringViewTest {

    @Test
    public void test_stringViews() {

        var sv1 = new StringView("abcdef ghi jklmno ");
        var sv2 = new StringView("owo uwu AYAYA");

        assertEquals(3, sv1.find("def"));
        assertEquals(7, sv1.find(new StringView("ghi")));
    }
}
