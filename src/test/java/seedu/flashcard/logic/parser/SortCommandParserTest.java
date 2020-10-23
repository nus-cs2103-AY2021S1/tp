package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.commands.SortCommand;
import seedu.flashcard.model.flashcard.SortCriteria;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(SortCriteria.LEAST_REVIEWED);
        assertParseSuccess(parser, "leastReviewed", expectedSortCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " \n leastReviewed  \t", expectedSortCommand);

        // case insensitive criteria
        assertParseSuccess(parser, "leASTRevieWED", expectedSortCommand);

    }
}
