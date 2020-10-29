package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAccountNameCommand;
import seedu.address.model.account.Name;

class EditAccountNameCommandParserTest {

    private EditAccountNameCommandParser parser = new EditAccountNameCommandParser();
    private Name editedName = new Name("my account");

    @Test
    public void parse_validName_success() {
        assertParseSuccess(parser, " n/my account", new EditAccountNameCommand(editedName));
    }

    @Test
    public void parse_missingName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, " n/", expectedMessage);
    }

    @Test
    public void parse_invalidArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAccountNameCommand.MESSAGE_USAGE);

        //invalid argument
        assertParseFailure(parser, "a", expectedMessage);

        //empty argument
        assertParseFailure(parser, "", expectedMessage);
    }
}
