package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEFINITION_DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.DEFINITION_DESC_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEFINITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFlashcards.AMY;
import static seedu.address.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(BOB).withTags(VALID_TAG_FINAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_HEAPING
                + DEFINITION_DESC_HEAPING + TAG_DESC_FINAL, new AddCommand(expectedFlashcard));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BUBBLE_SORT + NAME_DESC_HEAPING
                + DEFINITION_DESC_HEAPING + TAG_DESC_FINAL, new AddCommand(expectedFlashcard));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_HEAPING + DEFINITION_DESC_BUBBLE_SORT
                + DEFINITION_DESC_HEAPING + TAG_DESC_FINAL, new AddCommand(expectedFlashcard));

        // multiple tags - all accepted
        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(BOB).withTags(VALID_TAG_FINAL,
            VALID_TAG_DIFFICULT).build();
        assertParseSuccess(parser, NAME_DESC_HEAPING + DEFINITION_DESC_HEAPING
                + TAG_DESC_DIFFICULT + TAG_DESC_FINAL, new AddCommand(expectedFlashcardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_BUBBLE_SORT + DEFINITION_DESC_BUBBLE_SORT,
                new AddCommand(expectedFlashcard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_HEAPING + DEFINITION_DESC_HEAPING,
                expectedMessage);

        // missing definition prefix
        assertParseFailure(parser, NAME_DESC_HEAPING + VALID_DEFINITION_HEAPING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_HEAPING + DEFINITION_DESC_HEAPING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEFINITION_DESC_HEAPING
                + TAG_DESC_DIFFICULT + TAG_DESC_FINAL, Title.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_HEAPING + INVALID_DEFINITION_DESC
                + TAG_DESC_DIFFICULT + TAG_DESC_FINAL, Definition.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_HEAPING + DEFINITION_DESC_HEAPING
                + INVALID_TAG_DESC + VALID_TAG_FINAL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_DEFINITION_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_HEAPING
                + DEFINITION_DESC_HEAPING + TAG_DESC_DIFFICULT + TAG_DESC_FINAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
