package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DoneCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DoneCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DoneCommandParserTest {

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_validArgsSingleInput_returnsDoneCommand() {
        List<Index> parsedIndex = new ArrayList<>();
        parsedIndex.add(INDEX_FIRST_ASSIGNMENT);
        assertParseSuccess(parser, "1", new DoneCommand(parsedIndex));
    }

    @Test
    public void parse_validArgsMultipleInput_returnsDoneCommand() throws ParseException {
        List<Index> parsedIndexes = new ArrayList<>();
        parsedIndexes.add(INDEX_FIRST_ASSIGNMENT);
        parsedIndexes.add(INDEX_SECOND_ASSIGNMENT);

        assertParseSuccess(parser, "1 2", new DoneCommand(parsedIndexes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }
}
