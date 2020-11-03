package seedu.address.logic.parser.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;

class IdParserUtilTest {

    @Test
    public void parsePropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> IdParserUtil.parsePropertyId(null));
    }

    @Test
    public void parsePropertyId_invalidPropertyId_throwsParseException() {
        assertThrows(ParseException.class,
                () -> IdParserUtil.parsePropertyId(""));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parsePropertyId("p1"));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parsePropertyId("B1"));
    }

    @Test
    public void parsePropertyId_validPropertyId_returnsPropertyId() throws ParseException {
        PropertyId expected = new PropertyId(1);
        assertEquals(expected, IdParserUtil.parsePropertyId("P1"));
        assertEquals(expected, IdParserUtil.parsePropertyId("\t P1"));
    }

    @Test
    public void parseSellerId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> IdParserUtil.parseSellerId(null));
    }

    @Test
    public void parseSellerId_invalidSellerId_throwsParseException() {
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseSellerId(""));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseSellerId("s1"));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseSellerId("B1"));
    }

    @Test
    public void parseSellerId_validSellerId_returnsSellerId() throws ParseException {
        SellerId expected = new SellerId(1);
        assertEquals(expected, IdParserUtil.parseSellerId("S1"));
        assertEquals(expected, IdParserUtil.parseSellerId("\t S1"));
    }

    @Test
    public void parseBidderId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> IdParserUtil.parseBidderId(null));
    }

    @Test
    public void parseBidderId_invalidBidderId_throwsParseException() {
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseBidderId(""));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseBidderId("b1"));
        assertThrows(ParseException.class,
                () -> IdParserUtil.parseBidderId("S1"));
    }

    @Test
    public void parseBidderId_validBidderId_returnsBidderId() throws ParseException {
        BidderId expected = new BidderId(1);
        assertEquals(expected, IdParserUtil.parseBidderId("B1"));
        assertEquals(expected, IdParserUtil.parseBidderId("\t B1"));
    }
}
