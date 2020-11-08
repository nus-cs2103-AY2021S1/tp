package seedu.address.logic.parser.body;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.parser.AddWeightCommandParser;
import seedu.address.model.body.Weight;

public class AddWeightCommandParserTest {

    private final AddWeightCommandParser parser = new AddWeightCommandParser();

    @Test
    public void parse_validPrefixAndInput_success() {
        Weight testWeight = new Weight(70);

        //Correct format as per UG
        assertParseSuccess(parser, " w/70", new AddWeightCommand(testWeight));

        //Trailing whitespace and infront as well
        assertParseSuccess(parser, "  w/70 ", new AddWeightCommand(testWeight));
    }

    @Test
    public void parse_invalidPrefix_failure() {
        String failureMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE);

        //Mulitple height prefixes
        assertParseFailure(parser, " w/70 w/80", failureMessage);

        //Missing Prefix
        assertParseFailure(parser, " 79", failureMessage);
    }

    @Test
    public void parse_invalidInput_failure() {

        //Negative input
        assertParseFailure(parser, " w/-79", Weight.MESSAGE_CONSTRAINTS_FORMAT);

        //Zero input
        assertParseFailure(parser, " w/0", Weight.MESSAGE_CONSTRAINTS_LIMIT);

        //Too large of an input
        assertParseFailure(parser, " w/2147483648", Weight.MESSAGE_CONSTRAINTS_LIMIT);

        //Impossible height values
        assertParseFailure(parser, " w/20", Weight.MESSAGE_CONSTRAINTS_LIMIT);
        assertParseFailure(parser, " w/350", Weight.MESSAGE_CONSTRAINTS_LIMIT);

    }

}
