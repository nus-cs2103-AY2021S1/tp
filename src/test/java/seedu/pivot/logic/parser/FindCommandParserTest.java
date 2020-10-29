package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.FindCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.DetailsContainsKeywordsPredicate;

public class FindCommandParserTest {

    private static Index index = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parseCasePage_emptyArg_throwsParseException() {
        StateManager.setState(index);
        assertParseFailure(parser, "     ", MESSAGE_INCORRECT_MAIN_PAGE);
        StateManager.resetState();
    }

    @Test
    public void parseCasePage_validArgs_throwsParseException() {
        StateManager.setState(index);
        assertParseFailure(parser, "     ", MESSAGE_INCORRECT_MAIN_PAGE);
        StateManager.resetState();
    }

    @Test
    public void parseMainPage_emptyArg_throwsParseException() {
        StateManager.resetState();
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseMainPage_validArgs_returnsFindCommand() {
        StateManager.resetState();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new DetailsContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
