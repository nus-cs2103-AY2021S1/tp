package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.EMPTY_TAG_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.resireg.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.EditCommand;
import seedu.resireg.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.HELP.getFullMessage());

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 j/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_FACULTY_DESC, Faculty.MESSAGE_CONSTRAINTS); // invalid faculty
        assertParseFailure(parser, "1" + INVALID_STUDENT_ID_DESC, StudentId.MESSAGE_CONSTRAINTS); // invalid id
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + EMPTY_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + EMPTY_TAG_DESC + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EMPTY_TAG_DESC + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_FACULTY_AMY
                + VALID_STUDENT_ID_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + FACULTY_DESC_AMY + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withFaculty(VALID_FACULTY_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // faculty
        userInput = targetIndex.getOneBased() + FACULTY_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withFaculty(VALID_FACULTY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + FACULTY_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + FACULTY_DESC_AMY + STUDENT_ID_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + FACULTY_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withFaculty(VALID_FACULTY_BOB).withStudentId(VALID_STUDENT_ID_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + FACULTY_DESC_BOB
                + STUDENT_ID_DESC_BOB + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withFaculty(VALID_FACULTY_BOB).withStudentId(VALID_STUDENT_ID_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + EMPTY_TAG_DESC;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
