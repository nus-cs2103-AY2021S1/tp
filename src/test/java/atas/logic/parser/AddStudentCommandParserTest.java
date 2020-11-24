package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_MATRICULATION_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static atas.logic.commands.CommandTestUtil.MATRICULATION_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.MATRICULATION_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static atas.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static atas.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static atas.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_MATRICULATION_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static atas.logic.commands.studentlist.AddStudentCommand.MESSAGE_USAGE;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static atas.testutil.TypicalStudents.AMY;
import static atas.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.AddStudentCommand;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;
import atas.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_AMY + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + MATRICULATION_DESC_AMY + EMAIL_DESC_AMY,
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRICULATION_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_MATRICULATION_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MATRICULATION_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Matriculation.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MATRICULATION_DESC_BOB
                        + EMAIL_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
