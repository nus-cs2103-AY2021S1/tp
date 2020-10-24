package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.commands.CommandTestUtil.COMMENT_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.COMMENT_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_EXERCISE_DESC;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_REP_DESC;
import static seedu.fma.logic.commands.CommandTestUtil.REP_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.REP_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_B_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_B_STR;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_THIRD_LOG;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.EditCommand.EditLogDescriptor;
import seedu.fma.model.LogBook;
import seedu.fma.model.log.Rep;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.EditLogDescriptorBuilder;





public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, REP_DESC_A, MESSAGE_INVALID_FORMAT, logBook);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED, logBook);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT, logBook);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REP_DESC_A, MESSAGE_INVALID_FORMAT, logBook);

        // zero index
        assertParseFailure(parser, "0" + REP_DESC_A, MESSAGE_INVALID_FORMAT, logBook);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT, logBook);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT, logBook);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC, Name.MESSAGE_CONSTRAINTS, logBook); // invalid exercise
        assertParseFailure(parser, "1" + INVALID_REP_DESC, Rep.MESSAGE_CONSTRAINTS, logBook); // invalid rep

        // invalid exercise followed by valid rep
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + INVALID_REP_DESC, Name.MESSAGE_CONSTRAINTS, logBook);

        // valid exercise followed by invalid exercise. The test case for invalid exercise followed by valid exercise
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + EXERCISE_DESC_A + INVALID_EXERCISE_DESC, Name.MESSAGE_CONSTRAINTS, logBook);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + INVALID_REP_DESC,
                Name.MESSAGE_CONSTRAINTS, logBook);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A
                + COMMENT_DESC_A;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A)
                .withReps(VALID_REP_A_STR).withComment(VALID_COMMENT_A_STR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A)
                .withReps(VALID_REP_A_STR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // exercise
        Index targetIndex = INDEX_THIRD_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A;
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand, logBook);

        // reps
        userInput = targetIndex.getOneBased() + REP_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withReps(VALID_REP_A_STR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand, logBook);

        // email
        userInput = targetIndex.getOneBased() + COMMENT_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withComment(VALID_COMMENT_A_STR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A
                + EXERCISE_DESC_B + REP_DESC_B + COMMENT_DESC_B;

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_B)
                .withReps(VALID_REP_B_STR).withComment(VALID_COMMENT_B_STR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }


    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_LOG;
        String userInput = targetIndex.getOneBased() + INVALID_EXERCISE_DESC + EXERCISE_DESC_A;
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand, logBook);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COMMENT_DESC_A + INVALID_EXERCISE_DESC + EXERCISE_DESC_A
                + REP_DESC_A;
        descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).withComment(VALID_COMMENT_A_STR)
                .withReps(VALID_REP_A_STR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }
}
