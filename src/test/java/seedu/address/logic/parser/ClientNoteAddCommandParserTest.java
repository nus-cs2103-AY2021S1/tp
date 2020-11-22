package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.model.note.Note;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;

class ClientNoteAddCommandParserTest {

    private static final String SPACE = " ";
    private static final String INDEX_STRING = "1";
    private static final String NOTE_STRING = "yay this be a note";
    private static final String NO_INDEX_NO_NOTE = "just an empty string here";
    private static final String HAS_INDEX_NO_NOTE = INDEX_STRING + " nope no note here";
    private static final String HAS_INDEX_HAS_NOTE = INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;
    private static final String EXPECTED_PARSE_FAILURE_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientNoteAddCommand.MESSAGE_USAGE);

    private final TagNoteMap tagNoteMap = new TagNoteMap();
    private final ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
    private final Tag testTag = new Tag("testTag");
    private Set<Tag> untaggedTags;
    private Set<Tag> tags;

    @BeforeEach
    public void setUp() {
        tags = new HashSet<>();
        tags.add(testTag);
        untaggedTags = new HashSet<>();
        untaggedTags.add(Tag.UNTAGGED);
    }

    @Test
    public void constructor() {
        assertThrows(NullPointerException.class, () -> new ClientNoteAddCommandParser(null));
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_noIndexNoNote_parseFailure() {
        assertParseFailure(parser, NO_INDEX_NO_NOTE, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    public void parse_hasIndexNoNote_parseFailure() {
        assertParseFailure(parser, HAS_INDEX_NO_NOTE, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    public void parse_hasIndexHasNote_equalsExpected() {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(untaggedTags);
        ClientNoteAddCommand expected = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        assertParseSuccess(parser, HAS_INDEX_HAS_NOTE, expected);
    }

    @Test
    void parse_invalidFormatNoNote_parseFailure() {
        String userInput = INDEX_STRING + SPACE;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertParseFailure(parser, userInput, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    void parse_invalidFormatNoIndex_parseFailure() {
        String userInput = PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertParseFailure(parser, userInput, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    void parse_invalidIndex_parseFailure() {
        String userInput = "invalid index " + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertParseFailure(parser, userInput, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    void parse_validFormatUntaggedNote_parseSuccess() {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(untaggedTags);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_validFormatTaggedNote_parseSuccess() {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(tags);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = INDEX_STRING + SPACE + PREFIX_TAG
                + SPACE + testTag.tagName + SPACE + PREFIX_NOTE + NOTE_STRING;
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_hasTagPrefixNoTagString_parseFailure() {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(untaggedTags);
        String userInput = INDEX_STRING + SPACE + PREFIX_TAG + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

}
