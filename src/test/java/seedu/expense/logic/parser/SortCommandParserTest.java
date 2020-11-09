package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.SortCommand.REVERSE_KEYWORD;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.SortCommand;
import seedu.expense.model.expense.AmountComparator;
import seedu.expense.model.expense.DateComparator;
import seedu.expense.model.expense.DescriptionComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_SORT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_SORT + "dezzcription",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, "please " + PREFIX_SORT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleKeyword_oneSuccess() {
        String userInput = " " + PREFIX_SORT + DescriptionComparator.SORT_KEYWORD + REVERSE_KEYWORD;
        DateComparator dateComparator =
                new DateComparator(false, false, -1);
        AmountComparator amountComparator =
                new AmountComparator(false, false, -1);
        DescriptionComparator descriptionComparator = new DescriptionComparator(true, true, 0);
        SortCommand expectedCommand = new SortCommand(descriptionComparator);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleKeywords_multipleSuccess() {
        String userInput = " " + PREFIX_SORT + DescriptionComparator.SORT_KEYWORD
                + REVERSE_KEYWORD + " " + PREFIX_SORT + AmountComparator.SORT_KEYWORD;
        DateComparator dateComparator =
                new DateComparator(false, false, -1);
        DescriptionComparator descriptionComparator = new DescriptionComparator(true, true, 0);
        AmountComparator amountComparator =
                new AmountComparator(true, false, 1);
        SortCommand expectedCommand = new SortCommand(descriptionComparator, amountComparator);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
