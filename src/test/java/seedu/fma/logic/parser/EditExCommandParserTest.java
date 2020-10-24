package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.commands.CommandTestUtil.CALORIES_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.CALORIES_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_EXERCISE_DESC;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_CALORIES_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_CALORIES_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_B;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditExCommand;
import seedu.fma.model.LogBook;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.EditExDescriptorBuilder;



public class EditExCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExCommand.MESSAGE_USAGE);

    private final LogBook logBook = new LogBook();

    private EditExCommandParser parser = new EditExCommandParser();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CALORIES_DESC_B, MESSAGE_INVALID_FORMAT, logBook);

        // no field specified
        assertParseFailure(parser, "1", EditExCommand.MESSAGE_NOT_EDITED, logBook);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT, logBook);
    }


    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EXERCISE_DESC_A, MESSAGE_INVALID_FORMAT, logBook);

        // zero index
        assertParseFailure(parser, "0" + EXERCISE_DESC_B, MESSAGE_INVALID_FORMAT, logBook);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT, logBook);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT, logBook);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT, logBook);
    }


    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC, Name.MESSAGE_CONSTRAINTS, logBook); // invalid exercise
        assertParseFailure(parser, "1" + INVALID_CALORIES_DESC_A, Calories.MESSAGE_CONSTRAINTS, logBook); // invalid rep

        // invalid exercise followed by valid rep
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + CALORIES_DESC_A,
                Name.MESSAGE_CONSTRAINTS, logBook);

        // valid exercise followed by invalid exercise. The test case for invalid exercise followed by valid exercise
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + EXERCISE_DESC_A + INVALID_EXERCISE_DESC,
                Name.MESSAGE_CONSTRAINTS, logBook);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EXERCISE_DESC + INVALID_CALORIES_DESC_B,
                Name.MESSAGE_CONSTRAINTS, logBook);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + CALORIES_DESC_A;

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_A.getName())
                .withCaloriesPerRep(VALID_CALORIES_A).build();
        EditExCommand expectedCommand = new EditExCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        //name
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A;

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_A.getName()).build();
        EditExCommand expectedCommand = new EditExCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);

        //calories
        userInput = targetIndex.getOneBased() + CALORIES_DESC_A;

        descriptor = new EditExDescriptorBuilder()
                .withCaloriesPerRep(VALID_CALORIES_A).build();
        expectedCommand = new EditExCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_A + CALORIES_DESC_A + EXERCISE_DESC_B
                + EXERCISE_DESC_B + CALORIES_DESC_B;

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_B.getName())
                .withCaloriesPerRep(VALID_CALORIES_B).build();
        EditExCommand expectedCommand = new EditExCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + INVALID_CALORIES_DESC_A + CALORIES_DESC_A + EXERCISE_DESC_B
                + EXERCISE_DESC_B + CALORIES_DESC_B;

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_B.getName())
                .withCaloriesPerRep(VALID_CALORIES_B).build();
        EditExCommand expectedCommand = new EditExCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand, logBook);
    }
}
