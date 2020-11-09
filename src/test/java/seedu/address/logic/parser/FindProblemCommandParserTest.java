package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindProblemCommand;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;

public class FindProblemCommandParserTest {

    private FindProblemCommandParser parser = new FindProblemCommandParser();

    @Test
    public void parse_emptyArgument_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProblemCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArguments_returnsFindCommand() {
        FindProblemCommand expectedCommand =
                new FindProblemCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("table", "chair")));
        assertParseSuccess(parser, "  table  \t chair  \t", expectedCommand);
    }

}
