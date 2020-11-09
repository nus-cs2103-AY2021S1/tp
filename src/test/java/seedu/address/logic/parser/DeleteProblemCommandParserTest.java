package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteProblemCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteProblemCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteProblemCommand, and therefore we test only one of them.
 */
public class DeleteProblemCommandParserTest {

    private DeleteProblemCommandParser deleteProblemCommandParser = new DeleteProblemCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteProblemCommand() {
        assertParseSuccess(deleteProblemCommandParser, "3", new DeleteProblemCommand(INDEX_THIRD_BOOK));
    }

    @Test
    public void parse_invalidArguments_throwsParseException() {
        assertParseFailure(deleteProblemCommandParser, "aasd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProblemCommand.MESSAGE_USAGE));
    }
}
