package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.logic.commands.NewSessionCommand;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;

public class NewSessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewSessionCommand.MESSAGE_USAGE);

    private NewSessionCommandParser parser = new NewSessionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no session date time specified
        assertParseFailure(parser, "s/New Session", MESSAGE_INVALID_FORMAT);

        // no session name specified
        assertParseFailure(parser, "dt/23-10-2020 0900", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid session name
        assertParseFailure(parser, "s/****", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "x/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInput_returnsNewSessionCommand() {
        String input = " s/New Session dt/23-10-2020 0900";
        NewSessionCommand expectedCommand = new NewSessionCommand(
                new SessionName("New Session"),
                new SessionDateTime(LocalDateTime.of(2020, 10, 23, 9, 0))
        );
        assertParseSuccess(parser, input, expectedCommand);
    }
}
