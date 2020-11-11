package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_NOTE_TEXT;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_NOTE_TITLE;
import static seedu.bookmark.logic.commands.CommandTestUtil.NAME_DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.NOTE_TEXT;
import static seedu.bookmark.logic.commands.CommandTestUtil.NOTE_TITLE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NOTE_TEXT;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.AddNoteCommand;
import seedu.bookmark.model.book.Note;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Note expectedNote = new Note(VALID_NAME_JANE_EYRE, VALID_NOTE_TEXT);

        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_JANE_EYRE + NOTE_TEXT;
        AddNoteCommand expectedNoteCommand = new AddNoteCommand(targetIndex, expectedNote);
        assertParseSuccess(parser, userInput, expectedNoteCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, "1" + VALID_NAME_JANE_EYRE + NOTE_TEXT,
                expectedMessage);

        // missing text prefix
        assertParseFailure(parser, "1" + NAME_DESC_JANE_EYRE + VALID_NOTE_TEXT,
                expectedMessage);

        // missing text field
        assertParseFailure(parser, "1" + NAME_DESC_JANE_EYRE,
                expectedMessage);

        // missing title field
        assertParseFailure(parser, "1" + NOTE_TEXT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title which consists of a whitespace
        assertParseFailure(parser, "1" + INVALID_NOTE_TITLE + NOTE_TEXT, Note.MESSAGE_CONSTRAINTS);

        // invalid text which consists of a whitespace
        assertParseFailure(parser, "1" + NOTE_TITLE + INVALID_NOTE_TEXT, Note.MESSAGE_CONSTRAINTS);

        // no index specified
        assertParseFailure(parser, NOTE_TITLE + NOTE_TEXT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }
}
