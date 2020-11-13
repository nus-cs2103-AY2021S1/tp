package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ArgumentMultimapUtil.hasAllPrefixes;
import static seedu.address.logic.parser.ArgumentMultimapUtil.hasOnlyOneGivenPrefix;
import static seedu.address.logic.parser.ArgumentMultimapUtil.hasOnlyOnePrefix;
import static seedu.address.logic.parser.ArgumentMultimapUtil.hasOnlyTwoPrefixes;

import org.junit.jupiter.api.Test;

class ArgumentMultimapUtilTest {
    private ArgumentMultimap args = new ArgumentMultimap();
    // the tokenized ArgumentMultimap might have entries whose key is an empty Prefix, which should not be counted
    private Prefix emptyPrefix = new Prefix("");
    private Prefix prefix1 = new Prefix("prefix1");
    private Prefix prefix2 = new Prefix("prefix2");
    private Prefix prefix3 = new Prefix("prefix3");

    @Test
    void arePrefixesPresent_allPrefixesPresent_true() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix2, "");
        args.put(emptyPrefix, "");
        assertTrue(hasAllPrefixes(args, prefix1));
        assertTrue(hasAllPrefixes(args, prefix1, prefix2));
    }

    @Test
    void arePrefixesPresent_notAllPrefixesPresent_false() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(emptyPrefix, "");
        assertFalse(hasAllPrefixes(args, prefix2));
        assertFalse(hasAllPrefixes(args, prefix1, prefix2));
    }

    @Test
    void isOnlyOneGivenPrefixPresent_onlyOneGivenPrefixPresent_true() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix3, "");
        assertTrue(hasOnlyOneGivenPrefix(args, prefix1));
        args.put(emptyPrefix, "");
        assertTrue(hasOnlyOneGivenPrefix(args, prefix1));
        assertTrue(hasOnlyOneGivenPrefix(args, prefix1, prefix2));
    }

    @Test
    void isOnlyOneGivenPrefixPresent_twoGivenPrefixPresent_false() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix2, "");
        args.put(prefix3, "");
        assertFalse(hasOnlyOneGivenPrefix(args, prefix1, prefix2));
    }

    @Test
    void isOnlyOnePrefixPresent_moreThanOnePrefixesInMap_false() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix3, "");
        assertFalse(hasOnlyOnePrefix(args, prefix1, prefix2));
    }

    @Test
    void isOnlyOnePrefixPresent_onlyOneValidPrefixInMap_true() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        assertTrue(hasOnlyOnePrefix(args, prefix1));
        assertTrue(hasOnlyOnePrefix(args, prefix1, prefix2));
        args.put(emptyPrefix, "");
        assertTrue(hasOnlyOnePrefix(args, prefix1, prefix2));
    }

    @Test
    void areOnlyTheseTwoPrefixesPresent_moreThanTwoPrefixes_false() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix2, "");
        args.put(prefix3, "");
        assertFalse(hasOnlyTwoPrefixes(args, prefix1, prefix2));
    }

    @Test
    void areOnlyTheseTwoPrefixesPresent_onlyTheseTwoPrefixesInMap_true() {
        args = new ArgumentMultimap();
        args.put(prefix1, "");
        args.put(prefix2, "");
        assertTrue(hasOnlyTwoPrefixes(args, prefix1, prefix2));
        args.put(emptyPrefix, "");
        assertTrue(hasOnlyTwoPrefixes(args, prefix1, prefix2));
    }
}
