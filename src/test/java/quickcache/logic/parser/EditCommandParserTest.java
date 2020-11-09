package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_THREE;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_TWO;
import static quickcache.logic.commands.CommandTestUtil.CHOICE_DESC_CHOICE1;
import static quickcache.logic.commands.CommandTestUtil.DIFFICULTY_DESC_LOW;
import static quickcache.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static quickcache.logic.commands.CommandTestUtil.INVALID_CHOICE_DESC;
import static quickcache.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_THREE;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_TWO;
import static quickcache.logic.commands.CommandTestUtil.TAG_DESC_TAG1;
import static quickcache.logic.commands.CommandTestUtil.VALID_ANSWER_THREE;
import static quickcache.logic.commands.CommandTestUtil.VALID_ANSWER_TWO;
import static quickcache.logic.commands.CommandTestUtil.VALID_CHOICE_CHOICE1;
import static quickcache.logic.commands.CommandTestUtil.VALID_DIFFICULTY_LOW;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_THREE;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_TWO;
import static quickcache.logic.commands.CommandTestUtil.VALID_TAG_TAG1;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static quickcache.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static quickcache.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.index.Index;
import quickcache.logic.commands.EditCommand;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Question;
import quickcache.testutil.EditFlashcardDescriptorBuilder;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_TWO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_TWO, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_TWO, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid question
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC, Question.MESSAGE_CONSTRAINTS);

        // invalid answer
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS);

        // invalid question followed by valid answer
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC + ANSWER_DESC_TWO,
            Question.MESSAGE_CONSTRAINTS);

        // valid answer followed by invalid question. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + QUESTION_DESC_THREE + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC + INVALID_ANSWER_DESC,
            Question.MESSAGE_CONSTRAINTS);

        // empty choice option
        assertParseFailure(parser, "1" + INVALID_CHOICE_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased()
                + QUESTION_DESC_THREE
                + ANSWER_DESC_THREE
                + TAG_DESC_TAG1
                + CHOICE_DESC_CHOICE1
                + DIFFICULTY_DESC_LOW;

        Choice[] choices = new Choice[] {new Choice(VALID_CHOICE_CHOICE1)};

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withQuestion(VALID_QUESTION_THREE)
            .withAnswer(VALID_ANSWER_THREE)
            .withTags(VALID_TAG_TAG1)
            .withChoices(choices)
            .withDifficulty(VALID_DIFFICULTY_LOW).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_THREE;

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withQuestion(VALID_QUESTION_THREE)
            .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // question
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_TWO;
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withQuestion(VALID_QUESTION_TWO).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // answer
        userInput = targetIndex.getOneBased() + ANSWER_DESC_TWO;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_TWO).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_TWO + ANSWER_DESC_TWO
            + ANSWER_DESC_THREE + QUESTION_DESC_THREE;

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withQuestion(VALID_QUESTION_THREE).withAnswer(VALID_ANSWER_THREE)
            .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + INVALID_QUESTION_DESC + ANSWER_DESC_THREE + QUESTION_DESC_THREE;
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
            .withAnswer(VALID_ANSWER_THREE).withQuestion(VALID_QUESTION_THREE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + QUESTION_DESC_THREE + INVALID_ANSWER_DESC + ANSWER_DESC_THREE;
        descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_THREE)
                .withAnswer(VALID_ANSWER_THREE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
