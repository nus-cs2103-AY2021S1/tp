package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ParserUtilTest {

    @Test
    public void parseRoomNumber_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoomNumber(null));
    }

    @Test
    public void parseRoomNumber_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomNumber("10 a"));
    }

    @Test
    public void parseRoomNumber_negativeInteger_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoomNumber("-1"));
    }

    @Test
    public void parseRoomNumber_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseRoomNumber(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseRoomNumber_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(1, ParserUtil.parseRoomNumber("1"));

        // Leading and trailing whitespaces
        assertEquals(1, ParserUtil.parseRoomNumber("  1  "));
    }

    @Test
    public void parseTaskIndex_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskIndex(null));
    }

    @Test
    public void parseTaskIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskIndex("10 a"));
    }

    @Test
    public void parseTaskIndex_negativeInteger_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskIndex("-1"));
    }

    @Test
    public void parseTaskIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTaskIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseTaskIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(VALID_TASK_INDEX_ONE, ParserUtil.parseTaskIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(VALID_TASK_INDEX_ONE, ParserUtil.parseTaskIndex("  1  "));
    }

    @Test
    public void arePrefixesPresent() {
        Prefix prefixOnePresent = mock(Prefix.class);
        Prefix prefixTwoPresent = mock(Prefix.class);
        Prefix prefixThreeMissing = mock(Prefix.class);

        ArgumentMultimap argMultimap = mock(ArgumentMultimap.class);
        when(argMultimap.getValue(prefixOnePresent)).thenReturn(Optional.of("one"));
        when(argMultimap.getValue(prefixTwoPresent)).thenReturn(Optional.of("two"));
        when(argMultimap.getValue(prefixThreeMissing)).thenReturn(Optional.empty());

        // One missing prefix - returns false
        assertFalse(ParserUtil.arePrefixesPresent(argMultimap, prefixThreeMissing));

        // One present and one missing prefixes - returns false
        assertFalse(ParserUtil.arePrefixesPresent(argMultimap, prefixOnePresent, prefixThreeMissing));

        // No prefixes - returns true
        assertTrue(ParserUtil.arePrefixesPresent(argMultimap));

        // Two present prefixes - returns true
        assertTrue(ParserUtil.arePrefixesPresent(argMultimap, prefixOnePresent, prefixTwoPresent));
    }

    @Test
    public void isExactlyOnePrefixPresent() {
        Prefix prefixOnePresent = mock(Prefix.class);
        Prefix prefixTwoPresent = mock(Prefix.class);
        Prefix prefixThreeMissing = mock(Prefix.class);

        ArgumentMultimap argMultimap = mock(ArgumentMultimap.class);
        when(argMultimap.getValue(prefixOnePresent)).thenReturn(Optional.of("one"));
        when(argMultimap.getValue(prefixTwoPresent)).thenReturn(Optional.of("two"));
        when(argMultimap.getValue(prefixThreeMissing)).thenReturn(Optional.empty());

        // No prefixes - returns false
        assertFalse(ParserUtil.isExactlyOnePrefixPresent(argMultimap));

        // Two present prefixes - returns false
        assertFalse(ParserUtil.isExactlyOnePrefixPresent(argMultimap, prefixOnePresent, prefixTwoPresent));

        // Two present and one missing prefixes - returns false
        assertFalse(ParserUtil.isExactlyOnePrefixPresent(argMultimap,
                prefixOnePresent, prefixTwoPresent, prefixThreeMissing));

        // One present prefix - returns true
        assertTrue(ParserUtil.isExactlyOnePrefixPresent(argMultimap, prefixOnePresent));

        // One present and one missing prefixes - returns true
        assertTrue(ParserUtil.isExactlyOnePrefixPresent(argMultimap, prefixOnePresent, prefixThreeMissing));
    }
}
