package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import static seedu.fma.logic.commands.CommandTestUtil.*;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_THIRD_LOG;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.EditCommand.EditLogDescriptor;
import seedu.fma.logic.parser.EditCommandParser;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.log.Comment;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.EditLogDescriptorBuilder;

import org.junit.jupiter.api.Test;

public class EditCommandParserTest {

//    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, REP_DESC_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REP_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REP_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC, Name.MESSAGE_CONSTRAINTS); // invalid exercise
        assertParseFailure(parser, "1" + INVALID_REP_DESC, Rep.MESSAGE_CONSTRAINTS); // invalid rep

        // invalid exercise followed by valid rep
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + INVALID_REP_DESC, Name.MESSAGE_CONSTRAINTS);

        // valid exercise followed by invalid exercise. The test case for invalid exercise followed by valid exercise
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + EXERCISE_DESC_A + INVALID_EXERCISE_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + INVALID_REP_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A
                + COMMENT_DESC_A;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A)
                .withReps(VALID_REP_A).withComment(VALID_COMMENT_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A)
                .withReps(VALID_REP_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // exercise
        Index targetIndex = INDEX_THIRD_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A;
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // reps
        userInput = targetIndex.getOneBased() + REP_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withReps(VALID_REP_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + COMMENT_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withComment(VALID_COMMENT_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A
                + EXERCISE_DESC_B + REP_DESC_B + COMMENT_DESC_B;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_B)
                .withReps(VALID_REP_B).withComment(VALID_COMMENT_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + INVALID_EXERCISE_DESC + EXERCISE_DESC_A;
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COMMENT_DESC_A + INVALID_EXERCISE_DESC + EXERCISE_DESC_A
                + REP_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).withComment(VALID_COMMENT_A)
                .withReps(VALID_REP_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
