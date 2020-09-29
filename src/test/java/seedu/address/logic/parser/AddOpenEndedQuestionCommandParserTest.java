package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.OpenEndedQuestion;
import seedu.address.logic.commands.AddOpenEndedQuestionCommand;

public class AddOpenEndedQuestionCommandParserTest {
    private AddOpenEndedQuestionCommandParser parser = new AddOpenEndedQuestionCommandParser();

    /*
    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedPerson = new FlashcardBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_BOB + ANSWER_DESC_BOB,
                new AddOpenEndedQuestionCommand(expectedPerson));

        // multiple questions - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_AMY + QUESTION_DESC_BOB + ANSWER_DESC_BOB,
                new AddOpenEndedQuestionCommand(expectedPerson));

        // multiple answers - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_BOB + ANSWER_DESC_AMY + ANSWER_DESC_BOB,
                new AddOpenEndedQuestionCommand(expectedPerson));


    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(AMY).build();
        assertParseSuccess(parser, QUESTION_DESC_AMY + ANSWER_DESC_AMY,
                new AddOpenEndedQuestionCommand(expectedFlashcard));
    }
    */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddOpenEndedQuestionCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, VALID_QUESTION_BOB + ANSWER_DESC_BOB ,
                expectedMessage);

        // missing answer prefix
        assertParseFailure(parser, QUESTION_DESC_BOB + VALID_ANSWER_BOB ,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_BOB + VALID_ANSWER_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Question
        assertParseFailure(parser, ANSWER_DESC_BOB + INVALID_QUESTION_DESC,
                OpenEndedQuestion.MESSAGE_CONSTRAINTS);
        // invalid Answer
        assertParseFailure(parser, QUESTION_DESC_BOB + INVALID_ANSWER_DESC,
                Answer.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_QUESTION_DESC + INVALID_ANSWER_DESC,
                OpenEndedQuestion.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_BOB + ANSWER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOpenEndedQuestionCommand.MESSAGE_USAGE));
    }
}
