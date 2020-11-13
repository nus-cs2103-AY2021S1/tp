package seedu.jarvis.logic.parser;

import static seedu.jarvis.logic.commands.CommandTestUtil.EDIT_STUDENT;
import static seedu.jarvis.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.jarvis.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.edit.EditStudentCommand;
import seedu.jarvis.logic.commands.edit.EditStudentCommand.EditPersonDescriptor;
import seedu.jarvis.model.student.Email;
import seedu.jarvis.model.student.Name;
import seedu.jarvis.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            "The provided index is not valid as it is out of the accepted "
                    + "range. To obtain the valid index, use the view command and take note of the "
                    + "leftmost identifier of each item in the list.";

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, EDIT_STUDENT + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, EDIT_STUDENT + "1", EditStudentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, EDIT_STUDENT + "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, EDIT_STUDENT + "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, EDIT_STUDENT + "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, EDIT_STUDENT + "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, EDIT_STUDENT + "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, EDIT_STUDENT + "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, EDIT_STUDENT + "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, EDIT_STUDENT + "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = EDIT_STUDENT + targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + NAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = EDIT_STUDENT + targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = EDIT_STUDENT + targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = EDIT_STUDENT + targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EDIT_STUDENT + targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = EDIT_STUDENT + targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = EDIT_STUDENT + targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EDIT_STUDENT + targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
