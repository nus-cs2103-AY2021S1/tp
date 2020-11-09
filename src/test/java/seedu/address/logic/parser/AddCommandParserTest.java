package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AUTHOR_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AUTHOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ISBN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PUBLISHER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STOCKING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PUBLISHER_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.PUBLISHER_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.STOCKING_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.STOCKING_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.TIMES_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PUBLISHER_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_BOOK2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBooks.BOOK9;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.testutil.BookBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Book expectedBook = new BookBuilder(BOOK9).withCategories(VALID_CATEGORY_SCIENCE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2
                + LANGUAGE_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2 + TIMES_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                new AddCommand(expectedBook));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOOK1 + NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2
                + LANGUAGE_DESC_BOOK2 + CATEGORY_DESC_FRIEND + TIMES_DESC_BOOK2 + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                new AddCommand(expectedBook));

        // multiple isbn - last isbn accepted
        assertParseSuccess(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK1 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2
                + LANGUAGE_DESC_BOOK2 + CATEGORY_DESC_FRIEND + TIMES_DESC_BOOK2 + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                new AddCommand(expectedBook));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK1 + EMAIL_DESC_BOOK2
                + LANGUAGE_DESC_BOOK2 + CATEGORY_DESC_FRIEND + TIMES_DESC_BOOK2 + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                new AddCommand(expectedBook));

        // multiple languages - last language accepted
        assertParseSuccess(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK1
                + LANGUAGE_DESC_BOOK2 + CATEGORY_DESC_FRIEND + TIMES_DESC_BOOK2 + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK1,
                new AddCommand(expectedBook));

        // multiple categories - all accepted
        Book expectedBookMultipleCategories = new BookBuilder(BOOK9).withCategories(VALID_CATEGORY_SCIENCE,
                VALID_CATEGORY_MATH).build();
        assertParseSuccess(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                        + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2, new AddCommand(expectedBookMultipleCategories));

        //multiple stocking - last stocking accepted
        assertParseSuccess(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK1
                        + LANGUAGE_DESC_BOOK2 + TIMES_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK1
                        + STOCKING_DESC_BOOK2 + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2, new AddCommand(expectedBook));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                        + TIMES_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                expectedMessage);

        // missing isbn prefix
        assertParseFailure(parser, NAME_DESC_BOOK2 + VALID_ISBN_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                        + TIMES_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + VALID_EMAIL_BOOK2 + LANGUAGE_DESC_BOOK2
                        + TIMES_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                expectedMessage);

        // missing language prefix
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + VALID_LANGUAGE_BOOK2
                        + TIMES_DESC_BOOK2 + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOOK2 + VALID_ISBN_BOOK2 + VALID_EMAIL_BOOK2
                        + VALID_LANGUAGE_BOOK2 + VALID_TIMES_BOOK2 + CATEGORY_DESC_FRIEND + VALID_STOCKING_BOOK2
                        + VALID_AUTHOR_BOOK2 + VALID_PUBLISHER_BOOK2,
                expectedMessage);
    }

    // new attributes need to be added here
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                Name.MESSAGE_CONSTRAINTS);

        // invalid isbn
        assertParseFailure(parser, NAME_DESC_BOOK2 + INVALID_ISBN_DESC + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                Isbn.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + INVALID_EMAIL_DESC + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                Email.MESSAGE_CONSTRAINTS);

        // invalid language
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + INVALID_LANGUAGE_DESC
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                Language.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + INVALID_CATEGORY_DESC + VALID_CATEGORY_SCIENCE + STOCKING_DESC_BOOK2
                        + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                Category.MESSAGE_CONSTRAINTS);

        // invalid stocking
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + INVALID_STOCKING_DESC
                + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2, Stocking.MESSAGE_CONSTRAINTS);

        // invalid author
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                + INVALID_AUTHOR_DESC
                + PUBLISHER_DESC_BOOK2, Author.MESSAGE_CONSTRAINTS);

        // invalid publisher
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                + AUTHOR_DESC_BOOK2 + INVALID_PUBLISHER_DESC, Publisher.MESSAGE_CONSTRAINTS);

        // invalid times
        assertParseFailure(parser, NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2 + LANGUAGE_DESC_BOOK2
                + INVALID_TIMES_DESC + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOOK2
                + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2, Times.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOOK2 + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK2
                + LANGUAGE_DESC_BOOK2 + TIMES_DESC_BOOK2 + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND
                        + STOCKING_DESC_BOOK2 + AUTHOR_DESC_BOOK2 + PUBLISHER_DESC_BOOK2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
