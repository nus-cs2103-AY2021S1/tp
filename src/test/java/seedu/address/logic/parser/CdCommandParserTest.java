package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.USER_DIRECTORY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ABSOLUTE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHILD_FOLDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHILD_PATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddressType;
import seedu.address.logic.commands.CdCommand;

public class CdCommandParserTest {
    private CdCommandParser parser = new CdCommandParser();

    @Test
    public void parse_invalidInput_throwsParseException() {

        // all fields missing
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE));

        // has preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE));

        // has more than 1 prefix
        assertParseFailure(parser, " " + PREFIX_CHILD_PATH + " " + PREFIX_PARENT_PATH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE));

        // go to parent path with argument
        assertParseFailure(parser, " " + PREFIX_PARENT_PATH + "noArg", CdCommand.MESSAGE_PARENT_PATH_NOT_BLANK);

        // go to child with no argument
        assertParseFailure(parser, " " + PREFIX_CHILD_PATH, CdCommand.MESSAGE_BLANK_PATH);

        // using absolute path with no argument
        assertParseFailure(parser, " " + PREFIX_FILE_ADDRESS, CdCommand.MESSAGE_BLANK_PATH);
    }

    @Test
    public void parse_validArg_success() {
        // Using child path
        String userInput1 = VALID_CHILD_PATH;
        CdCommand cdCommandChild = new CdCommand(AddressType.CHILD, VALID_CHILD_FOLDER);
        assertParseSuccess(parser, userInput1, cdCommandChild);

        // Go to parent
        String userInput2 = VALID_PARENT_PATH;
        CdCommand cdCommandParent = new CdCommand(AddressType.PARENT, "");
        assertParseSuccess(parser, userInput2, cdCommandParent);

        // Using absolute path
        String userInput3 = VALID_ABSOLUTE_PATH;
        CdCommand cdCommandAbsolute = new CdCommand(AddressType.ABSOLUTE, USER_DIRECTORY_ADDRESS);
        assertParseSuccess(parser, userInput3, cdCommandAbsolute);
    }
}
