package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_ID_DESC_CS2103T;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.testutil.TypicalModules;

public class AddModuleCommandParserTest {

    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_validArgs_returnsAddModuleCommand() {
        Module expectedModule = CS2103T;
        assertParseSuccess(parser, MODULE_ID_DESC_CS2103T, new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
