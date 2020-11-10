package seedu.momentum.logic.parser;

import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.momentum.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.momentum.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.momentum.testutil.TypicalProjects.getTypicalProjectBook;

import org.junit.jupiter.api.Test;

import seedu.momentum.logic.commands.ShowComponentCommand;
import seedu.momentum.model.Model;
import seedu.momentum.model.ModelManager;
import seedu.momentum.model.UserPrefs;

public class ShowComponentCommandParserTest {
    private static final ShowComponentCommandParser parser = new ShowComponentCommandParser();
    private static final Model model = new ModelManager(getTypicalProjectBook(), new UserPrefs());

    @Test
    public void parse_validArgs_returnsShowComponentCommand() {
        String userInput = ShowComponentCommand.COMMAND_WORD + " " + PREFIX_TAG;
        ShowComponentCommand command = new ShowComponentCommand(ShowComponentCommandParser.ComponentType.TAGS);
        assertParseSuccess(parser, userInput, command, model);

        userInput += " " + PREFIX_TAG;
        command = new ShowComponentCommand(ShowComponentCommandParser.ComponentType.TAGS);
        assertParseSuccess(parser, userInput, command, model);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = "asd";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowComponentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage, model);
    }
}
