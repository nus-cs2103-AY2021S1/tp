package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.QuestionCommand.QuestionCommandPrefix.ADD_QUESTION_PREFIX;
import static seedu.address.logic.commands.QuestionCommand.QuestionCommandPrefix.SOLVE_QUESTION_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.model.student.Question;

public class QuestionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuestionCommand.MESSAGE_USAGE);

    private QuestionCommandParser parser = new QuestionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String testQuestion = "How do I look?";
        assertParseFailure(parser, ADD_QUESTION_PREFIX + testQuestion , MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid add parameters
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + ADD_QUESTION_PREFIX + " ",
                Question.MESSAGE_CONSTRAINTS);

        // invalid solve parameters
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + SOLVE_QUESTION_PREFIX + " ",
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + SOLVE_QUESTION_PREFIX + "A",
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + SOLVE_QUESTION_PREFIX + "A1",
                MESSAGE_INVALID_INDEX);

        // multiple prefixes detected
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                        + SOLVE_QUESTION_PREFIX + "1 "
                        + ADD_QUESTION_PREFIX + "Hello",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addQuestionPrefix_success() {
        Index target = INDEX_SECOND_PERSON;
        String testQuestion = "How do I look?";

        String userInput = target.getOneBased() + " " + ADD_QUESTION_PREFIX + testQuestion;
        AddQuestionCommand command = new AddQuestionCommand(target, new Question(testQuestion));

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_solveQuestionPrefix_success() {
        Index target = INDEX_THIRD_PERSON;
        Index question = INDEX_FIRST_PERSON;

        String userInput = target.getOneBased() + " " + SOLVE_QUESTION_PREFIX + question.getOneBased();
        SolveQuestionCommand command = new SolveQuestionCommand(target, question);

        assertParseSuccess(parser, userInput, command);
    }

}
