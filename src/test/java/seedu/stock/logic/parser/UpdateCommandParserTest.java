package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.INCREMENT_QUANTITY_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_INCREMENT_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NEW_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NEW_QUANTITY_DESC2;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SERIAL_NUMBER_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SERIAL_NUMBER_DESC2;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.LOW_QUANTITY_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.NEW_QUANTITY_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOCATION_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOW_QUANTITY_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_APPLE;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.testutil.UpdateStockDescriptorBuilder;

/**
 * Contains unit tests for UpdateCommandParser.
 */
public class UpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // EP: no serial number specified
        assertParseFailure(parser, NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // EP: no prefix specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // EP: random user input without prefixes
        assertParseFailure(parser, "thisIsRandom", MESSAGE_INVALID_FORMAT);

        // EP: invalid prefix and prefix unknown to Warenager.
        assertParseFailure(parser, "x/invalid", MESSAGE_INVALID_FORMAT);

        // EP: invalid prefix and prefix is known to Warenager.
        assertParseFailure(parser, QUANTITY_DESC_BANANA, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // EP: invalid name
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // EP: invalid new quantity
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NEW_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NEW_QUANTITY_DESC2, Quantity.MESSAGE_CONSTRAINTS);

        // EP: invalid increment quantity
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_INCREMENT_QUANTITY_DESC,
                QuantityAdder.MESSAGE_CONSTRAINTS);

        // EP: invalid location
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // EP: invalid serial number
        assertParseFailure(parser, INVALID_SERIAL_NUMBER_DESC, SerialNumber.MESSAGE_CONSTRAINTS);

        // EP: invalid serial numbers
        assertParseFailure(parser, INVALID_SERIAL_NUMBER_DESC + INVALID_SERIAL_NUMBER_DESC2,
                SerialNumber.MESSAGE_CONSTRAINTS);

        // EP: serial numbers some valid, but some invalid
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_SERIAL_NUMBER_DESC2,
                SerialNumber.MESSAGE_CONSTRAINTS);

        // EP: invalid field followed by a valid field
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_LOCATION_DESC + NAME_DESC_BANANA,
                Location.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA + INVALID_NEW_QUANTITY_DESC + NAME_DESC_BANANA,
                Quantity.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + INVALID_INCREMENT_QUANTITY_DESC
                + VALID_SOURCE_APPLE, QuantityAdder.MESSAGE_CONSTRAINTS);

        // EP: multiple invalid fields
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NAME_DESC + INVALID_NEW_QUANTITY_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // EP: new quantity provided
        String userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + NEW_QUANTITY_DESC_APPLE
                + LOCATION_DESC_APPLE + LOW_QUANTITY_DESC_APPLE;

        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).withLowQuantity(VALID_LOW_QUANTITY_APPLE)
                .withQuantity(VALID_QUANTITY_APPLE.toLowerCase()).build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: increment quantity provided
        userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + INCREMENT_QUANTITY_DESC_APPLE
                + LOCATION_DESC_APPLE + LOW_QUANTITY_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).withLowQuantity(VALID_LOW_QUANTITY_APPLE)
                .withQuantityAdder(VALID_QUANTITY_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // EP: one optional prefix provided
        String userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE;

        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: two optional prefixes provided
        userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + LOCATION_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: three optional prefixes provided
        userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + NEW_QUANTITY_DESC_APPLE + LOCATION_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase())
                .withQuantity(VALID_QUANTITY_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // EP: name
        String userInput = SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE;
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: location
        userInput = SERIAL_NUMBER_DESC_APPLE + LOCATION_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withLocation(VALID_LOCATION_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: new quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + NEW_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: increment quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + INCREMENT_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withQuantityAdder(VALID_QUANTITY_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: low quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + LOW_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withLowQuantity(VALID_LOW_QUANTITY_APPLE)
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleSerialNumbersAllFieldsSpecified_success() {
        // EP: new quantity provided
        String userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + NAME_DESC_APPLE + NEW_QUANTITY_DESC_APPLE + LOCATION_DESC_APPLE + LOW_QUANTITY_DESC_APPLE;

        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).withQuantity(VALID_QUANTITY_APPLE.toLowerCase())
                .withLowQuantity("0").build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: increment quantity provided
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + NAME_DESC_APPLE + INCREMENT_QUANTITY_DESC_APPLE + LOCATION_DESC_APPLE + LOW_QUANTITY_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).withQuantityAdder(VALID_QUANTITY_APPLE.toLowerCase())
                .withLowQuantity("0").build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleSerialNumbersSomeFieldsSpecified_success() {
        // EP: one optional prefix provided
        String userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + NAME_DESC_APPLE;

        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: two optional prefixes provided
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + NAME_DESC_APPLE + LOCATION_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: three optional prefixes provided
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA
                + NAME_DESC_APPLE + NEW_QUANTITY_DESC_APPLE + LOCATION_DESC_APPLE;

        descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .withLocation(VALID_LOCATION_APPLE.toLowerCase()).withQuantity(VALID_QUANTITY_APPLE.toLowerCase())
                .build();
        expectedCommand = new UpdateCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleSerialNumbersOneFieldSpecified_success() {
        // EP: name
        String userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA + NAME_DESC_APPLE;
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder().withName(VALID_NAME_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: location
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA + LOCATION_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withLocation(VALID_LOCATION_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: new quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA + NEW_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: increment quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA + INCREMENT_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withQuantityAdder(VALID_QUANTITY_APPLE.toLowerCase())
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: low quantity
        userInput = SERIAL_NUMBER_DESC_APPLE + SERIAL_NUMBER_DESC_BANANA + LOW_QUANTITY_DESC_APPLE;
        descriptor = new UpdateStockDescriptorBuilder().withLowQuantity(VALID_LOW_QUANTITY_APPLE)
                .withSerialNumber(VALID_SERIAL_NUMBER_APPLE.toLowerCase(), VALID_SERIAL_NUMBER_BANANA.toLowerCase())
                .build();
        expectedCommand = new UpdateCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
