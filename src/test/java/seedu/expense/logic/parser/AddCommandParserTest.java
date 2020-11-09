package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.AMOUNT_DESC_MISC;
import static seedu.expense.logic.commands.CommandTestUtil.DATE_DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.DATE_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.DESCRIPTION_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MISC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.expense.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.expense.testutil.TypicalExpenses.BUS;
import static seedu.expense.testutil.TypicalExpenses.MISC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.AddCommand;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.Description;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;
import seedu.expense.testutil.ExpenseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expense expectedExpense = new ExpenseBuilder(BUS).withTag(VALID_TAG_TRANSPORT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple descriptions - last descriptions accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_FOOD + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_FOOD + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple dates - last date accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_FOOD + DATE_DESC_BUS
                + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));

        // multiple tags - last tag accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + DATE_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, new AddCommand(expectedExpense));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Expense expectedExpense = new ExpenseBuilder(MISC).withDate(LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build(); // date set to today.
        assertParseSuccess(parser, DESCRIPTION_DESC_MISC + AMOUNT_DESC_MISC,
                new AddCommand(expectedExpense));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_BUS + AMOUNT_DESC_BUS,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + VALID_AMOUNT_BUS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_BUS + VALID_AMOUNT_BUS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + AMOUNT_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Description.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + INVALID_AMOUNT_DESC
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS + INVALID_DATE_DESC
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS
                + INVALID_TAG_DESC + VALID_TAG_FOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + AMOUNT_DESC_BUS + INVALID_TAG_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_BUS + AMOUNT_DESC_BUS
                + TAG_DESC_FOOD + TAG_DESC_TRANSPORT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
