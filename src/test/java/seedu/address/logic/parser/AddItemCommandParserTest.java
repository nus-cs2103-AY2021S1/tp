package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESCRIPTION_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESCRIPTION_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_LOCATION_DESC_PEACH_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_LOCATION_DESC_SPINACH_GARDEN;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_QUANTITY_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_QUANTITY_DESC_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TAG_MULTIPARSE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_LOCATION_PEACH_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItemPrecursors.DEFAULT_DESCRIPTION_PRECURSOR;
import static seedu.address.testutil.TypicalItemPrecursors.DEFAULT_QUANTITY_PRECURSOR;
import static seedu.address.testutil.TypicalItemPrecursors.DEFAULT_TAGS_PRECURSOR;
import static seedu.address.testutil.TypicalItemPrecursors.LOCATED_BANANA_PRECURSOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddItemCommand;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemPrecursorBuilder;

public class AddItemCommandParserTest {
    private AddItemCommandParser parser = new AddItemCommandParser();

    /**
     * Tests for if all fields present.
     */
    @Test
    public void parse_allFieldsPresent_success() {
        ItemPrecursor expectedItem = new ItemPrecursorBuilder(LOCATED_BANANA_PRECURSOR).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_APPLE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedItem));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_APPLE
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedItem));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_APPLE
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedItem));
    }

    @Test
    public void parse_someFieldsMissing_success() {
        ItemPrecursor expectedDefaultDescription = new ItemPrecursorBuilder(DEFAULT_DESCRIPTION_PRECURSOR)
                .build();

        // No Item Description
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedDefaultDescription));

        ItemPrecursor expectedDefaultQuantity = new ItemPrecursorBuilder(DEFAULT_QUANTITY_PRECURSOR)
                .build();

        //No quantity given
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedDefaultQuantity));

        ItemPrecursor expectedEmptyTag = new ItemPrecursorBuilder(DEFAULT_TAGS_PRECURSOR)
                .build();

        //No quantity given
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + ITEM_NAME_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD
                        + ITEM_TAG_MULTIPARSE,
                new AddItemCommand(expectedDefaultQuantity));
    }

    /**
     * Tests for compulsory fields.
     */
    @Test
    public void parse_compulsoryPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_ITEM_NAME_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, INVALID_NAME_PREFIX + VALID_ITEM_NAME_BANANA
                        + ITEM_QUANTITY_DESC_BANANA
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + ITEM_LOCATION_DESC_PEACH_ORCHARD,
                expectedMessage);

        // invalid prefixes used
        assertParseFailure(parser, ITEM_QUANTITY_DESC_APPLE
                        + ITEM_DESCRIPTION_DESC_BANANA
                        + VALID_ITEM_LOCATION_PEACH_ORCHARD,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ITEM_NAME_BANANA
                        + VALID_ITEM_QUANTITY_BANANA
                        + VALID_ITEM_DESCRIPTION_BANANA
                        + VALID_ITEM_LOCATION_PEACH_ORCHARD,
                expectedMessage);
    }

    /**
     * Tests for invalid values.
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
