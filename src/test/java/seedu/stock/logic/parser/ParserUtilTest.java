package seedu.stock.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.INDEX_FIRST_NOTE;
import static seedu.stock.testutil.TypicalStocks.QUANTITY_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.QUANTITY_THIRD_STOCK;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;

public class ParserUtilTest {

    private static final String INVALID_LOW_QUANTITY = "invalid";
    private static final String INVALID_LOCATION = "";
    private static final String INVALID_MISSING_SERIAL_NUMBER_HEADER = "invalid";
    private static final String INVALID_NAME = "N@me";
    private static final String INVALID_NOTE = "";
    private static final String INVALID_NOTE_INDEX = "-99";
    private static final String INVALID_QUANTITY = "invalid";
    private static final String INVALID_QUANTITY_ADDER = "invalid";
    private static final String INVALID_SOURCE = "";
    private static final String INVALID_SERIAL_NUMBER = "invalid";
    private static final String INVALID_SERIAL_NUMBERS_INPUT = "sn/ntuc sn/invalid";


    private static final String VALID_NAME = "Pineapple";
    private static final String VALID_QUANTITY = "100";
    private static final String VALID_LOCATION = "Fruits section";
    private static final String VALID_SOURCE = "ntuc";
    private static final String VALID_SERIAL_NUMBER = "ntuc1";
    private static final String VALID_NOTE = "Note for the pineapple.";
    private static final String VALID_QUANTITY_ADDER = "20";
    private static final String VALID_QUANTITY_ADDER_WITH_WHITESPACES = "      20          ";
    private static final String VALID_SERIAL_NUMBERS_INPUT = " sn/ntuc1 sn/ntuc2 sn/ntuc3";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseNoteIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNoteIndex(INVALID_NOTE_INDEX));
    }

    @Test
    public void parseNoteIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseNoteIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseNoteIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_NOTE, ParserUtil.parseNoteIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_NOTE, ParserUtil.parseNoteIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseQuantity_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseQuantity(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseQuantity_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(QUANTITY_FIRST_STOCK, ParserUtil.parseQuantity("2000"));

        // Leading and trailing whitespaces
        assertEquals(QUANTITY_FIRST_STOCK, ParserUtil.parseQuantity("  2000  "));
    }

    @Test
    public void parseLowQuantity_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLowQuantity(
                new Quantity(VALID_QUANTITY), Optional.of(INVALID_LOW_QUANTITY)));
    }

    @Test
    public void parseLowQuantity_isEmpty_throwsParseException() throws Exception {
        assertEquals(QUANTITY_FIRST_STOCK, ParserUtil.parseLowQuantity(
                new Quantity("2000"), Optional.empty()));
    }

    @Test
    public void parseLowQuantity_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseLowQuantity(new Quantity(VALID_QUANTITY),
                        Optional.of(Long.toString(Integer.MAX_VALUE + 1))));
    }

    @Test
    public void parseLowQuantity_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(QUANTITY_THIRD_STOCK, ParserUtil.parseLowQuantity(
                new Quantity("2103"), Optional.of("2200")));

        // Leading and trailing whitespaces
        assertEquals(QUANTITY_THIRD_STOCK, ParserUtil.parseLowQuantity(
                new Quantity("2103"), Optional.of("  2200  ")));
    }

    @Test
    public void parseLocation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }

    @Test
    public void parseLocation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsLocation() throws Exception {
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedLocation() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(locationWithWhitespace));
    }

    @Test
    public void parseSource_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSource((String) null));
    }

    @Test
    public void parseSource_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSource(INVALID_SOURCE));
    }

    @Test
    public void parseSource_validValueWithoutWhitespace_returnsSource() throws Exception {
        Source expectedSource = new Source(VALID_SOURCE);
        assertEquals(expectedSource, ParserUtil.parseSource(VALID_SOURCE));
    }

    @Test
    public void parseSource_validValueWithWhitespace_returnsTrimmedSource() throws Exception {
        String sourceWithWhitespace = WHITESPACE + VALID_SOURCE + WHITESPACE;
        Source expectedSource = new Source(VALID_SOURCE);
        assertEquals(expectedSource, ParserUtil.parseSource(sourceWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    @Test
    public void parseSerialNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSerialNumber((String) null));
    }

    @Test
    public void parseSerialNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSerialNumber(INVALID_SERIAL_NUMBER));
    }

    @Test
    public void parseSerialNumber_validValueWithoutWhitespace_returnsSerialNumber() throws Exception {
        SerialNumber expectedSerialNumber = new SerialNumber(VALID_SERIAL_NUMBER);
        assertEquals(expectedSerialNumber, ParserUtil.parseSerialNumber(VALID_SERIAL_NUMBER));
    }

    @Test
    public void parseSerialNumber_validValueWithWhitespace_returnsTrimmedSerialNumber() throws Exception {
        String serialNumberWithWhitespace = WHITESPACE + VALID_SERIAL_NUMBER + WHITESPACE;
        SerialNumber expectedSerialNumber = new SerialNumber(VALID_SERIAL_NUMBER);
        assertEquals(expectedSerialNumber, ParserUtil.parseSerialNumber(serialNumberWithWhitespace));
    }

    @Test
    public void parseQuantityAdder_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantityAdder(INVALID_QUANTITY_ADDER));
    }

    @Test
    public void parseQuantityAdder_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(new QuantityAdder(VALID_QUANTITY_ADDER),
                ParserUtil.parseQuantityAdder(VALID_QUANTITY_ADDER));

        // Leading and trailing whitespaces
        assertEquals(new QuantityAdder(VALID_QUANTITY_ADDER),
                ParserUtil.parseQuantityAdder(VALID_QUANTITY_ADDER_WITH_WHITESPACES));
    }

    @Test
    public void parseSerialNumbersInput_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSerialNumbers((String) null));
    }

    @Test
    public void parseSerialNumbersInput_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSerialNumbers(INVALID_SERIAL_NUMBERS_INPUT));
    }

    @Test
    public void parseSerialNumbersInput_missingPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSerialNumbers(INVALID_MISSING_SERIAL_NUMBER_HEADER));
    }

    @Test
    public void parseSerialNumbersInput_validValueWithoutWhitespace_returnsSerialNumberSet() throws Exception {
        Set<SerialNumber> expectedSerialNumberSet = new LinkedHashSet<>();
        expectedSerialNumberSet.add(new SerialNumber("ntuc1"));
        expectedSerialNumberSet.add(new SerialNumber("ntuc2"));
        expectedSerialNumberSet.add(new SerialNumber("ntuc3"));

        assertTrue(expectedSerialNumberSet.equals(ParserUtil.parseSerialNumbers(VALID_SERIAL_NUMBERS_INPUT)));
    }

    @Test
    public void parseSerialNumbersInput_validValueWithWhitespace_returnsTrimmedSerialNumber() throws Exception {

        String serialNumbersWithWhitespace = WHITESPACE + VALID_SERIAL_NUMBERS_INPUT + WHITESPACE;
        Set<SerialNumber> expectedSerialNumberSet = new LinkedHashSet<>();
        expectedSerialNumberSet.add(new SerialNumber("ntuc1"));
        expectedSerialNumberSet.add(new SerialNumber("ntuc2"));
        expectedSerialNumberSet.add(new SerialNumber("ntuc3"));

        assertTrue(expectedSerialNumberSet.equals(ParserUtil.parseSerialNumbers(serialNumbersWithWhitespace)));
    }

    @Test
    public void isInvalidPrefixPresent() {

        String input = "sn/ntuc1";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(input, CliSyntax.getAllPossiblePrefixesAsArray());
        Prefix[] validPrefixes = { CliSyntax.PREFIX_SERIAL_NUMBER };

        assertFalse(ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixes));
    }

    @Test
    public void isDuplicatePrefixPresent() {

        String input = " sn/ntuc1 n/ntuc";
        Prefix[] validPrefixes = { CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_SERIAL_NUMBER };
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(input, CliSyntax.getAllPossiblePrefixesAsArray());
        assertFalse(ParserUtil.isDuplicatePrefixPresent(argMultimap, validPrefixes));

        String inputWithDuplicates = " n/two n/three";
        ArgumentMultimap argMultimapWithDuplicates =
                ArgumentTokenizer.tokenize(inputWithDuplicates, CliSyntax.getAllPossiblePrefixesAsArray());
        assertTrue(ParserUtil.isDuplicatePrefixPresent(argMultimapWithDuplicates, validPrefixes));

    }

    @Test
    public void areAllPrefixesPresent() {

        String input = "sn/ntuc1";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(input, CliSyntax.getAllPossiblePrefixesAsArray());
        Prefix[] validPrefixes = { CliSyntax.PREFIX_SERIAL_NUMBER, CliSyntax.PREFIX_NAME };

        assertFalse(ParserUtil.areAllPrefixesPresent(argMultimap, validPrefixes));
    }

    @Test
    public void isAnyPrefixesPresent() {

        String input = "sn/ntuc1";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(input, CliSyntax.getAllPossiblePrefixesAsArray());
        Prefix[] validPrefixes = { CliSyntax.PREFIX_NAME };

        assertFalse(ParserUtil.isAnyPrefixPresent(argMultimap, validPrefixes));
    }

}
