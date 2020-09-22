package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUPPLIER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.SUPPLIER_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.SUPPLIER_DESC_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_POULTRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POULTRY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.CHICKEN_MANUAL;
import static seedu.address.testutil.TypicalItems.DUCK_MANUAL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.item.Quantity;
import seedu.address.model.item.Supplier;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItemBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(DUCK_MANUAL).withTags(VALID_TAG_POULTRY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DUCK + QUANTITY_DESC_DUCK
                + SUPPLIER_DESC_DUCK + TAG_DESC_POULTRY, new AddCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CHICKEN + NAME_DESC_DUCK + QUANTITY_DESC_DUCK
                + SUPPLIER_DESC_DUCK + TAG_DESC_POULTRY, new AddCommand(expectedItem));

        // multiple quantity - last quantity accepted
        assertParseSuccess(parser, NAME_DESC_DUCK + QUANTITY_DESC_CHICKEN + QUANTITY_DESC_DUCK
                + SUPPLIER_DESC_DUCK + TAG_DESC_POULTRY, new AddCommand(expectedItem));

        // multiple supplier - last supplier accepted
        assertParseSuccess(parser, NAME_DESC_DUCK + QUANTITY_DESC_DUCK + SUPPLIER_DESC_CHICKEN
                + SUPPLIER_DESC_DUCK + TAG_DESC_POULTRY, new AddCommand(expectedItem));

        // multiple tags - all accepted
        Item expectedItemMultipleTags = new ItemBuilder(DUCK_MANUAL).withTags(VALID_TAG_POULTRY, VALID_TAG_MEAT)
                .build();
        assertParseSuccess(parser, NAME_DESC_DUCK + QUANTITY_DESC_DUCK + SUPPLIER_DESC_DUCK
                + TAG_DESC_MEAT + TAG_DESC_POULTRY, new AddCommand(expectedItemMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Item expectedItem = new ItemBuilder(CHICKEN_MANUAL).withTags().build();
        assertParseSuccess(parser, NAME_DESC_CHICKEN + QUANTITY_DESC_CHICKEN + SUPPLIER_DESC_CHICKEN,
                new AddCommand(expectedItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_DUCK + QUANTITY_DESC_DUCK,
                expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, NAME_DESC_DUCK + VALID_QUANTITY_DUCK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_DUCK + VALID_QUANTITY_DUCK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + QUANTITY_DESC_DUCK + SUPPLIER_DESC_DUCK
                + TAG_DESC_MEAT + TAG_DESC_POULTRY, Name.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, NAME_DESC_DUCK + INVALID_QUANTITY_DESC + SUPPLIER_DESC_DUCK
                + TAG_DESC_MEAT + TAG_DESC_POULTRY, Quantity.MESSAGE_CONSTRAINTS);

        // invalid supplier
        assertParseFailure(parser, NAME_DESC_DUCK + QUANTITY_DESC_DUCK + INVALID_SUPPLIER_DESC
                + TAG_DESC_MEAT + TAG_DESC_POULTRY, Supplier.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_DUCK + QUANTITY_DESC_DUCK + SUPPLIER_DESC_DUCK
                + INVALID_TAG_DESC + VALID_TAG_POULTRY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + QUANTITY_DESC_DUCK + INVALID_SUPPLIER_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_DUCK + QUANTITY_DESC_DUCK
                + SUPPLIER_DESC_DUCK + TAG_DESC_MEAT + TAG_DESC_POULTRY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
