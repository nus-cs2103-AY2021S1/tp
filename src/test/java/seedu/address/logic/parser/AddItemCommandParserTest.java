package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESCRIPTION_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESCRIPTION_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_LOCATION_DESC_PEACH_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_LOCATION_DESC_SPINACH_GARDEN;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_QUANTITY_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_QUANTITY_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_LOCATION_PEACH_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.BANANA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddItemCommand;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

public class AddItemCommandParserTest {
    private AddItemCommandParser parser = new AddItemCommandParser();

    /**
     * tests for if all fields present
     */
    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(BANANA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                new AddItemCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_APPLE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                new AddItemCommand(expectedItem));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_APPLE
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                new AddItemCommand(expectedItem));

        // multiple emails - last email accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_APPLE
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                new AddItemCommand(expectedItem));
    }

    /**
     * tests for compulsory fields
     */
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_ITEM_NAME_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, ITEM_NAME_DESC_BANANA
                        + VALID_ITEM_QUANTITY_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + VALID_ITEM_DESCRIPTION_BANANA
                        + ITEM_LOCATION_DESC_SPINACH_GARDEN,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ITEM_NAME_BANANA
                        + VALID_ITEM_QUANTITY_BANANA
                        + VALID_ITEM_DESCRIPTION_BANANA
                        + VALID_ITEM_LOCATION_PEACH_ORCHARD,
                expectedMessage);
    }

    /**
     * tests for invalid values
     */
    @Test
    public void parse_invalidValue_failure() {
        // invalid quantity
        assertParseFailure(parser, ITEM_NAME_DESC_BANANA
                        + INVALID_QUANTITY_DESC
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_SPINACH_GARDEN,
                Quantity.MESSAGE_CONSTRAINTS);
    }
}
