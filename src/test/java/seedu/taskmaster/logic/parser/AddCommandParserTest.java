package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.taskmaster.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.taskmaster.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.taskmaster.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.taskmaster.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NUSNETID_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NUSNETID_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NUSNETID_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskmaster.testutil.TypicalStudents.AMY;
import static seedu.taskmaster.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.logic.commands.AddCommand;
import seedu.taskmaster.model.student.Email;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.Telegram;
import seedu.taskmaster.model.tag.Tag;
import seedu.taskmaster.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + NUSNETID_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + NUSNETID_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple telegrams - last telegram accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_AMY + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + NUSNETID_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + NUSNETID_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_AMY
                + NUSNETID_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY + NUSNETID_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_BOB,
                expectedMessage);

        // missing telegram prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_TELEGRAM_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + VALID_EMAIL_BOB + NUSNETID_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + VALID_NUSNETID_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_TELEGRAM_BOB + VALID_EMAIL_BOB + VALID_NUSNETID_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_TELEGRAM_DESC + EMAIL_DESC_BOB + NUSNETID_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Telegram.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + INVALID_EMAIL_DESC + NUSNETID_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, NusnetId.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + NUSNETID_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + NUSNETID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
