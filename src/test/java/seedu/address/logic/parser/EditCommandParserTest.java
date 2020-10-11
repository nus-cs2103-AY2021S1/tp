package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_4_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_5_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_4_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_5_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_4_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_5_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Question;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;

public class EditCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_QUESTION_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_QUESTION_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC, Question.MESSAGE_CONSTRAINTS); // invalid question
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS); // invalid answer
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS); // invalid category

        // invalid question followed by valid answer
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC + VALID_ANSWER_4_DESC, Question.MESSAGE_CONSTRAINTS);

        // valid question followed by invalid question. The test case for invalid question followed by valid question
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + VALID_QUESTION_4_DESC + INVALID_QUESTION_DESC, Question.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC + INVALID_ANSWER_DESC + INVALID_CATEGORY_DESC,
                Question.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased() + VALID_QUESTION_4_DESC + VALID_ANSWER_4_DESC
                + VALID_CATEGORY_4_DESC;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_4)
                .withAnswer(VALID_ANSWER_4).withCategory(VALID_CATEGORY_4).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + VALID_QUESTION_4_DESC + VALID_ANSWER_4_DESC;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_4)
                .withAnswer(VALID_ANSWER_4).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // question
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + VALID_QUESTION_4_DESC;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_4).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // answer
        userInput = targetIndex.getOneBased() + VALID_ANSWER_4_DESC;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_4).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + VALID_CATEGORY_4_DESC;
        descriptor = new EditFlashcardDescriptorBuilder().withCategory(VALID_CATEGORY_4).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + VALID_QUESTION_4_DESC + VALID_ANSWER_4_DESC
                + VALID_CATEGORY_4_DESC + VALID_QUESTION_4_DESC + VALID_ANSWER_4_DESC + VALID_CATEGORY_4_DESC
                + VALID_QUESTION_5_DESC + VALID_ANSWER_5_DESC + VALID_CATEGORY_5_DESC;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_5)
                .withAnswer(VALID_ANSWER_5).withCategory(VALID_CATEGORY_5)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + INVALID_QUESTION_DESC + VALID_QUESTION_4_DESC;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_4)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VALID_QUESTION_4_DESC + INVALID_ANSWER_DESC
                + VALID_CATEGORY_4_DESC + VALID_ANSWER_4_DESC;
        descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_4)
                .withAnswer(VALID_ANSWER_4)
                .withCategory(VALID_CATEGORY_4).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
