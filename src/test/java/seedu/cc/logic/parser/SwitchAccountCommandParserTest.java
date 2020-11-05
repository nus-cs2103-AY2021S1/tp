package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.accountlevel.SwitchAccountCommand;
import seedu.cc.model.CommonCents;
import seedu.cc.testutil.TypicalEntries;

class SwitchAccountCommandParserTest {

    private SwitchAccountCommandParser parser = new SwitchAccountCommandParser();
    private CommonCents cc = TypicalEntries.getTypicalCommonCents();

    @Test
    public void parse_validIndex_success() {
        assertParseSuccess(parser, "1", new SwitchAccountCommand(INDEX_FIRST_ENTRY));
    }

    @Test
    public void parse_invalidArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchAccountCommand.MESSAGE_USAGE);

        //non-integer index
        assertParseFailure(parser, "a", expectedMessage);

        //negative index
        assertParseFailure(parser, "-1", expectedMessage);

        //too many arguments
        assertParseFailure(parser, "1 2", expectedMessage);

        //no index specified
        assertParseFailure(parser, "", expectedMessage);
    }

}
