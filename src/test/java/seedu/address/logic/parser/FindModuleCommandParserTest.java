package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.FindModuleCommand;
import seedu.address.logic.parser.modulelistparsers.FindModuleParser;
import seedu.address.model.module.NameContainsKeywordsPredicate;

public class FindModuleCommandParserTest {

    private FindModuleParser parser = new FindModuleParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindModuleCommand() {
        // no leading and trailing whitespaces
        FindModuleCommand expectedFindModuleCommand =
                new FindModuleCommand(new NameContainsKeywordsPredicate(Arrays.asList("CS2030", "CS2103")));
        assertParseSuccess(parser, "CS2030 CS2103", expectedFindModuleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2030 \n \t CS2103  \t", expectedFindModuleCommand);
    }

}
