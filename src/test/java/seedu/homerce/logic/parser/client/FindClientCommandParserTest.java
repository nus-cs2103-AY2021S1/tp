package seedu.homerce.logic.parser.client;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.commons.core.Messages.MESSAGE_MULTIPLE_PARAMETERS;
import static seedu.homerce.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.homerce.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.client.FindClientCommand;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.client.predicate.ClientNamePredicate;
import seedu.homerce.model.client.predicate.ClientPhonePredicate;

public class FindClientCommandParserTest {

    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Name field
        ClientNamePredicate namePredicate = new ClientNamePredicate(Arrays.asList("Amy", "Bee"));

        assertParseSuccess(parser, NAME_DESC_AMY,
                new FindClientCommand(namePredicate));

        // Phone field
        ClientPhonePredicate phonePredicate = new ClientPhonePredicate(new Phone("11111111"));

        assertParseSuccess(parser, PHONE_DESC_AMY,
                new FindClientCommand(phonePredicate));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, VALID_PHONE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleFields_failure() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PARAMETERS);

        assertParseFailure(parser, PHONE_DESC_AMY + NAME_DESC_AMY, expectedMessage);
    }
}
