package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PATIENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddVisitCommand;

class AddVisitCommandParserTest {
    private final AddVisitCommandParser parser = new AddVisitCommandParser();

    @Test
    void parse_validArgs_returnsAddVisitCommand() {
        String visitDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertParseSuccess(parser, "1", new AddVisitCommand(visitDate, INDEX_FIRST_PATIENT));
        assertParseSuccess(parser, "2", new AddVisitCommand(visitDate, INDEX_SECOND_PATIENT));
        assertParseSuccess(parser, "3", new AddVisitCommand(visitDate, INDEX_THIRD_PATIENT));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1" , String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            AddVisitCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            AddVisitCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            AddVisitCommand.MESSAGE_USAGE));
    }
}
