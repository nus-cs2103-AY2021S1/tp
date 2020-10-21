package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.animal.AnimalComparator;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_throwsParseException() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(AnimalComparator.createAnimalNameComparator());
        assertParseSuccess(parser, "name", expectedSortCommand);

        // multiple whitespaces before and after keyword
        assertParseSuccess(parser, " \t name \t", expectedSortCommand);
    }
}
