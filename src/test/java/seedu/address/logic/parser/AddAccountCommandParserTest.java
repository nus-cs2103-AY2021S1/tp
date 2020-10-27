package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAccountCommand;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.testutil.TypicalEntries;

class AddAccountCommandParserTest {

    private AddAccountCommandParser parser = new AddAccountCommandParser();
    private Account accountStub = TypicalEntries.getTypicalAccount();

    @Test
    public void parse_validAccount_success() {
        String userInput = " n/" + accountStub.getName();
        assertParseSuccess(parser, userInput, new AddAccountCommand(accountStub));
    }

    @Test
    public void parse_invalidInput_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAccountCommand.MESSAGE_USAGE);

        //incorrect format
        assertParseFailure(parser, "General Account", expectedMessage);

        //no arguments
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_missingName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, " n/", expectedMessage);
    }
}
