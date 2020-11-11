package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.commons.core.Messages.MESSAGE_TOO_MANY_TAGS;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.NAME_DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_THIRD_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "n/" + VALID_NAME_1984, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_1984, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_1984, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_GENRE_DESC, Genre.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // too many tags
        int tagLimit = Book.MAX_TAG_COUNT;
        StringBuilder userInput = new StringBuilder();

        for (int i = 0; i <= tagLimit + 1; i++) {
            userInput.append(String.format(" %s %d ", PREFIX_TAG, i));
        }
        assertParseFailure(parser, "1" + userInput.toString(), String.format(MESSAGE_TOO_MANY_TAGS, tagLimit));


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Book} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_GOOD + TAG_DESC_BAD + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_GOOD + TAG_EMPTY + TAG_DESC_BAD, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_GOOD + TAG_DESC_BAD, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_GENRE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BAD
                + GENRE_DESC_1984 + NAME_DESC_1984 + TAG_DESC_GOOD;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_1984)
                .withGenre(VALID_GENRE_1984)
                .withTags(VALID_TAG_GOOD, VALID_TAG_BAD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + GENRE_DESC_1984;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder()
                .withGenre(VALID_GENRE_1984).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_BOOK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_1984;
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_1984).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + GENRE_DESC_1984;
        descriptor = new EditBookDescriptorBuilder().withGenre(VALID_GENRE_1984).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BAD;
        descriptor = new EditBookDescriptorBuilder().withTags(VALID_TAG_BAD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + GENRE_DESC_1984
                + TAG_DESC_GOOD + GENRE_DESC_1984 + TAG_DESC_GOOD
                 + GENRE_DESC_JANE_EYRE + TAG_DESC_BAD;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder()
                .withGenre(VALID_GENRE_JANE_EYRE).withTags(VALID_TAG_BAD, VALID_TAG_GOOD)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_BOOK;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
