package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.commands.CommandTestUtil.INVALID_ANSWER_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.INVALID_CATEGORY_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.INVALID_QUESTION_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_ANSWER_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_ANSWER_3;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_CATEGORY_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_QUESTION_3;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_3;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.commands.AddCommand;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Question;

public class AddCommandParserTest {
    public static final String PREFIX_ANSWER = " a/";
    public static final String PREFIX_CATEGORY = " c/";
    public static final String PREFIX_QUESTION = " q/";
    public static final String SPACE = " ";

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // standard flashcard with category
        assertParseSuccess(parser, SPACE + PREFIX_QUESTION + VALID_QUESTION_1 + PREFIX_ANSWER
                 + VALID_ANSWER_1 + PREFIX_CATEGORY + VALID_CATEGORY_1, new AddCommand(FLASHCARD_1));

        // flashcard with input arguments in reversed order, with category
        assertParseSuccess(parser, SPACE + PREFIX_ANSWER + VALID_ANSWER_1 + PREFIX_QUESTION + VALID_QUESTION_1
                 + PREFIX_CATEGORY + VALID_CATEGORY_1, new AddCommand(FLASHCARD_1));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // standard flashcard without category
        assertParseSuccess(parser, SPACE + PREFIX_QUESTION + VALID_QUESTION_3 + PREFIX_ANSWER
                + VALID_ANSWER_3, new AddCommand(FLASHCARD_3));

        // flashcard with input arguments in reversed order, without category
        assertParseSuccess(parser, SPACE + PREFIX_ANSWER + VALID_ANSWER_3
                + PREFIX_QUESTION + VALID_QUESTION_3 , new AddCommand(FLASHCARD_3));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, SPACE + VALID_QUESTION_1 + PREFIX_ANSWER + VALID_ANSWER_1,
                expectedMessage);

        // missing answer prefix
        assertParseFailure(parser, SPACE + PREFIX_QUESTION + VALID_QUESTION_1 + VALID_ANSWER_1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid question
        assertParseFailure(parser, SPACE + PREFIX_QUESTION + INVALID_QUESTION_1 + PREFIX_ANSWER
                + VALID_ANSWER_1, Question.MESSAGE_CONSTRAINTS);

        // invalid answer
        assertParseFailure(parser, SPACE + PREFIX_QUESTION + VALID_QUESTION_1 + PREFIX_ANSWER
                + INVALID_ANSWER_1, Answer.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, SPACE + PREFIX_QUESTION + VALID_QUESTION_1 + PREFIX_ANSWER
                + VALID_ANSWER_1 + PREFIX_CATEGORY + INVALID_CATEGORY_1, Category.MESSAGE_CONSTRAINTS);
    }
}
