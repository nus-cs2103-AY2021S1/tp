package seedu.stock.logic.parser;


import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_INCREMENT_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NEW_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NEW_QUANTITY_DESC2;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SOURCE_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_APPLE;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.Source;

public class UpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no serial number specified
        assertParseFailure(parser, NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // no keyword specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // random user input without keywords
        assertParseFailure(parser, "thisIsRandom", MESSAGE_INVALID_FORMAT);

        // invalid keyword without any valid keywords
        assertParseFailure(parser, "x/invalid", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid quantity prefix
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA + INVALID_QUANTITY_DESC, MESSAGE_INVALID_FORMAT);

        // invalid new quantity
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NEW_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NEW_QUANTITY_DESC2, Quantity.MESSAGE_CONSTRAINTS);

        // invalid increment quantity
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_INCREMENT_QUANTITY_DESC,
                QuantityAdder.MESSAGE_CONSTRAINTS);

        // invalid source
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_SOURCE_DESC, Source.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // invalid field followed by a valid field
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_LOCATION_DESC + SOURCE_DESC_APPLE,
                Location.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA + INVALID_NEW_QUANTITY_DESC + NAME_DESC_BANANA,
                Quantity.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + NAME_DESC_APPLE + INVALID_INCREMENT_QUANTITY_DESC
                + VALID_SOURCE_APPLE, QuantityAdder.MESSAGE_CONSTRAINTS);

        // multiple invalid fields
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE + INVALID_NAME_DESC + INVALID_NEW_QUANTITY_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }
}
