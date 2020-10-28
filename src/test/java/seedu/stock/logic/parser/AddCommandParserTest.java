package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.DEFAULT_SERIAL_NUMBER;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LOW_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SOURCE_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.LOW_QUANTITY_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.LOW_QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOW_QUANTITY_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_BANANA;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Stock expectedStock = new StockBuilder().withName(VALID_NAME_BANANA).withSource(VALID_SOURCE_BANANA)
                .withLocation(VALID_LOCATION_BANANA).withQuantity(VALID_QUANTITY_BANANA)
                .withSerialNumber(DEFAULT_SERIAL_NUMBER).build();

        // whitespace only preamble, optional field header low quantity not included
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA, new AddCommand(expectedStock));

        // field header in different order
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA + NAME_DESC_BANANA, new AddCommand(expectedStock));

        // optional field header low quantity present
        Stock newExpectedStock = new StockBuilder().withName(VALID_NAME_BANANA).withSource(VALID_SOURCE_BANANA)
                .withLocation(VALID_LOCATION_BANANA).withQuantity(VALID_QUANTITY_BANANA, VALID_LOW_QUANTITY_BANANA)
                .withSerialNumber(DEFAULT_SERIAL_NUMBER).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA + NAME_DESC_BANANA + LOW_QUANTITY_DESC_BANANA,
                new AddCommand(expectedStock));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA
                + LOCATION_DESC_BANANA, expectedMessage);

        // missing source prefix
        assertParseFailure(parser, NAME_DESC_BANANA + VALID_SOURCE_BANANA + QUANTITY_DESC_BANANA
                + LOCATION_DESC_BANANA, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + VALID_QUANTITY_BANANA
                + LOCATION_DESC_BANANA, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA
                + VALID_LOCATION_BANANA, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BANANA + VALID_SOURCE_BANANA + VALID_QUANTITY_BANANA
                + VALID_LOCATION_BANANA, expectedMessage);
    }

    @Test
    public void parse_multipleCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_DUPLICATE_HEADER_FIELD, AddCommand.MESSAGE_USAGE);

        // multiple name
        assertParseFailure(parser, NAME_DESC_BANANA + NAME_DESC_APPLE + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA, expectedMessage);

        // multiple source
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + SOURCE_DESC_APPLE
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA, expectedMessage);

        // multiple quantity
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + QUANTITY_DESC_APPLE + LOCATION_DESC_BANANA, expectedMessage);

        // multiple location
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA
                + LOCATION_DESC_BANANA + LOCATION_DESC_APPLE, expectedMessage);

        // multiple lowQuantity
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA
                + LOCATION_DESC_BANANA + LOW_QUANTITY_DESC_APPLE + LOW_QUANTITY_DESC_BANANA, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA, Name.MESSAGE_CONSTRAINTS);

        // invalid source
        assertParseFailure(parser, NAME_DESC_BANANA + INVALID_SOURCE_DESC
                        + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA, Source.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + INVALID_QUANTITY_DESC + LOCATION_DESC_BANANA, Quantity.MESSAGE_CONSTRAINTS);

        // invalid lowQuantity
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA + INVALID_LOW_QUANTITY_DESC,
                Quantity.LOW_QUANTITY_MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BANANA + SOURCE_DESC_BANANA + QUANTITY_DESC_BANANA
                        + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SOURCE_DESC_BANANA
                        + QUANTITY_DESC_BANANA + INVALID_LOCATION_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BANANA + SOURCE_DESC_BANANA
                + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
