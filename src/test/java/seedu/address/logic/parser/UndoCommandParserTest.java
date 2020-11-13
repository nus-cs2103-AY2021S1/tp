package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoCommand;

class UndoCommandParserTest {

    private static UndoCommandParser parser;

    @BeforeAll
    public static void prepareParser() {
        parser = new UndoCommandParser();
    }

    @Test
    public void parse_invalidInput_throwCommandException() {
        String invalidInput = "abc";
        String invalidInput2 = "123";
        assertParseFailure(parser, invalidInput, UndoCommandParser.ERROR_MESSAGE);
        assertParseFailure(parser, invalidInput2, UndoCommandParser.ERROR_MESSAGE);
    }

    @Test
    public void parse_validInput_success() {
        String validInput = ""; // Empty parameter
        String validInput2 = "   "; // White spaces
        String validInput3 = "\t"; // Tab
        String validInput4 = "\n"; // Newline
        UndoCommand expectedCommand = new UndoCommand();

        assertParseSuccess(parser, validInput, expectedCommand);
        assertParseSuccess(parser, validInput2, expectedCommand);
        assertParseSuccess(parser, validInput3, expectedCommand);
        assertParseSuccess(parser, validInput4, expectedCommand);
    }
}
