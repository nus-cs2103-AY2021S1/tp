package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CHOICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_ALICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.MultipleChoiceQuestion;
import seedu.address.logic.commands.AddMultipleChoiceQuestionCommand;
import seedu.address.testutil.FlashcardBuilder;

public class AddMultipleChoiceQuestionCommandParserTest {
    private AddMultipleChoiceQuestionCommandParser parser = new AddMultipleChoiceQuestionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String[] choices = {"First", "Second", "Third", "Fourth"};
        Flashcard expectedFlashcard = new FlashcardBuilder().withMultipleChoiceQuestion("Alice", choices)
                .withAnswer("2").build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_ALICE + ANSWER_DESC_ALICE + CHOICE_DESC,
                new AddMultipleChoiceQuestionCommand(expectedFlashcard));

        // multiple questions - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_AMY + QUESTION_DESC_ALICE + ANSWER_DESC_ALICE + CHOICE_DESC,
                new AddMultipleChoiceQuestionCommand(expectedFlashcard));

        // multiple answers - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_ALICE + ANSWER_DESC_AMY + ANSWER_DESC_ALICE + CHOICE_DESC,
                new AddMultipleChoiceQuestionCommand(expectedFlashcard));


    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMultipleChoiceQuestionCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, VALID_QUESTION_ALICE
                        + ANSWER_DESC_ALICE + CHOICE_DESC,
                        expectedMessage);

        // missing answer prefix
        assertParseFailure(parser, QUESTION_DESC_ALICE + VALID_ANSWER_ALICE + CHOICE_DESC,
                expectedMessage);

        // missing Choices prefix
        assertParseFailure(parser, QUESTION_DESC_ALICE + ANSWER_DESC_ALICE ,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_ALICE + VALID_ANSWER_ALICE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Question
        assertParseFailure(parser, ANSWER_DESC_ALICE + INVALID_QUESTION_DESC + CHOICE_DESC,
                MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);
        // invalid Answer
        assertParseFailure(parser, QUESTION_DESC_BOB + INVALID_ANSWER_DESC + CHOICE_DESC,
                Answer.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_QUESTION_DESC + INVALID_ANSWER_DESC + CHOICE_DESC,
                MultipleChoiceQuestion.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_ALICE
                        + ANSWER_DESC_ALICE + CHOICE_DESC,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                AddMultipleChoiceQuestionCommand.MESSAGE_USAGE));
    }
}
