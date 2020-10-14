package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ListRoomFilter;

class ListRoomCommandParserTest {

    private final ListRoomCommandParser parser = new ListRoomCommandParser();

    @Test
    void parse_whitespaceOnlyArg_returnsListRoomCommand() {
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(ListRoomFilter.ALL);

        // empty input
        assertParseSuccess(parser, "", expectedListRoomCommand);

        // whitespace
        assertParseSuccess(parser, "            ", expectedListRoomCommand);

        // tabs
        assertParseSuccess(parser, "\t", expectedListRoomCommand);

        // tabs
        assertParseSuccess(parser, "         \t       ", expectedListRoomCommand);
    }

    @Test
    void parse_validVacantCommand_returnsListRoomCommand() {
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(ListRoomFilter.VACANT);
        assertParseSuccess(parser, "--" + ListRoomCommand.COMMAND_VACANT_FLAG, expectedListRoomCommand);
    }

    @Test
    void parse_validAllocatedCommand_returnsListRoomCommand() {
        ListRoomCommand expectedListRoomCommand = new ListRoomCommand(ListRoomFilter.ALLOCATED);
        assertParseSuccess(parser, "--" + ListRoomCommand.COMMAND_ALLOCATED_FLAG, expectedListRoomCommand);
    }

    @Test
    void parse_invalidOptionalArgs_throwsParseException() {
        // missing flag
        assertParseFailure(parser, "--",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
        // no two dashes
        assertParseFailure(parser, "-",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
        // misspelled vacant
        assertParseFailure(parser, "--vacent",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
        // misspelled allocated
        assertParseFailure(parser, "-allokated",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
    }

}
