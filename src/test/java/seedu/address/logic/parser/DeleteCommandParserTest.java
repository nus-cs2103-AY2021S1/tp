package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgsSingleInput_returnsDeleteCommand() throws ParseException {
        List<Index> parsedIndexes = new ArrayList<>();
        Index index = ParserUtil.parseIndex("1");
        parsedIndexes.add(index);
        assertParseSuccess(parser, "1", new DeleteCommand(parsedIndexes));
    }

    @Test
    public void parse_validArgsMultipleInputs_returnsDeleteCommand() throws ParseException {
        List<Index> parsedIndexes = new ArrayList<>();
        Index indexOne = ParserUtil.parseIndex("1");
        Index indexTwo = ParserUtil.parseIndex("2");
        Index indexThree = ParserUtil.parseIndex("3");
        parsedIndexes.add(indexOne);
        parsedIndexes.add(indexTwo);
        parsedIndexes.add(indexThree);
        assertParseSuccess(parser, "1 2 3", new DeleteCommand(parsedIndexes));
    }

    @Test
    public void parse_invalidArgsAlphabet_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsSpecialChar_throwsParseException() {
        assertParseFailure(parser, "% ^ &", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
