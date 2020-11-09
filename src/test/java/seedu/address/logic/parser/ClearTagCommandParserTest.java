//@@author jerrylchong
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearTagCommand;
import seedu.address.model.person.Name;

public class ClearTagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearTagCommand.MESSAGE_USAGE);

    private ClearTagCommandParser parser = new ClearTagCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // empty preamble specified
        assertParseFailure(parser, "", Name.MESSAGE_CONSTRAINTS);

        // invalid name being parsed as preamble
        assertParseFailure(parser, "James&", Name.MESSAGE_CONSTRAINTS);
    }
}
