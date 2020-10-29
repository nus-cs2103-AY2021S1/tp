package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;

public class AddModuleCommandParserTest {

    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_validArgs_returnsAddModuleCommand() {
        //TODO change this to use TypicalModule.CS2100
        Module expectedModule = new Module(new ModuleId("CS2100"));
        assertParseSuccess(parser, "CS2100", new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
