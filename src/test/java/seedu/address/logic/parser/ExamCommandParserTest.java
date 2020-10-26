package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_EXAM_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EXAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.ExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

public class ExamCommandParserTest {

    private final ExamCommandParser parser = new ExamCommandParser();

    @Test
    public void parseValidAddExamCommand_success() throws Exception {
        String userInput = String.format("add %s", INDEX_FIRST_PERSON.getOneBased() + EXAM_DESC_AMY);
        System.out.println(userInput);
        AddExamCommand command = (AddExamCommand) parser
                .parseExamCommand(userInput);
        assertEquals(new AddExamCommand(INDEX_FIRST_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY))), command);
    }

    @Test
    public void parseValidDeleteExamCommand_success() throws Exception {
        String userInput = String.format("delete %s", "" + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_EXAM_INDEX + INDEX_FIRST_PERSON.getOneBased());
        DeleteExamCommand command = (DeleteExamCommand) parser
                .parseExamCommand(userInput);
        assertEquals(new DeleteExamCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExamCommand.MESSAGE_USAGE), () -> parser.parseExamCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_EXAM_COMMAND, () ->
                parser.parseExamCommand("unknownCommand"));
    }
}
