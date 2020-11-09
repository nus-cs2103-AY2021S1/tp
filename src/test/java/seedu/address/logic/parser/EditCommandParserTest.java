package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AUTHOR_DESC_BOOK1;
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
import static seedu.address.logic.commands.CommandTestUtil.PUBLISHER_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.STOCKING_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.TIMES_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PUBLISHER_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_BOOK1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditBookDescriptor;
import seedu.address.model.book.*;
import seedu.address.model.book.Email;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.category.Category;
import seedu.address.testutil.EditBookDescriptorBuilder;

public class EditCommandParserTest {

    private static final String CATEGORY_EMPTY = " " + PREFIX_CATEGORY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_BOOK1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_BOOK1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_BOOK1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 q/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid name
        assertParseFailure(parser, "1" + INVALID_ISBN_DESC, Isbn.MESSAGE_CONSTRAINTS);
        // invalid isbn
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + INVALID_LANGUAGE_DESC, Language.MESSAGE_CONSTRAINTS);
        //invalid language
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS);
        //invalid category
        assertParseFailure(parser, "1" + INVALID_STOCKING_DESC, Stocking.MESSAGE_CONSTRAINTS);
        // invalid stocking
        assertParseFailure(parser, "1" + INVALID_TIMES_DESC, Times.MESSAGE_CONSTRAINTS);
        // invalid times
        assertParseFailure(parser, "1" + INVALID_AUTHOR_DESC, Author.MESSAGE_CONSTRAINTS);
        // invalid author
        assertParseFailure(parser, "1" + INVALID_PUBLISHER_DESC, Publisher.MESSAGE_CONSTRAINTS);
        // invalid publisher

        //All Failed

        // invalid isbn followed by valid email
        assertParseFailure(parser, "1" + INVALID_ISBN_DESC + EMAIL_DESC_BOOK1, Isbn.MESSAGE_CONSTRAINTS);

        // valid isbn followed by invalid isbn. The test case for invalid isbn followed by valid isbn
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ISBN_DESC_BOOK2 + INVALID_ISBN_DESC, Isbn.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_CATEGORY} alone will reset the categories of the {@code Book} being edited,
        // parsing it together with a valid category results in error
        assertParseFailure(parser, "1" + CATEGORY_DESC_FRIEND + CATEGORY_DESC_HUSBAND + CATEGORY_EMPTY,
                Category.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_DESC_FRIEND + CATEGORY_EMPTY + CATEGORY_DESC_HUSBAND,
                Category.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CATEGORY_EMPTY + CATEGORY_DESC_FRIEND + CATEGORY_DESC_HUSBAND,
                Category.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_LANGUAGE_BOOK1
                        + VALID_ISBN_BOOK1,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + ISBN_DESC_BOOK2 + CATEGORY_DESC_HUSBAND
                + EMAIL_DESC_BOOK1 + LANGUAGE_DESC_BOOK1 + NAME_DESC_BOOK1 + CATEGORY_DESC_FRIEND;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK1)
                .withIsbn(VALID_ISBN_BOOK2).withEmail(VALID_EMAIL_BOOK1).withLanguage(VALID_LANGUAGE_BOOK1)
                .withCategories(VALID_CATEGORY_MATH, VALID_CATEGORY_SCIENCE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + ISBN_DESC_BOOK2 + EMAIL_DESC_BOOK1;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_BOOK2)
                .withEmail(VALID_EMAIL_BOOK1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_BOOK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BOOK1;
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOOK1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // isbn
        userInput = targetIndex.getOneBased() + ISBN_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withEmail(VALID_EMAIL_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // language
        userInput = targetIndex.getOneBased() + LANGUAGE_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withLanguage(VALID_LANGUAGE_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // categories
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_FRIEND;
        descriptor = new EditBookDescriptorBuilder().withCategories(VALID_CATEGORY_SCIENCE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // stocking
        userInput = targetIndex.getOneBased() + STOCKING_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withStockings(VALID_STOCKING_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // times
        userInput = targetIndex.getOneBased() + TIMES_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withTimes(VALID_TIMES_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // author
        userInput = targetIndex.getOneBased() + AUTHOR_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withAuthor(VALID_AUTHOR_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // publisher
        userInput = targetIndex.getOneBased() + PUBLISHER_DESC_BOOK1;
        descriptor = new EditBookDescriptorBuilder().withPublisher(VALID_PUBLISHER_BOOK1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + ISBN_DESC_BOOK1 + LANGUAGE_DESC_BOOK1 + EMAIL_DESC_BOOK1
                + CATEGORY_DESC_FRIEND + ISBN_DESC_BOOK1 + LANGUAGE_DESC_BOOK1 + EMAIL_DESC_BOOK1 + CATEGORY_DESC_FRIEND
                + ISBN_DESC_BOOK2 + LANGUAGE_DESC_BOOK2 + EMAIL_DESC_BOOK2 + CATEGORY_DESC_HUSBAND;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_BOOK2)
                .withEmail(VALID_EMAIL_BOOK2).withLanguage(VALID_LANGUAGE_BOOK2).withCategories(VALID_CATEGORY_SCIENCE,
                        VALID_CATEGORY_MATH).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + INVALID_ISBN_DESC + ISBN_DESC_BOOK2;
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_BOOK2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOOK2 + INVALID_ISBN_DESC + LANGUAGE_DESC_BOOK2
                + ISBN_DESC_BOOK2;
        descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_BOOK2).withEmail(VALID_EMAIL_BOOK2)
                .withLanguage(VALID_LANGUAGE_BOOK2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetCategories_success() {
        Index targetIndex = INDEX_THIRD_BOOK;
        String userInput = targetIndex.getOneBased() + CATEGORY_EMPTY;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withCategories().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
