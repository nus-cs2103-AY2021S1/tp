package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BUS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExpenses.BUS;
import static seedu.address.testutil.TypicalExpenses.FOOD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ExpenseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expense expectedExpense = new ExpenseBuilder(BUS).withTags(VALID_TAG_TRANSPORT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple names - last name accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_FOOD + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_FOOD + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple dates - last date accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_FOOD + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple tags - all accepted
        Expense expectedExpenseMultipleTags = new ExpenseBuilder(BUS).withTags(VALID_TAG_FOOD, VALID_TAG_TRANSPORT)
                .build();
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, new AddCommand(expectedExpenseMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Expense expectedExpense = new ExpenseBuilder(FOOD).withTags().withDate(LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build(); // date set to today.
        assertParseSuccess(parser, DESCRIPTION_DESC_FOOD + AMOUNT_DESC_FOOD,
                new AddCommand(expectedExpense));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DESCRIPTION_BUS + AMOUNT_DESC_BUS,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + VALID_AMOUNT_BUS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_BUS + VALID_AMOUNT_BUS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + AMOUNT_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Description.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + INVALID_AMOUNT_DESC
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Amount.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + INVALID_DATE_DESC
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS
                + INVALID_TAG_DESC + VALID_TAG_FOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + AMOUNT_DESC_BUS,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
