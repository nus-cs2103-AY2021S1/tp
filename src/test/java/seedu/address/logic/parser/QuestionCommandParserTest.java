package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TEST_QUESTIONS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOLVE_QUESTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.UnsolvedQuestion;

public class QuestionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuestionCommand.MESSAGE_USAGE);

    private QuestionCommandParser parser = new QuestionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String testQuestion = "How do I look?";
        assertParseFailure(parser, PREFIX_ADD_QUESTION + testQuestion , MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // non-numerical index
        assertParseFailure(parser, "a" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAddParameter_failure() {
        // invalid add parameters
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_ADD_QUESTION,
                Question.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_ADD_QUESTION + " ",
                Question.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidSolveParameter_failure() {
        // invalid solve parameters
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION + " ",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION + "A",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION + "A1",
                MESSAGE_INVALID_FORMAT);

        // invalid question index
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION + "0 test",
                MESSAGE_INVALID_INDEX);

        // invalid solution
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_SOLVE_QUESTION + "1  ",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidDeleteParameter_failure() {
        // invalid delete parameters
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_DELETE_QUESTION,
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_DELETE_QUESTION + " ",
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_DELETE_QUESTION + "B",
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_DELETE_QUESTION + "2b",
                MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_multiplePrefixes_failure() {
        // multiple fields detected
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOLVE_QUESTION + "1 "
                        + PREFIX_ADD_QUESTION + "Hello",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOLVE_QUESTION + "1 "
                        + PREFIX_DELETE_QUESTION + "1",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_DELETE_QUESTION + "1 "
                        + PREFIX_ADD_QUESTION + "Hello",
                MESSAGE_INVALID_FORMAT);

        // all three detected
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_ADD_QUESTION + "Hello "
                        + PREFIX_SOLVE_QUESTION + "1 "
                        + PREFIX_DELETE_QUESTION + "2",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addQuestionPrefix_success() {
        Index target = INDEX_SECOND_PERSON;
        String userInput = target.getOneBased() + " " + PREFIX_ADD_QUESTION + TEST_QUESTIONS[0];
        AddQuestionCommand command = new AddQuestionCommand(target, new UnsolvedQuestion(TEST_QUESTIONS[0]));

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_solveQuestionPrefix_success() {
        Index target = INDEX_THIRD_PERSON;
        Index question = Index.fromOneBased(1);
        String input = question.getOneBased() + " " + DEFAULT_SOLUTION;

        String userInput = target.getOneBased() + " " + PREFIX_SOLVE_QUESTION + input;
        SolveQuestionCommand command = new SolveQuestionCommand(target, question, DEFAULT_SOLUTION);

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_deleteQuestionPrefix_success() {
        Index target = INDEX_SECOND_PERSON;
        Index question = Index.fromOneBased(3);

        String userInput = target.getOneBased() + " " + PREFIX_DELETE_QUESTION + question.getOneBased();
        DeleteQuestionCommand command = new DeleteQuestionCommand(target, question);

        assertParseSuccess(parser, userInput, command);
    }

}
