package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParsePersonUtil.MESSAGE_INVALID_GIT_INDEX;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.DeletePersonCommand;

public class DeleteTeammateParserTest {

    private DeletePersonCommandParser parser = new DeletePersonCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTeammateCommand() {
        assertParseSuccess(parser, "Sparrow32",
            new DeletePersonCommand(GIT_USERINDEX_FIRST_TEAMMATE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "random input",
            String.format(MESSAGE_INVALID_GIT_INDEX, DeletePersonCommand.MESSAGE_USAGE));
    }
}

