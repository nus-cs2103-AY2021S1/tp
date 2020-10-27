package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.DeallocateCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AllocateCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeallocateCommandParserTest {

    private DeallocateCommandParser parser = new DeallocateCommandParser();

    @Test
    public void parse_validArgs_returnsDeallocateCommand() {
        assertParseSuccess(parser, " si/1", new DeallocateCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeallocateCommand.HELP.getFullMessage()));
    }
}
