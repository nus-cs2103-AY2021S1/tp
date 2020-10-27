package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENTSOURCE_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.CLIENTSOURCE_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENTSOURCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_CAT;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_DOG;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTSOURCE_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTSOURCE_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withClientSources(VALID_CLIENTSOURCE_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                + PRIORITY_DESC_BOB, new AddCommand(expectedPerson, null));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                + PRIORITY_DESC_BOB, new AddCommand(expectedPerson, null));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                + PRIORITY_DESC_BOB, new AddCommand(expectedPerson, null));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                + PRIORITY_DESC_BOB, new AddCommand(expectedPerson, null));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                + PRIORITY_DESC_BOB, new AddCommand(expectedPerson, null));

        // multiple clientsources - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withClientSources(VALID_CLIENTSOURCE_FRIEND,
                VALID_CLIENTSOURCE_HUSBAND)
                .withNote(VALID_NOTE_DOG).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + CLIENTSOURCE_DESC_FRIEND + CLIENTSOURCE_DESC_HUSBAND + NOTE_DESC_DOG + PRIORITY_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags, null));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // missing phone prefix
        Person expectedPersonWithoutPhone = new PersonBuilder(AMY).withoutPhone().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_CAT + PRIORITY_DESC_AMY,
                new AddCommand(expectedPersonWithoutPhone, null));

        // missing email prefix
        Person expectedPersonWithoutEmail = new PersonBuilder(AMY).withoutEmail().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                        + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_CAT + PRIORITY_DESC_AMY,
                new AddCommand(expectedPersonWithoutEmail, null));

        // missing address prefix
        Person expectedPersonWithoutAddress = new PersonBuilder(AMY).withoutAddress().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_CAT + PRIORITY_DESC_AMY,
                new AddCommand(expectedPersonWithoutAddress, null));

        // zero clientSources
        Person expectedPerson = new PersonBuilder(AMY).withClientSources().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + NOTE_DESC_CAT + PRIORITY_DESC_AMY,
                new AddCommand(expectedPerson, null));

        // missing note prefix
        Person expectedPersonWithoutNote = new PersonBuilder(AMY).withoutNote().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + CLIENTSOURCE_DESC_FRIEND + PRIORITY_DESC_AMY,
                new AddCommand(expectedPersonWithoutNote, null));

        // missing priority prefix - should default to undefined
        Person expectedPersonWithoutPriority = new PersonBuilder(AMY).withoutPriority().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_CAT,
                new AddCommand(expectedPersonWithoutPriority, null));

        // missing priority prefix
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_CAT,
                new AddCommand(expectedPersonWithoutPriority, null));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + NOTE_DESC_DOG + CLIENTSOURCE_DESC_HUSBAND
                        + PRIORITY_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_NOTE_DOG + CLIENTSOURCE_DESC_HUSBAND
                        + PRIORITY_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + CLIENTSOURCE_DESC_HUSBAND + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG + PRIORITY_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + CLIENTSOURCE_DESC_HUSBAND + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG + PRIORITY_DESC_BOB ,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                        + CLIENTSOURCE_DESC_HUSBAND + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG + PRIORITY_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + CLIENTSOURCE_DESC_HUSBAND + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG + PRIORITY_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid clientsource
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_CLIENTSOURCE_DESC + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG + PRIORITY_DESC_BOB,
                ClientSource.MESSAGE_CONSTRAINTS);

        //invalid note
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_CLIENTSOURCE_FRIEND + INVALID_NOTE_DESC + PRIORITY_DESC_BOB, Note.MESSAGE_CONSTRAINTS);

        //invalid priority
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_CLIENTSOURCE_FRIEND + NOTE_DESC_DOG + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + CLIENTSOURCE_DESC_HUSBAND + CLIENTSOURCE_DESC_FRIEND + NOTE_DESC_DOG
                        + PRIORITY_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
