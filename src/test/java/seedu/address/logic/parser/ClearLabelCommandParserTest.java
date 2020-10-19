package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ClearLabelCommand;
import seedu.address.model.person.Name;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ClearLabelCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearLabelCommand.MESSAGE_USAGE);

    private ClearLabelCommandParser parser = new ClearLabelCommandParser();
    @Test
    public void parse_missingParts_failure() {
        // no tags field specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "James&", Name.MESSAGE_CONSTRAINTS);
    }
}
