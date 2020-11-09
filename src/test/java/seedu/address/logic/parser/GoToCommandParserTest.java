package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseGoToFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseGoToSuccess;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST_TRAVELPLAN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.GoToCommand;

public class GoToCommandParserTest {
    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void parse_validTravelPlanArgs_returnsGoToCommand() {
        assertParseGoToSuccess(parser, " -travelplan 1",
                new GoToCommand(INDEX_FIRST_TRAVELPLAN, true));
    }

    @Test
    public void parse_validWishlistArgs_returnsGoToCommand() {
        assertParseGoToSuccess(parser, " -wishlist", new GoToCommand(false));
    }

    @Test
    public void parse_missingTravelPlanIndex_throwsParseException() {
        assertParseGoToFailure(parser, " -travelplan",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INDEX));
    }

    @Test
    public void parse_redundantWishlistArgs_throwsParseException() {
        assertParseGoToFailure(parser, " -wishlist 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseGoToFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDirectoryType_throwsParseException() {
        assertParseGoToFailure(parser, " -activity 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }
}
