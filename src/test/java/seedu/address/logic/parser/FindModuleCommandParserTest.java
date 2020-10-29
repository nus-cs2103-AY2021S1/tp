package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindModuleCommandParserTest {

    private FindModuleCommandParser parser = new FindModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindModuleCommand expectedFindCommand =
                new FindModuleCommand(new ModuleContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2030")));
        assertParseSuccess(parser, "CS2103T CS2030", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T \n \t CS2030  \t", expectedFindCommand);
    }

}
