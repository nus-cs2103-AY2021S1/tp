package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.commands.AddCommand.MESSAGE_USAGE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESCRIPTION_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_QUANTITY_UNIT;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_QUANTITY_VALUE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_QUANTITY_ZERO_VALUE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.QUANTITY_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_WHOLEMEAL;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;
import static seedu.simplykitchen.logic.parser.ArgumentTokenizer.ILLEGAL_PREFIX;
import static seedu.simplykitchen.logic.parser.ArgumentTokenizer.MULTIPLE_SAME_PREFIX;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.simplykitchen.testutil.TypicalFood.APPLE_PIE;
import static seedu.simplykitchen.testutil.TypicalFood.BREAD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.testutil.FoodBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(BREAD).withTags(VALID_TAG_FROZEN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD + TAG_DESC_FROZEN, new AddCommand(expectedFood));

        // multiple tags - all accepted
        Food expectedFoodMultipleTags = new FoodBuilder(BREAD).withTags(VALID_TAG_FROZEN, VALID_TAG_WHOLEMEAL)
                .build();
        assertParseSuccess(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD + EXPIRY_DATE_DESC_BREAD
                + QUANTITY_DESC_BREAD + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN,
                new AddCommand(expectedFoodMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Food expectedFood = new FoodBuilder(APPLE_PIE).withTags().build();
        assertParseSuccess(parser, DESCRIPTION_DESC_APPLE_PIE + PRIORITY_DESC_APPLE_PIE
                + EXPIRY_DATE_DESC_APPLE_PIE + QUANTITY_DESC_APPLE_PIE, new AddCommand(expectedFood));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_BREAD + PRIORITY_DESC_BREAD
                        + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD, expectedMessage);

        // missing expiry date prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                        + VALID_EXPIRY_DATE_BREAD + QUANTITY_DESC_BREAD, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                        + EXPIRY_DATE_DESC_BREAD + VALID_QUANTITY_BREAD, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_BREAD + PRIORITY_DESC_BREAD
                        + VALID_EXPIRY_DATE_BREAD + VALID_QUANTITY_BREAD, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, Description.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + INVALID_PRIORITY_DESC
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, Priority.MESSAGE_CONSTRAINTS);

        // invalid expiry date format
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + INVALID_EXPIRY_DATE_DESC + QUANTITY_DESC_BREAD
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, ExpiryDate.MESSAGE_CONSTRAINTS);

        // invalid unit in quantity field
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + INVALID_QUANTITY_UNIT
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, Quantity.QUANTITY_UNIT_CONSTRAINTS);

        // invalid value in quantity field
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + INVALID_QUANTITY_VALUE
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, Quantity.QUANTITY_VALUE_CONSTRAINTS);

        // invalid value zero in quantity field
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + INVALID_QUANTITY_ZERO_VALUE
                + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN, Quantity.QUANTITY_VALUE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD
                + INVALID_TAG_DESC + VALID_TAG_FROZEN, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + PRIORITY_DESC_BREAD
                        + QUANTITY_DESC_BREAD + INVALID_EXPIRY_DATE_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD + TAG_DESC_WHOLEMEAL + TAG_DESC_FROZEN,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleSameFields_failure() {
        // multiple descriptions - last description accepted
        assertParseFailure(parser, DESCRIPTION_DESC_APPLE_PIE + DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD + TAG_DESC_FROZEN,
                String.format(MULTIPLE_SAME_PREFIX, "d/"));

        // multiple priorities - last priority accepted
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_APPLE_PIE + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD + TAG_DESC_FROZEN,
                String.format(MULTIPLE_SAME_PREFIX, "p/"));

        // multiple expiry dates - last expiry date accepted
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + PRIORITY_DESC_BREAD + EXPIRY_DATE_DESC_APPLE_PIE
                + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD + TAG_DESC_FROZEN,
                String.format(MULTIPLE_SAME_PREFIX, "e/"));
    }

    @Test
    public void parse_invalidPrefix_failure() {
        assertParseFailure(parser, DESCRIPTION_DESC_BREAD + EXPIRY_DATE_DESC_BREAD + QUANTITY_DESC_BREAD
                + PRIORITY_DESC_BREAD + TAG_DESC_WHOLEMEAL + " o/testing", String.format(ILLEGAL_PREFIX, "o/"));
    }
}
