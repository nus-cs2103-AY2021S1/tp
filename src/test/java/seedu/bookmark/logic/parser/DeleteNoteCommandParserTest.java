package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.DeleteNoteCommand;

public class DeleteNoteCommandParserTest {
    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        Index targetNoteIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + " " + targetNoteIndex.getOneBased();
        DeleteNoteCommand expectedNoteCommand = new DeleteNoteCommand(targetIndex, targetNoteIndex);
        assertParseSuccess(parser, userInput, expectedNoteCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE);
        // missing note index
        assertParseFailure(parser, "1",
                expectedMessage);
        // missing input
        assertParseFailure(parser, "",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE);
        // non numerical index
        assertParseFailure(parser, "hi 1",
                expectedMessage);

        // non numerical note index
        assertParseFailure(parser, "1 hi",
                expectedMessage);
    }
}
