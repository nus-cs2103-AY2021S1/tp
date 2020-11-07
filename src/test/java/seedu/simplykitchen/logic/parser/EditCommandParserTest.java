package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESCRIPTION_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_QUANTITY_UNIT;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_QUANTITY_VALUE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.PRIORITY_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.QUANTITY_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.TAG_DESC_WHOLEMEAL;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLY_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;
import static seedu.simplykitchen.logic.parser.ArgumentTokenizer.ILLEGAL_PREFIX;
import static seedu.simplykitchen.logic.parser.ArgumentTokenizer.MULTIPLE_SAME_PREFIX;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_THIRD_FOOD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.simplykitchen.model.food.Description;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.Quantity;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION_APPLE_PIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_DESC_APPLE_PIE, MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_DESC_APPLE_PIE, MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", String.format(ILLEGAL_PREFIX, "i/"));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC,
                Priority.MESSAGE_CONSTRAINTS); // invalid priority
        assertParseFailure(parser, "1" + INVALID_EXPIRY_DATE_DESC,
                ExpiryDate.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_QUANTITY_UNIT,
                Quantity.QUANTITY_UNIT_CONSTRAINTS); // invalid quantity unit
        assertParseFailure(parser, "1" + INVALID_QUANTITY_VALUE,
                Quantity.QUANTITY_VALUE_CONSTRAINTS); // invalid quantity value
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid priority followed by valid expiry date
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC + EXPIRY_DATE_DESC_APPLE_PIE,
                Priority.MESSAGE_CONSTRAINTS);

        // valid priority followed by invalid priority. The test case for invalid priority followed by valid priority
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PRIORITY_DESC_BREAD + INVALID_PRIORITY_DESC,
                "Multiple p/ detected. Please remove one of them.");

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Food} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FROZEN + TAG_DESC_WHOLEMEAL + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FROZEN + TAG_EMPTY + TAG_DESC_WHOLEMEAL,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FROZEN + TAG_DESC_WHOLEMEAL,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + INVALID_EXPIRY_DATE_DESC
                + VALID_PRIORITY_APPLE_PIE + VALID_QUANTITY_APPLY_PIE, Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_BREAD + TAG_DESC_WHOLEMEAL
                + EXPIRY_DATE_DESC_BREAD + DESCRIPTION_DESC_APPLE_PIE + TAG_DESC_FROZEN + QUANTITY_DESC_BREAD;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
                .withPriority(VALID_PRIORITY_BREAD).withExpiryDate(VALID_EXPIRY_DATE_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD)
                .withTags(VALID_TAG_WHOLEMEAL, VALID_TAG_FROZEN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_BREAD + EXPIRY_DATE_DESC_APPLE_PIE;

        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_BREAD)
                .withExpiryDate(VALID_EXPIRY_DATE_APPLE_PIE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_APPLE_PIE;
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_APPLE_PIE).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_APPLE_PIE;
        descriptor = new EditFoodDescriptorBuilder().withPriority(VALID_PRIORITY_APPLE_PIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_DESC_APPLE_PIE;
        descriptor = new EditFoodDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_APPLE_PIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_APPLE_PIE;
        descriptor = new EditFoodDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLY_PIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FROZEN;
        descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_FROZEN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_APPLE_PIE
                + EXPIRY_DATE_DESC_APPLE_PIE + TAG_DESC_FROZEN + PRIORITY_DESC_APPLE_PIE
                + EXPIRY_DATE_DESC_APPLE_PIE + TAG_DESC_FROZEN + PRIORITY_DESC_BREAD
                + EXPIRY_DATE_DESC_BREAD + TAG_DESC_WHOLEMEAL + QUANTITY_DESC_APPLE_PIE
                + QUANTITY_DESC_BREAD;

        assertParseFailure(parser, userInput, String.format(MULTIPLE_SAME_PREFIX, "p/"));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_failure() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + INVALID_PRIORITY_DESC + PRIORITY_DESC_BREAD;
        assertParseFailure(parser, userInput, String.format(MULTIPLE_SAME_PREFIX, "p/"));

        // other valid values specified
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_DESC_BREAD + INVALID_PRIORITY_DESC + PRIORITY_DESC_BREAD;

        assertParseFailure(parser, userInput, String.format(MULTIPLE_SAME_PREFIX, "p/"));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
