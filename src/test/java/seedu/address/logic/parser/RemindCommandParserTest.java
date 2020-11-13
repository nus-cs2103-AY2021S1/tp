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
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RemindCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RemindCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemindCommandParserTest {

    private RemindCommandParser parser = new RemindCommandParser();

    @Test
    public void parse_validArgsSingleInput_returnsRemindCommand() {
        List<Index> parsedIndex = new ArrayList<>();
        parsedIndex.add(INDEX_FIRST_ASSIGNMENT);
        assertParseSuccess(parser, "1", new RemindCommand(parsedIndex));
    }

    @Test
    public void parse_validArgsMultipleInput_returnsRemindCommand() throws ParseException {
        List<Index> parsedIndexes = new ArrayList<>();
        parsedIndexes.add(INDEX_FIRST_ASSIGNMENT);
        parsedIndexes.add(INDEX_SECOND_ASSIGNMENT);

        assertParseSuccess(parser, "1 2", new RemindCommand(parsedIndexes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }
}
