package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertDeleteParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertDeleteParseSuccess;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.delete.DeleteAccommodationCommand;
import seedu.address.logic.command.delete.DeleteActivityCommand;
import seedu.address.logic.command.delete.DeleteCommand;
import seedu.address.logic.command.delete.DeleteFriendCommand;
import seedu.address.logic.command.delete.DeleteTravelPlanCommand;





public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validActivityArgs_returnsDeleteCommand() {
        assertDeleteParseSuccess(parser, " -activity 1", new DeleteActivityCommand(INDEX_FIRST));
    }

    @Test
    public void parse_validAccommodationArgs_returnsDeleteCommand() {
        assertDeleteParseSuccess(parser, " -accommodation 1",
                new DeleteAccommodationCommand(INDEX_FIRST));
    }
    @Test
    public void parse_validFriendArgs_returnsDeleteCommand() {
        assertDeleteParseSuccess(parser, " -friend 1", new DeleteFriendCommand(INDEX_FIRST));
    }

    @Test
    public void parse_validTravelPlanArgs_returnsDeleteCommand() {
        assertDeleteParseSuccess(parser, " -travelplan 1", new DeleteTravelPlanCommand(INDEX_FIRST));
    }


    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertDeleteParseFailure(parser, " -activiti 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsLength_throwsParseException() {
        assertDeleteParseFailure(parser, " -activity 1 asdasd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
