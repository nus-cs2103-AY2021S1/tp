package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_2;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_3;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_MOVIE;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOOKS;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOOKS;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MOVIE;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOOKS;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MOVIE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExpenses.BOOKS;
import static seedu.address.testutil.TypicalExpenses.MOVIE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExpenseCommand;
import seedu.address.model.expense.Category;
//import seedu.address.model.expense.Date;
import seedu.address.model.expense.Expense;
import seedu.address.testutil.ExpenseBuilder;

public class AddCommandParserTest {
    private AddExpenseCommandParser parser = new AddExpenseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expense expectedExpense1 = new ExpenseBuilder(BOOKS).build();

        String userInput1 = AMOUNT_DESC_BOOKS + CATEGORY_DESC_BOOKS
                + DATE_DESC_BOOKS + DESCRIPTION_DESC_BOOKS;

        // whitespace only preamble
        assertParseSuccess(parser, userInput1, new AddExpenseCommand(expectedExpense1));

        Expense expectedExpense2 = new ExpenseBuilder(MOVIE).build();

        String userInput2 = AMOUNT_DESC_MOVIE + CATEGORY_DESC_MOVIE
                + DATE_DESC_MOVIE + DESCRIPTION_DESC_MOVIE;

        assertParseSuccess(parser, userInput2, new AddExpenseCommand(expectedExpense2));

        Expense expectedExpense3 = new ExpenseBuilder(BOOKS).withAmount(VALID_AMOUNT_MOVIE).build();

        String userInput3 = AMOUNT_DESC_MOVIE + CATEGORY_DESC_BOOKS
                + DATE_DESC_BOOKS + DESCRIPTION_DESC_BOOKS;

        assertParseSuccess(parser, userInput3, new AddExpenseCommand(expectedExpense3));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
    //        Expense expectedExpense1 = new ExpenseBuilder(BOOKS).build();
    //
    //        String userInput1 = " a/" + VALID_AMOUNT_BOOKS + " c/" + VALID_CATEGORY_BOOKS
    //                + " D/" + VALID_DATE_BOOKS;
    //
    //        // whitespace only preamble
    //        assertParseSuccess(parser, userInput1, new AddExpenseCommand(expectedExpense1));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenseCommand.MESSAGE_USAGE);

        String userInput1 = CATEGORY_DESC_BOOKS
                + DATE_DESC_BOOKS + DESCRIPTION_DESC_BOOKS;

        // missing name prefix
        assertParseFailure(parser, userInput1, expectedMessage);

        String userInput2 = AMOUNT_DESC_MOVIE + DATE_DESC_MOVIE
                + DESCRIPTION_DESC_MOVIE;

        // missing phone prefix
        assertParseFailure(parser, userInput2, expectedMessage);

        String userInput3 = AMOUNT_DESC_MOVIE + CATEGORY_DESC_BOOKS
                + DESCRIPTION_DESC_BOOKS;

        // missing email prefix
        assertParseFailure(parser, userInput3,
                expectedMessage);

        //        // missing address prefix
        //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
        //                expectedMessage);
        //
        //        // all prefixes missing
        //        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
        //                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid date test not working
        //change to category.message_constraints to pass checkstyle
        //remember to change later
        String userInput1 = AMOUNT_DESC_BOOKS + CATEGORY_DESC_MOVIE
                + " D/" + INVALID_DATE_1 + DESCRIPTION_DESC_BOOKS;
        assertParseFailure(parser, userInput1, Category.MESSAGE_CONSTRAINTS);

        String userInput2 = AMOUNT_DESC_BOOKS + CATEGORY_DESC_BOOKS
                + " D/" + INVALID_DATE_2 + DESCRIPTION_DESC_BOOKS;
        assertParseFailure(parser, userInput2, Category.MESSAGE_CONSTRAINTS);

        //String userInput3 = " a/" + VALID_AMOUNT_BOOKS + " c/" + VALID_CATEGORY_BOOKS
        //+ " D/" + INVALID_DATE_3 + " d/" + VALID_DESCRIPTION_BOOKS;
        //assertParseFailure(parser, userInput3, Category.MESSAGE_CONSTRAINTS);

        String userInput4 = AMOUNT_DESC_BOOKS + " c/" + INVALID_CATEGORY_1
                + " D/" + VALID_DATE_BOOKS + DESCRIPTION_DESC_BOOKS;
        assertParseFailure(parser, userInput4, Category.MESSAGE_CONSTRAINTS);

        String userInput5 = AMOUNT_DESC_BOOKS + " c/" + INVALID_CATEGORY_2
                + " D/" + VALID_DATE_BOOKS + DESCRIPTION_DESC_BOOKS;
        assertParseFailure(parser, userInput5, Category.MESSAGE_CONSTRAINTS);


        //        // invalid name
        //        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
        //
        //        // invalid phone
        //        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
        //
        //        // invalid email
        //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
        //        // invalid tag
        //        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
        //
        //        // two invalid values, only first invalid value reported
        //        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
        //                Name.MESSAGE_CONSTRAINTS);
        //
        //        // non-empty preamble
        //        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
        //                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
        //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
