package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_ADDRESS_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_EMAIL_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_NAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_PHONE_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_B;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalPersons.DESC_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.EditTeammateCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditTeammateDescriptorBuilder;

public class EditTeammateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeammateCommand.MESSAGE_USAGE);

    private EditTeammateCommandParser parser = new EditTeammateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // empty user input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no correct GitUserIndex or fields
        assertParseFailure(parser, "%", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "$% ^" + VALID_TEAMMATE_GIT_USERNAME_A, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "mn/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_NAME_DESC_A,
            PersonName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_PHONE_DESC_A,
            Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_EMAIL_DESC_A,
            Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_ADDRESS_DESC_A,
            Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser,
            VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_PHONE_DESC_A + TEAMMATE_EMAIL_DESC_A,
            Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone.
        assertParseFailure(parser, VALID_TEAMMATE_GIT_USERNAME_A + TEAMMATE_PHONE_DESC_A
            + INVALID_TEAMMATE_PHONE_DESC_A, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
            VALID_TEAMMATE_GIT_USERNAME_A + INVALID_TEAMMATE_PHONE_DESC_A + INVALID_TEAMMATE_EMAIL_DESC_A
                + INVALID_TEAMMATE_ADDRESS_DESC_A, Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        GitUserIndex targetIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        String userInput = targetIndex.getGitUserNameString()
            + TEAMMATE_NAME_DESC_B + TEAMMATE_PHONE_DESC_B + TEAMMATE_ADDRESS_DESC_B + TEAMMATE_EMAIL_DESC_B;

        EditTeammateCommand.EditTeammateDescriptor descriptor =
            new EditTeammateDescriptorBuilder(DESC_A)
                .withTeammatetName(VALID_TEAMMATE_NAME_B)
                .withPhone(VALID_TEAMMATE_PHONE_B)
                .withEmail(VALID_TEAMMATE_EMAIL_B)
                .withAddress(VALID_TEAMMATE_ADDRESS_B).build();

        EditTeammateCommand expectedCommand = new EditTeammateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        GitUserIndex targetIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        String userInput = targetIndex.getGitUserNameString()
            + TEAMMATE_ADDRESS_DESC_B + TEAMMATE_EMAIL_DESC_B;

        EditTeammateCommand.EditTeammateDescriptor descriptor =
            new EditTeammateDescriptorBuilder()
                .withEmail(VALID_TEAMMATE_EMAIL_B)
                .withAddress(VALID_TEAMMATE_ADDRESS_B).build();

        EditTeammateCommand expectedCommand = new EditTeammateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {

        GitUserIndex gitUserIndex;
        String userInput;
        EditTeammateCommand.EditTeammateDescriptor descriptor;
        EditTeammateCommand expectedCommand;

        // teammateName
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + TEAMMATE_NAME_DESC_B;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withTeammatetName(VALID_TEAMMATE_NAME_B).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + TEAMMATE_PHONE_DESC_B;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withPhone(VALID_TEAMMATE_PHONE_B).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + TEAMMATE_EMAIL_DESC_B;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withEmail(VALID_TEAMMATE_EMAIL_B).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + TEAMMATE_ADDRESS_DESC_B;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withAddress(VALID_TEAMMATE_ADDRESS_B).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsAcceptsLast_success() {
        GitUserIndex targetIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        String userInput = targetIndex.getGitUserNameString()
            + TEAMMATE_ADDRESS_DESC_A + TEAMMATE_EMAIL_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_ADDRESS_DESC_B + TEAMMATE_EMAIL_DESC_B + TEAMMATE_PHONE_DESC_B;

        EditTeammateCommand.EditTeammateDescriptor descriptor =
            new EditTeammateDescriptorBuilder()
                .withEmail(VALID_TEAMMATE_EMAIL_B)
                .withAddress(VALID_TEAMMATE_ADDRESS_B)
                .withPhone(VALID_TEAMMATE_PHONE_B).build();

        EditTeammateCommand expectedCommand = new EditTeammateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {

        GitUserIndex gitUserIndex;
        String userInput;
        EditTeammateCommand.EditTeammateDescriptor descriptor;
        EditTeammateCommand expectedCommand;

        // other valid values specified
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + INVALID_TEAMMATE_NAME_DESC_A + TEAMMATE_NAME_DESC_A;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withTeammatetName(VALID_TEAMMATE_NAME_A).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        gitUserIndex = GIT_USERINDEX_FIRST_TEAMMATE;
        userInput = gitUserIndex.getGitUserNameString() + INVALID_TEAMMATE_NAME_DESC_A + TEAMMATE_NAME_DESC_A
            + TEAMMATE_PHONE_DESC_A;
        descriptor =
            new EditTeammateDescriptorBuilder()
                .withTeammatetName(VALID_TEAMMATE_NAME_A)
                .withPhone(VALID_TEAMMATE_PHONE_A).build();
        expectedCommand = new EditTeammateCommand(gitUserIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }
}
