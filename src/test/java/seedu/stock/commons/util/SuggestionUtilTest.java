package seedu.stock.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SuggestionUtilTest {
    @Test
    public void min() {
        // EP: all three numbers the same
        assertEquals(2103, SuggestionUtil.min(2103, 2103, 2103));

        // EP: two numbers same and one different
        assertEquals(2103, SuggestionUtil.min(2103, 2104, 2103));

        // EP: all three numbers different
        assertEquals(2103, SuggestionUtil.min(2103, 2104, 2105));
    }

    @Test
    public void minimumEditDistance() {
        // EP: empty strings
        assertEquals(0, SuggestionUtil.minimumEditDistance("", ""));

        // EP: empty string and nonempty string
        assertEquals(6, SuggestionUtil.minimumEditDistance("", "string"));

        // EP: equal nonempty strings
        assertEquals(0, SuggestionUtil.minimumEditDistance("string", "string"));

        // EP: different nonempty strings
        assertEquals(10, SuggestionUtil.minimumEditDistance("damith", "string"));
    }
}
