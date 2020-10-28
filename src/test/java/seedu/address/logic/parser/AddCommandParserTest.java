package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ROSES;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SUNFLOWERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ROSES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SUNFLOWER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.RevenueBuilder;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();
    private ExpenseBuilder expenseBuilder = new ExpenseBuilder();
    private RevenueBuilder revenueBuilder = new RevenueBuilder();

    @Test
    public void parse_allFieldsPresent_success() {

        Expense expenseStub = expenseBuilder.withDescription(VALID_DESCRIPTION_EXPENSE)
                .withAmount(VALID_AMOUNT_EXPENSE).withTags(VALID_TAG_ROSES).build();

        Revenue revenueStub = revenueBuilder.withDescription(VALID_DESCRIPTION_REVENUE)
                .withAmount(VALID_AMOUNT_REVENUE).withTags(VALID_TAG_SUNFLOWER).build();

        Expense expenseStubSeveralTags = expenseBuilder.withDescription(VALID_DESCRIPTION_EXPENSE)
                .withAmount(VALID_AMOUNT_EXPENSE).withTags(VALID_TAG_ROSES, VALID_TAG_SUNFLOWER).build();

        Revenue revenueStubSeveralTags = revenueBuilder.withDescription(VALID_DESCRIPTION_REVENUE)
                .withAmount(VALID_AMOUNT_REVENUE).withTags(VALID_TAG_ROSES, VALID_TAG_SUNFLOWER).build();

        //for parsing expenses with one tag
        assertParseSuccess(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES, new AddCommand(expenseStub));

        //for parsing revenues with one tag
        assertParseSuccess(parser, CATEGORY_DESC_REVENUE + DESCRIPTION_DESC_REVENUE
                + AMOUNT_DESC_REVENUE + TAG_DESC_SUNFLOWERS, new AddCommand(revenueStub));

        //for parsing expenses with more than one tag
        assertParseSuccess(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES + TAG_DESC_SUNFLOWERS,
                new AddCommand(expenseStubSeveralTags));

        //for parsing revenues with more than one tag
        assertParseSuccess(parser, CATEGORY_DESC_REVENUE + DESCRIPTION_DESC_REVENUE
                + AMOUNT_DESC_REVENUE + TAG_DESC_ROSES + TAG_DESC_SUNFLOWERS,
                new AddCommand(revenueStubSeveralTags));
    }

    @Test
    public void parse_optionalTagsMissing_success() {
        Expense expenseStub = expenseBuilder.withDescription(VALID_DESCRIPTION_EXPENSE)
                .withAmount(VALID_AMOUNT_EXPENSE).build();

        Revenue revenueStub = revenueBuilder.withDescription(VALID_DESCRIPTION_REVENUE)
                .withAmount(VALID_AMOUNT_REVENUE).build();

        //for parsing expenses without tags
        assertParseSuccess(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE, new AddCommand(expenseStub));

        //for parsing revenues without tags
        assertParseSuccess(parser, CATEGORY_DESC_REVENUE + DESCRIPTION_DESC_REVENUE
                + AMOUNT_DESC_REVENUE, new AddCommand(revenueStub));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing category field
        assertParseFailure(parser, DESCRIPTION_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + TAG_DESC_ROSES, expectedMessage);

        //missing description field
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + TAG_DESC_ROSES, expectedMessage);

        //missing amount field
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + TAG_DESC_ROSES, expectedMessage);

        //all prefixes missing
        assertParseFailure(parser, VALID_AMOUNT_EXPENSE + VALID_DESCRIPTION_EXPENSE
                + VALID_AMOUNT_EXPENSE + VALID_TAG_ROSES , expectedMessage);
    }

    @Test
    public void parse_extraPrefixes() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PREFIXES, AddCommand.PREFIXES);

        // Extra prefixes
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + CATEGORY_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + DESCRIPTION_DESC_EXPENSE + TAG_DESC_ROSES, expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {

        //invalid category
        assertParseFailure(parser, INVALID_CATEGORY_DESC + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES, Category.MESSAGE_CONSTRAINTS);

        //invalid description
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + INVALID_DESCRIPTION_DESC
                + AMOUNT_DESC_EXPENSE + TAG_DESC_ROSES, Description.MESSAGE_CONSTRAINTS);

        //invalid amount
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + INVALID_AMOUNT_DESC + TAG_DESC_ROSES, Amount.MESSAGE_CONSTRAINTS);

        //invalid tag
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        //invalid tag
        assertParseFailure(parser, CATEGORY_DESC_EXPENSE + DESCRIPTION_DESC_EXPENSE
                + AMOUNT_DESC_EXPENSE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }



}
