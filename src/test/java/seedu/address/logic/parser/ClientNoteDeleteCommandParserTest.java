package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteDeleteCommand;

class ClientNoteDeleteCommandParserTest {

    private static final String EXPECTED_PARSE_FAILURE_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientNoteDeleteCommand.MESSAGE_USAGE);

    private final ClientNoteDeleteCommandParser parser = new ClientNoteDeleteCommandParser();

    @Test
    public void parse_emptyRestOfCommand_throwsParseException() {
        String userInput = "";
        assertParseFailure(parser, userInput, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    public void parse_inCorrectlyDelimitedRestOfCommand_throwsParseException() {
        String userInput1 = "11";
        assertParseFailure(parser, userInput1, EXPECTED_PARSE_FAILURE_MESSAGE);
    }

    @Test
    public void parse_correctUserInput_doesNotThrowExceptionParsesSuccessfully() {
        Index expectedClientIndex = Index.fromOneBased(1);
        Index expectedClientNoteIndex = Index.fromOneBased(12);
        ClientNoteDeleteCommand expectedCommand =
                new ClientNoteDeleteCommand(expectedClientIndex, expectedClientNoteIndex);
        String restOfCommand = "1 12";
        assertParseSuccess(parser, restOfCommand, expectedCommand);
    }

}
