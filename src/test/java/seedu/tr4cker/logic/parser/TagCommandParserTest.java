package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DELETE_TAG;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NEW_TAG;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NO_DELETE_TAG;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NO_NEW_TAG;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DELETE_ASSIGNMENT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DELETE_MISSION;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_NEW_HOMEWORK;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_NEW_WORK;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_1;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.TagCommand;
import seedu.tr4cker.model.tag.Tag;

class TagCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private final TagCommandParser parser = new TagCommandParser();
    private final Tag tag1 = new Tag("homework");
    private final Tag tag2 = new Tag("assignment");
    private final Tag tag3 = new Tag("work");
    private final Tag tag4 = new Tag("mission");
    private final Set<Tag> add = new HashSet<>();
    private final Set<Tag> delete = new HashSet<>();
    private final Set<Tag> emptySet = new HashSet<>();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() { // invalid tags
        assertParseFailure(parser, "1" + INVALID_NEW_TAG, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DELETE_TAG, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsPresent_success() { // tag 1 new/tag1 del/tag2
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TAG_NEW_HOMEWORK + TAG_DELETE_ASSIGNMENT;

        add.add(tag1);
        delete.add(tag2);
        TagCommand tagCommand = new TagCommand(targetIndex, add, delete);

        assertParseSuccess(parser, userInput, tagCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() { // tag 1 new/tag1 || tag 1 del/tag2
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput1 = targetIndex.getOneBased() + TAG_DELETE_ASSIGNMENT;
        String userInput2 = targetIndex.getOneBased() + TAG_NEW_HOMEWORK;

        add.add(tag1);
        delete.add(tag2);

        // no new/
        TagCommand tagCommand1 = new TagCommand(targetIndex, emptySet, delete);
        assertParseSuccess(parser, userInput1, tagCommand1);

        // no del/
        TagCommand tagCommand2 = new TagCommand(targetIndex, add, emptySet);
        assertParseSuccess(parser, userInput2, tagCommand2);
    }

    @Test
    public void parse_valuesMissing_failure() { // tag 1 new/ || tag 1 del/
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput1 = targetIndex.getOneBased() + TAG_DELETE_ASSIGNMENT + INVALID_NO_NEW_TAG;
        String userInput2 = targetIndex.getOneBased() + TAG_NEW_HOMEWORK + INVALID_NO_DELETE_TAG;
        String userInput3 = targetIndex.getOneBased() + INVALID_NO_NEW_TAG + INVALID_NO_DELETE_TAG;

        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;

        // missing new prefix
        assertParseFailure(parser, userInput1, expectedMessage);

        // missing del prefix
        assertParseFailure(parser, userInput2, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, userInput3, expectedMessage);
    }

    @Test
    public void parse_multipleValues_success() { // tag 1 new/tag1 del/tag2 new/tag3
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput1 = targetIndex.getOneBased() + TAG_DELETE_ASSIGNMENT
                + TAG_NEW_HOMEWORK + TAG_NEW_WORK;
        String userInput2 =
                targetIndex.getOneBased() + TAG_DELETE_ASSIGNMENT + TAG_NEW_WORK
                        + TAG_DELETE_MISSION + TAG_NEW_HOMEWORK;
        String userInput3 = targetIndex.getOneBased() + TAG_NEW_HOMEWORK
                + TAG_DELETE_ASSIGNMENT + TAG_DELETE_MISSION;


        add.add(tag1);
        add.add(tag3);
        delete.add(tag2);
        assertParseSuccess(parser, userInput1, new TagCommand(targetIndex, add, delete));

        delete.add(tag4);
        assertParseSuccess(parser, userInput2, new TagCommand(targetIndex, add, delete));

        add.remove(tag3);
        assertParseSuccess(parser, userInput3, new TagCommand(targetIndex, add, delete));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1 new/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

}
