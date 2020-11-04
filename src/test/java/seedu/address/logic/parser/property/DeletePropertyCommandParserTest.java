package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.DeletePropertyCommand;
import seedu.address.model.id.PropertyId;

public class DeletePropertyCommandParserTest {

    private DeletePropertyCommandParser parser = new DeletePropertyCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1",
                new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null));
        assertParseSuccess(parser, "\t 1",
                new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null));
    }

    @Test
    public void parse_validPropertyId_returnsDeleteCommand() {
        assertParseSuccess(parser, "P1",
                new DeletePropertyCommand(null, new PropertyId("P1")));
        assertParseSuccess(parser, "\t P1",
                new DeletePropertyCommand(null, new PropertyId("P1")));
    }

    @Test
    public void parse_invalid_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePropertyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePropertyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "p2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePropertyCommand.MESSAGE_USAGE));
    }
}
