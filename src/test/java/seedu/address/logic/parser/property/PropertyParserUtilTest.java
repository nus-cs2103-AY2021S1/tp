package seedu.address.logic.parser.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_RENTAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_NAME;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_PROPERTY_TYPE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.price.PriceFilter;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

class PropertyParserUtilTest {

    @Test
    public void parsePropertyName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parsePropertyName(null));
    }

    @Test
    public void parsePropertyName_invalidName_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePropertyName(INVALID_PROPERTY_NAME));
    }

    @Test
    public void parsePropertyName_validName_returnsPropertyName() throws ParseException {
        PropertyName expected = new PropertyName(VALID_PROPERTY_NAME_ANCHORVALE);
        assertEquals(expected, PropertyParserUtil.parsePropertyName(VALID_PROPERTY_NAME_ANCHORVALE));
        assertEquals(expected,
                PropertyParserUtil.parsePropertyName("\t " + VALID_PROPERTY_NAME_ANCHORVALE));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parseAddress(null));
    }

    @Test
    public void parsAddress_invalidAddress_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parseAddress(""));
    }

    @Test
    void parseAddress_validAddress_returnsAddress() throws ParseException {
        Address expected = new Address(VALID_PROPERTY_ADDRESS_ANCHORVALE);
        assertEquals(expected, PropertyParserUtil.parseAddress(VALID_PROPERTY_ADDRESS_ANCHORVALE));
        assertEquals(expected,
                PropertyParserUtil.parseAddress("\t " + VALID_PROPERTY_ADDRESS_ANCHORVALE));
    }

    @Test
    public void parsePropertyType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parsePropertyType(null));
    }

    @Test
    public void parsePropertyType_invalidPropertyType_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePropertyType(INVALID_PROPERTY_PROPERTY_TYPE));
    }

    @Test
    public void parsePropertyType_validPropertyType_returnsPropertyType() throws ParseException {
        PropertyType expected = new PropertyType(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE);
        assertEquals(expected,
                PropertyParserUtil.parsePropertyType(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE));
        assertEquals(expected,
                PropertyParserUtil.parsePropertyType("\t " + VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE));
    }

    @Test
    public void parseIsRental_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parseIsRental(null));
    }

    @Test
    public void parseIsRental_invalidIsRental_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parseIsRental(INVALID_PROPERTY_IS_RENTAL));
    }

    @Test
    public void parseIsRental_validIsRental_returnsIsRental() throws ParseException {
        IsRental expected = new IsRental(VALID_PROPERTY_IS_RENTAL_ANCHORVALE);
        assertEquals(expected,
                PropertyParserUtil.parseIsRental(VALID_PROPERTY_IS_RENTAL_ANCHORVALE));
        assertEquals(expected,
                PropertyParserUtil.parseIsRental("\t " + VALID_PROPERTY_IS_RENTAL_ANCHORVALE));
    }

    @Test
    public void parseKeywords_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parseKeywords(null));
    }

    @Test
    public void parseKeywords_invalidKeywords_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parseKeywords(""));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parseKeywords("\t  "));
    }

    @Test
    public void parseKeywords_validKeywords_returnsKeywordsList() throws ParseException {
        List<String> expected = Arrays.asList("some", "words");
        assertEquals(expected, PropertyParserUtil.parseKeywords("  some\t words"));
    }

    @Test
    public void parsePropertyParserUtil_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parsePriceFilter(null));
    }

    @Test
    public void parsePriceFilter_invalidPriceFilter_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter(""));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("     "));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter(">> 2000"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("random price"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("500"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter(">="));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("< price"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("a"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("< -1"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("< 10000000000001"));
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parsePriceFilter("> 0"));
    }

    @Test
    public void parsePriceFilter_validPriceFilter_returnsPriceFilter() {
        List<String> filterStrings = Arrays.asList("< 100", "\t < 100", "<100",
                "<= 100", "\t <= 100", "<=100",
                "== 100", "\t == 100", "==100",
                "> 100", "\t > 100", ">100",
                ">= 100", "\t >= 100", ">=100");
        filterStrings.forEach(string -> {
            try {
                assertEquals(new PriceFilter(string), PropertyParserUtil.parsePriceFilter(string));
            } catch (ParseException e) {
                assert false;
            }
        });
    }

    @Test
    public void parseIsClosedDeal_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> PropertyParserUtil.parseIsClosedDeal(null));
    }

    @Test
    public void parseIsClosedDeal_invalidIsClosedDeal_throwsParseException() {
        assertThrows(ParseException.class,
                () -> PropertyParserUtil.parseIsClosedDeal(INVALID_PROPERTY_IS_CLOSED_DEAL));
    }

    @Test
    public void parseIsClosedDeal_validIsClosedDeal_returnsIsClosedDeal() throws ParseException {
        IsClosedDeal expected = new IsClosedDeal(VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE);
        assertEquals(expected,
                PropertyParserUtil.parseIsClosedDeal(VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE));
    }
}