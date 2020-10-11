package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListItemCommandParserTest {

    private final ListItemCommandParser parser = new ListItemCommandParser();

    /**
     * Tests any blank input argument should give a list item command.
     */
    @Test
    public void parse_validArgs_returnsListItemCommand() throws ParseException {
        assertEquals(parser.parse(" ").getClass(), ListItemCommand.class);
    }

    /**
     * Tests any non-blank input argument should display the relevant error message.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListItemCommand.MESSAGE_USAGE));
    }
}
