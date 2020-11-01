package seedu.cc.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.commands.CommandTestUtil.AMOUNT_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.AMOUNT_DESC_REVENUE;
import static seedu.cc.logic.commands.CommandTestUtil.CATEGORY_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.CATEGORY_DESC_REVENUE;
import static seedu.cc.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REVENUE;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.TAG_DESC_ROSES;
import static seedu.cc.logic.commands.CommandTestUtil.TAG_DESC_SUNFLOWERS;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_CATEGORY_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ROSES;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_TAG;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.cc.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.Messages;
import seedu.cc.commons.core.category.Category;
import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.entrylevel.EditCommand;
import seedu.cc.logic.commands.entrylevel.EditCommand.EditEntryDescriptor;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.model.tag.Tag;
import seedu.cc.testutil.EditEntryDescriptorBuilder;
import seedu.cc.testutil.EntryUtil;
import seedu.cc.testutil.TypicalEntries;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CATEGORY_EXPENSE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CATEGORY_DESC_EXPENSE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CATEGORY_DESC_REVENUE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_REVENUE + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_EXPENSE + INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_REVENUE + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        //valid category, followed by invalid description, followed by valid amount
        assertParseFailure(parser, "1" + CATEGORY_DESC_EXPENSE + INVALID_DESCRIPTION_DESC
            + AMOUNT_DESC_EXPENSE, Description.MESSAGE_CONSTRAINTS);

        //valid category, followed by valid description, followed by invalid description
        assertParseFailure(parser, "1" + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + INVALID_DESCRIPTION_DESC, String.format(Messages.MESSAGE_MULTIPLE_PREFIXES, EditCommand.PREFIXES));

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Entry} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + CATEGORY_DESC_EXPENSE + TAG_DESC_ROSES
                + TAG_DESC_SUNFLOWERS + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_REVENUE + TAG_DESC_ROSES + TAG_EMPTY
                        + TAG_DESC_SUNFLOWERS, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_EXPENSE + TAG_EMPTY + TAG_DESC_ROSES
                        + TAG_DESC_SUNFLOWERS, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + CATEGORY_DESC_REVENUE + INVALID_DESCRIPTION_DESC
                        + AMOUNT_DESC_REVENUE + INVALID_TAG_DESC,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + TAG_DESC_ROSES;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_EXPENSE).withAmount(VALID_AMOUNT_EXPENSE)
                .withTags(VALID_TAG_ROSES).build();

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory(VALID_CATEGORY_EXPENSE)
                .withDescription(VALID_DESCRIPTION_EXPENSE).withAmount(VALID_AMOUNT_EXPENSE)
                .withTags(VALID_TAG_ROSES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someOptionalFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory("expense")
                .withDescription(VALID_DESCRIPTION_EXPENSE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES + CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES + CATEGORY_DESC_REVENUE + DESCRIPTION_DESC_REVENUE
                + AMOUNT_DESC_REVENUE + TAG_DESC_ROSES;

        assertParseFailure(parser, userInput, String.format(Messages.MESSAGE_MULTIPLE_PREFIXES, EditCommand.PREFIXES));
    }

    @Test
    public void parse_invalidOptionalValueFollowedByValidValue_failure() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + INVALID_DESCRIPTION_DESC
                + DESCRIPTION_DESC_EXPENSE;
        assertParseFailure(parser, userInput, String.format(Messages.MESSAGE_MULTIPLE_PREFIXES, EditCommand.PREFIXES));

        // other valid values specified
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + INVALID_DESCRIPTION_DESC
                + AMOUNT_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE;
        assertParseFailure(parser, userInput, String.format(Messages.MESSAGE_MULTIPLE_PREFIXES, EditCommand.PREFIXES));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased() + CATEGORY_DESC_EXPENSE + TAG_EMPTY;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory(VALID_CATEGORY_EXPENSE)
                .withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parseEditCommand_editExpense() throws Exception {
        Expense expense = TypicalEntries.PAY_RENT;
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(expense).build();
        EditCommand actualCommand = parser.parse(INDEX_FIRST_ENTRY.getOneBased()
                + " " + EntryUtil.getEntryDetails(TypicalEntries.PAY_RENT));
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseEditCommand_editRevenue() throws Exception {
        Revenue revenue = TypicalEntries.SELL_FLOWER_SEEDS;
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(revenue).build();
        EditCommand actualCommand = parser.parse(INDEX_FIRST_ENTRY.getOneBased()
                + " " + EntryUtil.getEntryDetails(TypicalEntries.SELL_FLOWER_SEEDS));
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        assertEquals(expectedCommand, actualCommand);
    }

}
