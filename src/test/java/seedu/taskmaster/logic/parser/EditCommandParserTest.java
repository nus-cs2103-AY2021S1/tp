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
import static seedu.taskmaster.logic.commands.CommandTestUtil.NUSNETID_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.NUSNETID_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NUSNETID_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NUSNETID_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskmaster.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.logic.commands.EditCommand;
import seedu.taskmaster.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.taskmaster.model.student.Email;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Telegram;
import seedu.taskmaster.model.tag.Tag;
import seedu.taskmaster.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1 a/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid telegram
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, NusnetId.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid telegram followed by valid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC + EMAIL_DESC_AMY, Telegram.MESSAGE_CONSTRAINTS);

        // valid telegram followed by invalid telegram. The test case for invalid telegram followed by valid telegram
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TELEGRAM_DESC_BOB + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_NUSNETID_AMY + VALID_TELEGRAM_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NUSNETID_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_NUSNETID_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withTelegram(VALID_TELEGRAM_BOB)
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

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + NUSNETID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAddress(VALID_NUSNETID_AMY).build();
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
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + NUSNETID_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + TELEGRAM_DESC_AMY + NUSNETID_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + TELEGRAM_DESC_BOB + NUSNETID_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_NUSNETID_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_TELEGRAM_DESC + TELEGRAM_DESC_BOB;
        EditCommand.EditStudentDescriptor descriptor =
                new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC + NUSNETID_DESC_BOB
                + TELEGRAM_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_NUSNETID_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
