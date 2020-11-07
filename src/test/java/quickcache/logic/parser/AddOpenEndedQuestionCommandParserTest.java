package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.commons.core.Messages.MESSAGE_TOO_MANY_ANSWERS;
import static quickcache.commons.core.Messages.MESSAGE_TOO_MANY_QUESTIONS;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_ONE;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_THREE;
import static quickcache.logic.commands.CommandTestUtil.ANSWER_DESC_TWO;
import static quickcache.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static quickcache.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static quickcache.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_ONE;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_THREE;
import static quickcache.logic.commands.CommandTestUtil.QUESTION_DESC_TWO;
import static quickcache.logic.commands.CommandTestUtil.VALID_ANSWER_THREE;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_THREE;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.AddOpenEndedQuestionCommand;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Question;

public class AddOpenEndedQuestionCommandParserTest {
    private final AddOpenEndedQuestionCommandParser parser = new AddOpenEndedQuestionCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddOpenEndedQuestionCommand.MESSAGE_USAGE);

        // missing question prefix
        CommandParserTestUtil.assertParseFailure(parser, VALID_QUESTION_THREE + ANSWER_DESC_THREE,
            expectedMessage);

        // missing answer prefix
        CommandParserTestUtil.assertParseFailure(parser, QUESTION_DESC_THREE + VALID_ANSWER_THREE,
            expectedMessage);


        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, VALID_QUESTION_THREE + VALID_ANSWER_THREE,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Question
        CommandParserTestUtil.assertParseFailure(parser, ANSWER_DESC_THREE + INVALID_QUESTION_DESC,
            Question.MESSAGE_CONSTRAINTS);
        // invalid Answer
        CommandParserTestUtil.assertParseFailure(parser, QUESTION_DESC_THREE + INVALID_ANSWER_DESC,
            Answer.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, INVALID_QUESTION_DESC + INVALID_ANSWER_DESC,
            Question.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_THREE + ANSWER_DESC_THREE,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOpenEndedQuestionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleQuestionsPresent_failure() {
        // multiple questions - not accepted
        CommandParserTestUtil.assertParseFailure(parser,
                QUESTION_DESC_TWO + QUESTION_DESC_ONE + ANSWER_DESC_ONE,
                MESSAGE_TOO_MANY_QUESTIONS);
    }

    @Test
    public void parse_mutipleAnswersPresent_failure() {
        // multiple answers - not accepted
        CommandParserTestUtil.assertParseFailure(parser,
                QUESTION_DESC_ONE + ANSWER_DESC_TWO + ANSWER_DESC_ONE,
                MESSAGE_TOO_MANY_ANSWERS);
    }
}
