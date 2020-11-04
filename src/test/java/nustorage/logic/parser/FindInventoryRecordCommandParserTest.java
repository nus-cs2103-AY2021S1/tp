package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.FindInventoryRecordCommand;
import nustorage.model.record.InventoryRecordNameContainsKeywordsPredicate;

public class FindInventoryRecordCommandParserTest {
    private FindInventoryRecordCommandParser parser = new FindInventoryRecordCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindInventoryRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindInventoryCommand() {
        // no leading and trailing whitespaces
        FindInventoryRecordCommand expectedFindInventoryCommand =
                new FindInventoryRecordCommand(
                        new InventoryRecordNameContainsKeywordsPredicate(Arrays.asList("iPad", "iPhone")));
        assertParseSuccess(parser, "iPad iPhone", expectedFindInventoryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n iPad \n \t iPhone  \t", expectedFindInventoryCommand);
    }
}
