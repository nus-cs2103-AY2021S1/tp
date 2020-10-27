package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.DATE_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DESCRIPTION_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.expense.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.expense.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;
import static seedu.expense.testutil.TypicalIndexes.INDEX_THIRD_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.core.index.Index;
import seedu.expense.logic.commands.EditCommand;
import seedu.expense.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.Description;
import seedu.expense.model.tag.Tag;
import seedu.expense.testutil.EditExpenseDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION_FOOD, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_DESC_FOOD, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_DESC_FOOD, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid amount followed by valid date
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + DATE_DESC_FOOD, Amount.MESSAGE_CONSTRAINTS);

        // valid amount followed by invalid amount. The test case for invalid amount followed by valid amount
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + AMOUNT_DESC_BUS + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_DESCRIPTION_DESC + INVALID_DATE_DESC + VALID_AMOUNT_FOOD,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS + TAG_DESC_FOOD
                + DATE_DESC_FOOD + DESCRIPTION_DESC_FOOD + TAG_DESC_TRANSPORT;

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_FOOD)
                .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_FOOD)
                .withTag(VALID_TAG_TRANSPORT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUS + DATE_DESC_FOOD;

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BUS)
                .withDate(VALID_DATE_FOOD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_EXPENSE;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_FOOD;
        EditExpenseDescriptor descriptor =
                new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_FOOD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_FOOD;
        descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_FOOD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_FOOD;
        descriptor = new EditExpenseDescriptorBuilder().withDate(VALID_DATE_FOOD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TRANSPORT;
        descriptor = new EditExpenseDescriptorBuilder().withTag(VALID_TAG_TRANSPORT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_FOOD + DATE_DESC_FOOD
                + TAG_DESC_TRANSPORT + AMOUNT_DESC_FOOD + DATE_DESC_FOOD + TAG_DESC_TRANSPORT
                + AMOUNT_DESC_BUS + DATE_DESC_BUS + TAG_DESC_FOOD;

        EditCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BUS)
                .withDate(VALID_DATE_BUS).withTag(VALID_TAG_FOOD)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + INVALID_AMOUNT_DESC + AMOUNT_DESC_BUS;
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BUS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DATE_DESC_BUS + INVALID_AMOUNT_DESC
                + AMOUNT_DESC_BUS;
        descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() throws Exception {
        Index targetIndex = INDEX_THIRD_EXPENSE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withTag("").build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
