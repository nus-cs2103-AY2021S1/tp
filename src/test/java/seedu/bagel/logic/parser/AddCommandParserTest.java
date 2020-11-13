package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.commands.CommandTestUtil.DESC_DESC_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.DESC_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.bagel.logic.commands.CommandTestUtil.INVALID_LINK_DESC;
import static seedu.bagel.logic.commands.CommandTestUtil.INVALID_SET_DESC;
import static seedu.bagel.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.bagel.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.bagel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.bagel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.bagel.logic.commands.CommandTestUtil.SET_DESC_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.SET_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.bagel.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.bagel.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_SET_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.bagel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bagel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.AMY;
import static seedu.bagel.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.commands.AddCommand;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;
import seedu.bagel.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_BOB + DESC_DESC_BOB + SET_DESC_BOB
                        + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));

        // multiple word titles accepted
        assertParseSuccess(parser, TITLE_DESC_AMY + TITLE_DESC_BOB + DESC_DESC_BOB + SET_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));

        // multiple word desc accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DESC_DESC_AMY + DESC_DESC_BOB + SET_DESC_BOB
                        + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));

        // multiple tags - all accepted
        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, TITLE_DESC_BOB + DESC_DESC_BOB + SET_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedFlashcardMultipleTags));
        assertParseSuccess(parser, TITLE_DESC_BOB + DESC_DESC_BOB + TAG_DESC_HUSBAND + SET_DESC_BOB
                        + TAG_DESC_FRIEND, new AddCommand(expectedFlashcardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DESC_DESC_AMY + SET_DESC_AMY,
                new AddCommand(expectedFlashcard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_BOB + DESC_DESC_BOB + SET_DESC_BOB,
                expectedMessage);

        // missing desc prefix
        assertParseFailure(parser, TITLE_DESC_BOB + VALID_DESC_BOB + SET_DESC_BOB,
                expectedMessage);

        // missing set prefix
        assertParseFailure(parser, TITLE_DESC_BOB + DESC_DESC_BOB + VALID_SET_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_BOB + VALID_DESC_BOB + VALID_SET_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESC_DESC_BOB + SET_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Title.MESSAGE_CONSTRAINTS);

        // invalid desc
        assertParseFailure(parser, TITLE_DESC_BOB + INVALID_DESC_DESC + SET_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid set
        assertParseFailure(parser, TITLE_DESC_BOB + DESC_DESC_BOB
                + INVALID_SET_DESC + TAG_DESC_HUSBAND + VALID_TAG_FRIEND, FlashcardSet.MESSAGE_CONSTRAINTS);

        // invalid link
        assertParseFailure(parser, TITLE_DESC_BOB + DESC_DESC_BOB + INVALID_LINK_DESC + SET_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Link.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_BOB + DESC_DESC_BOB + SET_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_DESC_DESC + SET_DESC_BOB,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_BOB + DESC_DESC_BOB + SET_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));
    }
}
