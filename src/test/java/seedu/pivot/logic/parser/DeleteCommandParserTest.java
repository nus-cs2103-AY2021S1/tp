package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

// For reference, when the code reaches to DeleteCommandParser, it should have only "*TYPE INDEX"
public class DeleteCommandParserTest {

    public static final String INVALID_TYPE = "IamWrongFormat";
    public static final String INVALID_TYPE_MAIN_PAGE = "victim";
    public static final String INVALID_TYPE_CASE_PAGE = "case";
    public static final String VALID_TYPE_MAIN_PAGE = "case";
    public static final String VALID_TYPE_CASE_PAGE = "victim";
    public static final String VALID_INDEX = " " + "1";
    public static final String INVALID_INDEX = " " + "A";
    //public static final String EMPTY_STRING =  "";

    private static Index index = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());

    private DeleteCommandParser parser = new DeleteCommandParser();

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parseMainPage_invalidCommandFormat_throwsParseException() {
        //At Main Page
        StateManager.resetState();

        //Empty Arguments
        //assertThrows(ParseException.class, () -> parser.parse(EMPTY_STRING));
        //assertParseFailure(parser, EMPTY_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        //        DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));

        //No Index
        assertThrows(ParseException.class, () -> parser.parse(VALID_TYPE_MAIN_PAGE));
        assertParseFailure(parser, VALID_TYPE_MAIN_PAGE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
    }

    @Test
    public void parseMainPage_invalidArguments_throwsParseException() {
        //At Main Page
        StateManager.resetState();

        //Type is wrong
        assertThrows(ParseException.class, () -> parser.parse(INVALID_TYPE + VALID_INDEX));
        assertParseFailure(parser, INVALID_TYPE + VALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));

        //Invalid Index
        assertThrows(ParseException.class, () -> parser.parse(VALID_TYPE_MAIN_PAGE + INVALID_INDEX));
        assertParseFailure(parser, VALID_TYPE_MAIN_PAGE + INVALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));

        //Invalid type for Main page
        assertThrows(ParseException.class, () -> parser.parse(INVALID_TYPE_MAIN_PAGE + VALID_INDEX));
        assertParseFailure(parser, INVALID_TYPE_MAIN_PAGE + VALID_INDEX,
                MESSAGE_INCORRECT_CASE_PAGE);
    }

    @Test
    public void parseCasePage_invalidCommandFormat_throwsParseException() {
        //At Case Page
        StateManager.setState(index);

        //Empty Arguments
        //assertThrows(ParseException.class, () -> parser.parse(EMPTY_STRING));
        //assertParseFailure(parser, EMPTY_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        //        DeleteCommand.MESSAGE_USAGE_CASE_PAGE));

        //No Index
        assertThrows(ParseException.class, () -> parser.parse(VALID_TYPE_MAIN_PAGE));
        assertParseFailure(parser, VALID_TYPE_CASE_PAGE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_CASE_PAGE));
    }

    @Test
    public void parseCasePage_invalidArguments_throwsParseException() {
        //At Case Page
        StateManager.setState(index);

        //Type is wrong
        assertThrows(ParseException.class, () -> parser.parse(INVALID_TYPE + VALID_INDEX));
        assertParseFailure(parser, INVALID_TYPE + VALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_CASE_PAGE));

        //Invalid Index
        assertThrows(ParseException.class, () -> parser.parse(VALID_TYPE_CASE_PAGE + INVALID_INDEX));
        assertParseFailure(parser, VALID_TYPE_CASE_PAGE + INVALID_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_CASE_PAGE));

        //Invalid type for Case page
        assertThrows(ParseException.class, () -> parser.parse(INVALID_TYPE_CASE_PAGE + VALID_INDEX));
        assertParseFailure(parser, INVALID_TYPE_CASE_PAGE + VALID_INDEX,
                MESSAGE_INCORRECT_MAIN_PAGE);
    }

}
