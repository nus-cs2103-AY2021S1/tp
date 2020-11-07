package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.CHARLIE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(CHARLIE).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE
                + EMAIL_DESC_CHARLIE + STUDENT_ID_DESC_CHARLIE + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ALEX + NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_ALEX + PHONE_DESC_CHARLIE
                + EMAIL_DESC_CHARLIE + STUDENT_ID_DESC_CHARLIE + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_ALEX + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple student IDs - last id accepted
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_ALEX + STUDENT_ID_DESC_CHARLIE + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(CHARLIE).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(CHARLIE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE, new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                        + STUDENT_ID_DESC_CHARLIE, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_CHARLIE + VALID_PHONE_CHARLIE + EMAIL_DESC_CHARLIE
                        + STUDENT_ID_DESC_CHARLIE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + VALID_EMAIL_CHARLIE
                        + STUDENT_ID_DESC_CHARLIE, expectedMessage);

        // missing student id prefix
        assertParseFailure(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                        + VALID_STUDENT_ID_CHARLIE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_CHARLIE + VALID_PHONE_CHARLIE + VALID_EMAIL_CHARLIE
                        + VALID_STUDENT_ID_CHARLIE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_CHARLIE + INVALID_PHONE_DESC + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + INVALID_EMAIL_DESC
                + STUDENT_ID_DESC_CHARLIE + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + INVALID_STUDENT_ID_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentId.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                + STUDENT_ID_DESC_CHARLIE + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                        + INVALID_STUDENT_ID_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE
                        + EMAIL_DESC_CHARLIE + STUDENT_ID_DESC_CHARLIE + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
