package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MessagesTest {
    @Test
    public void appendPluralChar_negativeInput_throwsAssertionError() {
        assertThrows(AssertionError.class, ()-> Messages.appendPluralChar(-1));
    }
    @Test
    public void appendPluralChar_oneAsInput_returnsEmptyString() {
        String expected = "";
        assertEquals(expected, Messages.appendPluralChar(1));
    }
    @Test
    public void appendPluralChar_validInputsNotOne_returnsStringS() {
        String expected = "s";
        assertEquals(expected, Messages.appendPluralChar(0));
        assertEquals(expected, Messages.appendPluralChar(2));
    }
}
