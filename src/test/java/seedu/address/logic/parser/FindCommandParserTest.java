package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.expense.DateMatchesPredicate;
import seedu.address.model.expense.NameContainsKeywordsPredicate;
import seedu.address.model.expense.TagsMatchesPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                new DateMatchesPredicate("07-09-2020"),
                new TagsMatchesPredicate(Arrays.asList("crypto", "cs"))
        );
        String x = "-d Alice Bob -@ 07-09-2020 t/ crypto t/ cs";
        assertParseSuccess(parser, x, expectedFindCommand);

        // multiple whitespaces between keywords
        String y = "\n-d Alice \n \t Bob \n \t -@07-09-2020 t/ crypto t/ cs";
        assertParseSuccess(parser, y, expectedFindCommand);
    }

}
