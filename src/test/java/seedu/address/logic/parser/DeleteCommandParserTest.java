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
        List<Index> parsedIndexes = ParserUtil.parseIndexes("1 2");
        assertParseSuccess(parser, "1 2", new DeleteCommand(parsedIndexes));
    }

    @Test
    public void parse_invalidArgsNegativeIndex_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsAlphabet_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "b hcc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsSpecialChar_throwsParseException() {
        assertParseFailure(parser, "*", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "% ^ &", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
