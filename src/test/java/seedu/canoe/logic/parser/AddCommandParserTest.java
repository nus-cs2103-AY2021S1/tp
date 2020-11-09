package seedu.canoe.logic.parser;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.commands.CommandTestUtil.ACADEMICYEAR_DESC_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.ACADEMICYEAR_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.FRIDAY_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_ACADEMICYEAR_DESC;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.canoe.logic.commands.CommandTestUtil.MONDAY_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.canoe.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.canoe.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.canoe.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.canoe.logic.commands.CommandTestUtil.THURSDAY_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.TUESDAY_DESC_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.canoe.logic.commands.CommandTestUtil.WEDNESDAY_DESC_BOB;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.canoe.testutil.TypicalStudents.AMY;
import static seedu.canoe.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.AddCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Student expectedStudent =
                new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + ACADEMICYEAR_DESC_BOB + TAG_DESC_FRIEND + MONDAY_DESC_BOB + TUESDAY_DESC_BOB + WEDNESDAY_DESC_BOB
            + THURSDAY_DESC_BOB + FRIDAY_DESC_BOB, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + ACADEMICYEAR_DESC_BOB + TAG_DESC_FRIEND + MONDAY_DESC_BOB + TUESDAY_DESC_BOB + WEDNESDAY_DESC_BOB
            + THURSDAY_DESC_BOB + FRIDAY_DESC_BOB, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + ACADEMICYEAR_DESC_BOB + TAG_DESC_FRIEND + MONDAY_DESC_BOB + TUESDAY_DESC_BOB + WEDNESDAY_DESC_BOB
            + THURSDAY_DESC_BOB + FRIDAY_DESC_BOB, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
            + ACADEMICYEAR_DESC_BOB + TAG_DESC_FRIEND + MONDAY_DESC_BOB + TUESDAY_DESC_BOB + WEDNESDAY_DESC_BOB
            + THURSDAY_DESC_BOB + FRIDAY_DESC_BOB, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ACADEMICYEAR_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MONDAY_DESC_BOB + TUESDAY_DESC_BOB + WEDNESDAY_DESC_BOB
            + THURSDAY_DESC_BOB + FRIDAY_DESC_BOB, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags()
                .build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ACADEMICYEAR_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE);

        // missing id prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ACADEMICYEAR_DESC_BOB,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ACADEMICYEAR_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + ACADEMICYEAR_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + ACADEMICYEAR_DESC_BOB,
                expectedMessage);

        // missing academic year prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + VALID_ACADEMICYEAR_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                        + VALID_EMAIL_BOB + VALID_ACADEMICYEAR_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + ACADEMICYEAR_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + ACADEMICYEAR_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + ACADEMICYEAR_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid academic year
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ACADEMICYEAR_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                AcademicYear.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + ACADEMICYEAR_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ACADEMICYEAR_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ACADEMICYEAR_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
