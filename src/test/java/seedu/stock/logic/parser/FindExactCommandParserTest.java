package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA_WITH_WHITESPACES_BETWEEN;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOCATION_APPLE_KEYWORDS;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_BANANA_KEYWORDS;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BANANA_KEYWORDS;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_APPLE_KEYWORDS;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.FindExactCommand;
import seedu.stock.model.stock.predicates.LocationContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SerialNumberContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SourceContainsKeywordsPredicate;


public class FindExactCommandParserTest {

    private FindExactCommandParser parser = new FindExactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindExactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindExactCommand() {

        // no leading and trailing whitespaces
        FindExactCommand expectedFindExactCommand =
                new FindExactCommand(Collections.singletonList(
                        new SourceContainsKeywordsPredicate(Arrays.asList(VALID_SOURCE_APPLE_KEYWORDS))));
        assertParseSuccess(parser, SOURCE_DESC_APPLE, expectedFindExactCommand);

        FindExactCommand expectedSecondFindExactCommand =
                new FindExactCommand(Collections.singletonList(
                        new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BANANA_KEYWORDS))));
        // multiple whitespaces between keywords
        assertParseSuccess(parser, NAME_DESC_BANANA_WITH_WHITESPACES_BETWEEN, expectedSecondFindExactCommand);

    }

    @Test
    public void parse_allFieldsPresent_success() {
        NameContainsKeywordsPredicate expectedNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BANANA_KEYWORDS));
        LocationContainsKeywordsPredicate expectedLocationPredicate =
                new LocationContainsKeywordsPredicate(Arrays.asList(VALID_LOCATION_APPLE_KEYWORDS));
        SerialNumberContainsKeywordsPredicate expectedSnPredicate =
                new SerialNumberContainsKeywordsPredicate(Arrays.asList(VALID_SERIAL_NUMBER_BANANA_KEYWORDS));

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_BANANA,
                new FindExactCommand(Collections.singletonList(expectedNamePredicate)));

        // field headers in different order
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + LOCATION_DESC_APPLE + NAME_DESC_BANANA + SERIAL_NUMBER_DESC_BANANA,
                new FindExactCommand(Arrays.asList(expectedNamePredicate,
                        expectedLocationPredicate, expectedSnPredicate)));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindExactCommand.MESSAGE_USAGE);

        // missing any prefix
        assertParseFailure(parser, VALID_NAME_BANANA, expectedMessage);
        assertParseFailure(parser, VALID_SERIAL_NUMBER_APPLE, expectedMessage);

    }

    @Test
    public void parse_duplicateCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_DUPLICATE_HEADER_FIELD, FindExactCommand.MESSAGE_USAGE);

        // multiple name
        assertParseFailure(parser, NAME_DESC_BANANA + NAME_DESC_APPLE + SOURCE_DESC_BANANA
                + LOCATION_DESC_BANANA, expectedMessage);

        // multiple source
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + SOURCE_DESC_APPLE
                + LOCATION_DESC_BANANA, expectedMessage);

        // multiple location
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + LOCATION_DESC_BANANA + LOCATION_DESC_APPLE, expectedMessage);

        // multiple serialNumber
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + LOCATION_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + SERIAL_NUMBER_DESC_APPLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BANANA + SOURCE_DESC_BANANA
                        + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindExactCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidFieldsPresent_failure() {
        // quantity field present
        assertParseFailure(parser,
                NAME_DESC_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindExactCommand.MESSAGE_USAGE));

    }

}
