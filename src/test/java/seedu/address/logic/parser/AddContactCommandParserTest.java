package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddContactCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddContactCommandParserTest {
    private final AddContactCommandParser parser = new AddContactCommandParser();
    private final String commandWord = "addcontact";

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should add person successfully if all required fields "
                + "are present")
        public void parse_allFieldsPresent_success() {
            UUID uuid = UUID.randomUUID();
            AddContactCommandParser parser =
                    new AddContactCommandParser(uuid);
            Person expectedPerson = new PersonBuilder(BOB)
                    .withTags(VALID_TAG_FRIEND)
                    .build(uuid);

            // whitespace only preamble
            assertParseSuccess(
                    parser,
                    PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                            + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );

            // multiple names - last name accepted
            assertParseSuccess(
                    parser,
                    NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                            + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );

            // multiple phones - last phone accepted
            assertParseSuccess(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                            + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );

            // multiple emails - last email accepted
            assertParseSuccess(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                            + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );

            // multiple addresses - last address accepted
            assertParseSuccess(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + ADDRESS_DESC_AMY
                            + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );

            // multiple tags - all accepted
            Person expectedPersonMultipleTags = new PersonBuilder(BOB)
                    .withTags(
                            VALID_TAG_FRIEND,
                            VALID_TAG_HUSBAND
                    )
                    .build(uuid);
            assertParseSuccess(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB
                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    new AddContactCommand(expectedPersonMultipleTags),
                    commandWord
            );
        }

        @Test
        @DisplayName("should add person successfully even if optional fields "
                + "are missing")
        public void parse_optionalFieldsMissing_success() {
            UUID uuid = UUID.randomUUID();
            AddContactCommandParser parser =
                    new AddContactCommandParser(uuid);
            // zero tags
            Person expectedPerson = new PersonBuilder(AMY)
                    .withTags()
                    .build(uuid);
            assertParseSuccess(
                    parser,
                    NAME_DESC_AMY
                            + PHONE_DESC_AMY
                            + EMAIL_DESC_AMY
                            + ADDRESS_DESC_AMY,
                    new AddContactCommand(expectedPerson),
                    commandWord
            );
        }

        @Test
        @DisplayName("should fail to add person if compulsory fields are "
                + "missing")
        public void parse_compulsoryFieldMissing_failure() {
            String expectedMessage = String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.getMessageUsage(commandWord)
            );

            // missing name prefix
            assertParseFailure(parser,
                    VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB,
                    expectedMessage,
                    commandWord
            );

            // all prefixes missing
            assertParseFailure(parser,
                    VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                            + VALID_ADDRESS_BOB,
                    expectedMessage,
                    commandWord
            );
        }

        @Test
        @DisplayName("should no add person if there are invalid values in the"
                + " input")
        public void parse_invalidValue_failure() {
            // invalid name
            assertParseFailure(
                    parser,
                    INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB
                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    Name.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // invalid phone
            assertParseFailure(
                    parser,
                    NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB
                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    Phone.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // invalid email
            assertParseFailure(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                            + ADDRESS_DESC_BOB
                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    Email.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // invalid address
            assertParseFailure(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + INVALID_ADDRESS_DESC
                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    Address.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // invalid tag
            assertParseFailure(
                    parser,
                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB
                            + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                    Tag.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // two invalid values, only first invalid value reported
            assertParseFailure(parser,
                    INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                            + INVALID_ADDRESS_DESC,
                    Name.MESSAGE_CONSTRAINTS,
                    commandWord
            );

            // non-empty preamble
            assertParseFailure(parser,
                    PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                            + EMAIL_DESC_BOB
                            + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                            + TAG_DESC_FRIEND,
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            AddContactCommand.getMessageUsage(commandWord)
                    ),
                    commandWord
            );
        }
    }
}
