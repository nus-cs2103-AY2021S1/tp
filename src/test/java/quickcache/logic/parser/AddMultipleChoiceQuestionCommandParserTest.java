package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_ALICE;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_AMY;
import static quickcache.logic.commands.CommandTestUtil.CHOICE_DESC;
import static quickcache.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static quickcache.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static quickcache.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static quickcache.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_ALICE;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_BOB;
import static quickcache.logic.commands.CommandTestUtil.VALID_ANSWER_ALICE;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_ALICE;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.AddMultipleChoiceQuestionCommand;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.testutil.FlashcardBuilder;

public class AddMultipleChoiceQuestionCommandParserTest {
    private final AddMultipleChoiceQuestionCommandParser parser = new AddMultipleChoiceQuestionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard expectedFlashcard = new FlashcardBuilder().withMultipleChoiceQuestion("Alice", choices)
            .withAnswer("Second").withTags(new String[]{}).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + QUESTION_DESC_ALICE + ANSWER_DESC_ALICE + CHOICE_DESC,
            new AddMultipleChoiceQuestionCommand(expectedFlashcard));

        // multiple questions - last question accepted
        CommandParserTestUtil.assertParseSuccess(parser,
            QUESTION_DESC_AMY + QUESTION_DESC_ALICE + ANSWER_DESC_ALICE + CHOICE_DESC,
            new AddMultipleChoiceQuestionCommand(expectedFlashcard));

        // multiple answers - last answer accepted
        CommandParserTestUtil.assertParseSuccess(parser,
            QUESTION_DESC_ALICE + ANSWER_DESC_AMY + ANSWER_DESC_ALICE + CHOICE_DESC,
            new AddMultipleChoiceQuestionCommand(expectedFlashcard));


    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddMultipleChoiceQuestionCommand.MESSAGE_USAGE);

        // missing question prefix
        CommandParserTestUtil.assertParseFailure(parser, VALID_QUESTION_ALICE
                + ANSWER_DESC_ALICE + CHOICE_DESC,
            expectedMessage);

        // missing answer prefix
        CommandParserTestUtil.assertParseFailure(parser, QUESTION_DESC_ALICE + VALID_ANSWER_ALICE + CHOICE_DESC,
            expectedMessage);

        // missing Choices prefix
        CommandParserTestUtil.assertParseFailure(parser, QUESTION_DESC_ALICE + ANSWER_DESC_ALICE,
            expectedMessage);


        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, VALID_QUESTION_ALICE + VALID_ANSWER_ALICE,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Question
        CommandParserTestUtil.assertParseFailure(parser, ANSWER_DESC_ALICE + INVALID_QUESTION_DESC + CHOICE_DESC,
            MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);
        // invalid Answer
        CommandParserTestUtil.assertParseFailure(parser, QUESTION_DESC_BOB + INVALID_ANSWER_DESC + CHOICE_DESC,
            Answer.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, INVALID_QUESTION_DESC + INVALID_ANSWER_DESC + CHOICE_DESC,
            MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_ALICE
                + ANSWER_DESC_ALICE + CHOICE_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
    }
}
