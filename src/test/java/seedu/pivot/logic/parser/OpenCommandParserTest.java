package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.OpenCaseCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

public class OpenCommandParserTest {


    public static final String INVALID_TYPE = "IAmWrongFormat";
    public static final String TYPE_CASE = "case";
    public static final String VALID_INDEX = " " + "1";
    public static final String INVALID_NONEMPTY_INDEX = " " + "A";
    public static final String INVALID_EMPTY_INDEX = " ";

    private static Index index = Index.fromZeroBased(FIRST_INDEX.getZeroBased());

    private OpenCommandParser parser = new OpenCommandParser();

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, TYPE_CASE + VALID_INDEX, new OpenCaseCommand(index));
    }

    @Test
    public void parse_invalidCommandFormat_throwsParseException() {
        //At Main Page
        StateManager.resetState();

        //Empty Arguments
        //assertThrows(ParseException.class, () -> parser.parse(EMPTY_STRING));
        //assertParseFailure(parser, EMPTY_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        //        OpenCommand.MESSAGE_USAGE));

        //No Index
        assertThrows(ParseException.class, () -> parser.parse(TYPE_CASE));
        assertParseFailure(parser, TYPE_CASE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                OpenCaseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArguments_throwsParseException() {
        //At Main Page
        StateManager.resetState();

        //Type is wrong
        assertThrows(ParseException.class, () -> parser.parse(INVALID_TYPE + VALID_INDEX));
        assertParseFailure(parser, INVALID_TYPE + VALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                OpenCaseCommand.MESSAGE_USAGE));

        //Invalid non empty Index
        assertThrows(ParseException.class, () -> parser.parse(TYPE_CASE + INVALID_NONEMPTY_INDEX));
        assertParseFailure(parser, TYPE_CASE + INVALID_NONEMPTY_INDEX, MESSAGE_INVALID_INDEX);

        //Invalid empty index
        assertThrows(ParseException.class, () -> parser.parse(TYPE_CASE + INVALID_EMPTY_INDEX));
        assertParseFailure(parser, TYPE_CASE + INVALID_EMPTY_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                OpenCaseCommand.MESSAGE_USAGE));
    }
}
