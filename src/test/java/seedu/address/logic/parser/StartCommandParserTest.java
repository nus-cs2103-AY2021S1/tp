package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_INPUT_FOR_ONE_WORD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEMESTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CORRECT_SEMESTER_OF_MOD_NAME_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.StartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.semester.Semester;

public class StartCommandParserTest {

    private StartCommandParser parser = new StartCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_INPUT_FOR_ONE_WORD_COMMAND, Messages.MESSAGE_INVALID_SEMESTER);
    }

    @Test
    public void parse_invalidSemester_failure() {
        Semester invalidSemester = INVALID_SEMESTER;
        assertParseFailure(parser, invalidSemester.toString(), Messages.MESSAGE_INVALID_SEMESTER);
    }

    @Test
    public void parse_validSemester_success() throws ParseException {
        Semester validSemester = VALID_CORRECT_SEMESTER_OF_MOD_NAME_B;
        StartCommand startCommand = new StartCommand(validSemester);
        assertParseSuccess(parser, validSemester.toString(), startCommand);
    }
}
