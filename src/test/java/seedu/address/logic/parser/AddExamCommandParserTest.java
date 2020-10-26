package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

public class AddExamCommandParserTest {

    private AddExamCommandParser parser = new AddExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //all fields presents - single
        String userInput = INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY;
        AddExamCommand expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam names - last name accepted
        userInput = INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_BOB, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam dates - last date accepted
        userInput = INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_EXAM_DATE
                + VALID_EXAM_DATE_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_BOB, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam scores - last score accepted
        userInput = INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_SCORE
                + VALID_EXAM_SCORE_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_BOB)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingArguments_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddExamCommand.MESSAGE_USAGE);

        // missing index and prefix
        assertParseFailure(parser, " ", expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, EXAM_DESC_AMY, expectedMessage);

        // valid index, missing all three prefixes
        assertParseFailure(parser, INDEX_SECOND_PERSON + " ", expectedMessage);

        // valid index, missing two prefixes
        assertParseFailure(parser, INDEX_SECOND_PERSON + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_AMY, expectedMessage);

        // valid index, missing one prefixes
        assertParseFailure(parser, INDEX_SECOND_PERSON + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_AMY + " " + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY, expectedMessage);
    }
}
