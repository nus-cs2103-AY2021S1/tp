package com.eva.logic.parser;

import static com.eva.logic.parser.CommandParserTestUtil.assertParseFailure;
import static com.eva.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static com.eva.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.DeleteStaffCommand;


public class DeleteStaffCommandParserTest {

    private DeleteStaffCommandParser parser = new DeleteStaffCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteStaffCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE));
    }
}
