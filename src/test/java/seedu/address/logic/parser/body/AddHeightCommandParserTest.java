package seedu.address.logic.parser.body;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.parser.AddHeightCommandParser;
import seedu.address.model.body.Height;

public class AddHeightCommandParserTest {

    private final AddHeightCommandParser parser = new AddHeightCommandParser();

    @Test
    public void parse_validPrefixAndInput_success() {
        Height testHeight = new Height(170);

        //Correct format as per UG
        assertParseSuccess(parser, " h/170", new AddHeightCommand(testHeight));

        //Trailing whitespace and infront as well
        assertParseSuccess(parser, "  h/170 ", new AddHeightCommand(testHeight));
    }

    @Test
    public void parse_invalidPrefix_failure() {
        String failureMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddHeightCommand.MESSAGE_USAGE);

        //Mulitple height prefixes
        assertParseFailure(parser, " h/170 h/180", failureMessage);

        //Missing Prefix
        assertParseFailure(parser, " 179", failureMessage);
    }

    @Test
    public void parse_invalidInput_failure() {

        //Negative input
        assertParseFailure(parser, " h/-179", Height.MESSAGE_CONSTRAINTS_FORMAT);

        //Zero input
        assertParseFailure(parser, " h/0", Height.MESSAGE_CONSTRAINTS_LIMIT);

        //Too large of an input
        assertParseFailure(parser, " h/2147483648", Height.MESSAGE_CONSTRAINTS_LIMIT);

        //Impossible height values
        assertParseFailure(parser, " h/20", Height.MESSAGE_CONSTRAINTS_LIMIT);
        assertParseFailure(parser, " h/250", Height.MESSAGE_CONSTRAINTS_LIMIT);

    }

}
