package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.suspectcommands.AddSuspectCommand;
import seedu.address.model.investigationcase.Name;
import seedu.address.model.investigationcase.Suspect;

public class AddSuspectCommandParserTest {
    // Todo: move static fields to CommandTestUtil
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&";

    private AddSuspectCommandParser parser = new AddSuspectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Suspect expectedSuspect = new Suspect(new Name(VALID_NAME_BOB));

        // normal input
        assertParseSuccess(parser, NAME_DESC_BOB,
                new AddSuspectCommand(expectedSuspect));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB,
                new AddSuspectCommand(expectedSuspect));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB,
                new AddSuspectCommand(expectedSuspect));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE));
    }
}
