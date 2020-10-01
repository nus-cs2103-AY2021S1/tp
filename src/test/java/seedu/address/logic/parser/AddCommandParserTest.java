package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_WASH;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_WASH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_WASH;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_WASH;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.COOK;
import static seedu.address.testutil.TypicalTasks.WASH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Address;
import seedu.address.model.task.Email;
import seedu.address.model.task.Title;
import seedu.address.model.task.Phone;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(WASH).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH
                + ADDRESS_DESC_WASH + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_COOK + TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH
                + ADDRESS_DESC_WASH + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, TITLE_DESC_WASH + PHONE_DESC_COOK + PHONE_DESC_WASH + EMAIL_DESC_WASH
                + ADDRESS_DESC_WASH + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple emails - last email accepted
        assertParseSuccess(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_COOK + EMAIL_DESC_WASH
                + ADDRESS_DESC_WASH + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_COOK
                + ADDRESS_DESC_WASH + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(WASH).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_WASH
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(COOK).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_COOK + PHONE_DESC_COOK + EMAIL_DESC_COOK + ADDRESS_DESC_COOK,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_WASH,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, TITLE_DESC_WASH + VALID_PHONE_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_WASH,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + VALID_EMAIL_WASH + ADDRESS_DESC_WASH,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + VALID_ADDRESS_WASH,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_WASH + VALID_PHONE_WASH + VALID_EMAIL_WASH + VALID_ADDRESS_WASH,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + PHONE_DESC_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_WASH
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Title.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, TITLE_DESC_WASH + INVALID_PHONE_DESC + EMAIL_DESC_WASH + ADDRESS_DESC_WASH
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + INVALID_EMAIL_DESC + ADDRESS_DESC_WASH
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH + ADDRESS_DESC_WASH
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + PHONE_DESC_WASH + EMAIL_DESC_WASH + INVALID_ADDRESS_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_WASH + PHONE_DESC_WASH + EMAIL_DESC_WASH
                + ADDRESS_DESC_WASH + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
