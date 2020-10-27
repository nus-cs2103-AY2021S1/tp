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
}
