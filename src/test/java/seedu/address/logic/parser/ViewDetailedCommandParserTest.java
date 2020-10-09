package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewDetailsCommand;
import seedu.address.model.item.NameIsExactlyPredicate;

public class ViewDetailedCommandParserTest {

    private ViewDetailsCommandParser parser = new ViewDetailsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDetailsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewDetailsCommand() {
        // no leading and trailing whitespaces
        ViewDetailsCommand expectedViewDetailsCommand =
                new ViewDetailsCommand(new NameIsExactlyPredicate(Collections.singletonList("Shard of Alice #32")));
        assertParseSuccess(parser, "Shard of Alice #32", expectedViewDetailsCommand);
    }

}
