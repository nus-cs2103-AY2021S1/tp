package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.FindCommand;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_DESCRIPTION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommandParser.MISSING_ARGUMENTS));
    }

    @Test
    public void parse_emptyDate_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_DATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommandParser.MISSING_ARGUMENTS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(
                new DescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                new DateMatchesPredicate(Arrays.asList("07-09-2020"))
        );
        String x = " " + PREFIX_DESCRIPTION + "Alice Bob " + PREFIX_DATE + " 07-09-2020 ";
        assertParseSuccess(parser, x, expectedFindCommand);
    }
}
