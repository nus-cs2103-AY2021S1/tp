package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEFINITION_DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.DEFINITION_DESC_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEFINITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_BUBBLE_SORT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_BUBBLE_SORT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_BUBBLE_SORT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Title.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DEFINITION_DESC, Definition.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Flashcard} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FINAL + TAG_DESC_DIFFICULT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FINAL + TAG_EMPTY + TAG_DESC_DIFFICULT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FINAL + TAG_DESC_DIFFICULT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEFINITION_DESC,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased() + TAG_DESC_DIFFICULT
                + DEFINITION_DESC_BUBBLE_SORT + NAME_DESC_BUBBLE_SORT + TAG_DESC_FINAL;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withName(VALID_NAME_BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_BUBBLE_SORT)
                .withTags(VALID_TAG_DIFFICULT, VALID_TAG_FINAL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + DEFINITION_DESC_BUBBLE_SORT;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withDefinition(VALID_DEFINITION_BUBBLE_SORT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BUBBLE_SORT;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withName(VALID_NAME_BUBBLE_SORT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // definition
        userInput = targetIndex.getOneBased() + DEFINITION_DESC_BUBBLE_SORT;
        descriptor = new EditFlashcardDescriptorBuilder().withDefinition(VALID_DEFINITION_BUBBLE_SORT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FINAL;
        descriptor = new EditFlashcardDescriptorBuilder().withTags(VALID_TAG_FINAL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + DEFINITION_DESC_BUBBLE_SORT
                + TAG_DESC_FINAL + DEFINITION_DESC_BUBBLE_SORT + TAG_DESC_FINAL
                + DEFINITION_DESC_HEAPING + TAG_DESC_DIFFICULT;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_FINAL, VALID_TAG_DIFFICULT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + INVALID_DEFINITION_DESC + DEFINITION_DESC_HEAPING;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withDefinition(VALID_DEFINITION_HEAPING).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_BUBBLE_SORT + INVALID_DEFINITION_DESC
            + DEFINITION_DESC_HEAPING;
        descriptor = new EditFlashcardDescriptorBuilder()
                .withDefinition(VALID_DEFINITION_HEAPING).withName(VALID_NAME_BUBBLE_SORT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
