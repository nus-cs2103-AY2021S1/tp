package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.person.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.person.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.person.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.person.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.person.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.person.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.person.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.person.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.parser.person.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.person.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.AddCommand;
import seedu.address.model.person.person.Email;
import seedu.address.model.person.person.Name;
import seedu.address.model.person.person.Person;
import seedu.address.model.person.person.Phone;
import seedu.address.model.person.person.Role;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, ROLE_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPersonWithoutPhone = new PersonBuilder(AMY).withPhone().build();
        assertParseSuccess(parser,
                ROLE_DESC_AMY + NAME_DESC_AMY + EMAIL_DESC_AMY + DESCRIPTION_DESC_AMY,
                new AddCommand(expectedPersonWithoutPhone));

        Person expectedPersonWithoutDescription = new PersonBuilder(AMY).withDescription().build();
        assertParseSuccess(parser, ROLE_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedPersonWithoutDescription));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ROLE_DESC_BOB + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + DESCRIPTION_DESC_BOB, expectedMessage);

        // missing role
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_ROLE_BOB + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, ROLE_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ROLE_DESC_BOB + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ROLE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + DESCRIPTION_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB, Role.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ROLE_DESC_BOB + INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // empty preamble
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
