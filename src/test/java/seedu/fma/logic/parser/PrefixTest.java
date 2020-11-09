package seedu.fma.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PrefixTest {

    @Test
    void equals_samePrefixText_returnTrue() {
        assertTrue(new Prefix("a/").equals(new Prefix("a/")));
        assertTrue(new Prefix("aefawefwa/").equals(new Prefix("aefawefwa/")));
    }
    @Test
    void equals_differentPrefixText_returnFalse() {
        assertFalse(new Prefix("fewf/").equals(new Prefix("a/")));
        assertFalse(new Prefix("aefawefwa").equals(new Prefix("aefawefwa/")));
    }
}
