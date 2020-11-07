package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.logic.commands.CommandTestUtil.FEED_TIME_DESC_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.FEED_TIME_DESC_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_FEED_TIME_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.NAME_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_ARCHIE;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_FEED_TIME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.zookeep.testutil.TypicalAnimals.AHMENG;
import static seedu.zookeep.testutil.TypicalAnimals.BUTTERCUP;
import static seedu.zookeep.testutil.TypicalAnimals.COCO;

import org.junit.jupiter.api.Test;

import seedu.zookeep.logic.commands.AppendCommand;
import seedu.zookeep.logic.commands.EditAnimalDescriptor;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;
import seedu.zookeep.testutil.EditAnimalDescriptorBuilder;


class AppendCommandParserTest {

    private static final String FEED_TIME_EMPTY = " " + PREFIX_FEED_TIME;
    private static final String MEDICAL_CONDITION_EMPTY = " " + PREFIX_MEDICAL_CONDITION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendCommand.MESSAGE_USAGE);

    private AppendCommandParser parser = new AppendCommandParser();

    @Test
    void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ARCHIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "123", AppendCommand.MESSAGE_NOT_APPENDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "123" + INVALID_MEDICAL_CONDITION_DESC,
                MedicalCondition.MESSAGE_CONSTRAINTS); // invalid medicalCondition
        assertParseFailure(parser, "123" + INVALID_FEED_TIME_DESC,
                FeedTime.MESSAGE_CONSTRAINTS); // invalid feedTime

        // while parsing {@code PREFIX_MEDICAL_CONDITION} alone will reset the
        // medicalConditions of the {@code Animal} being edited,
        // parsing it together with a valid medicalCondition results in error
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_DESC_ARTHRITIS + MEDICAL_CONDITION_DESC_OBESE
                + MEDICAL_CONDITION_EMPTY, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_DESC_ARTHRITIS
                + MEDICAL_CONDITION_EMPTY + MEDICAL_CONDITION_DESC_OBESE, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + MEDICAL_CONDITION_EMPTY + MEDICAL_CONDITION_DESC_ARTHRITIS
                + MEDICAL_CONDITION_DESC_OBESE, MedicalCondition.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_FEED_TIME} alone will reset the
        // feedTimes of the {@code Animal} being edited,
        // parsing it together with a valid feedTime results in error
        assertParseFailure(parser, "123" + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING
                + FEED_TIME_EMPTY, FeedTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + FEED_TIME_DESC_MORNING
                + FEED_TIME_EMPTY + FEED_TIME_DESC_EVENING, FeedTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "123" + FEED_TIME_EMPTY + FEED_TIME_DESC_MORNING
                + FEED_TIME_DESC_EVENING, FeedTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Id targetId = BUTTERCUP.getId();
        String userInput = targetId.value + MEDICAL_CONDITION_DESC_OBESE + FEED_TIME_DESC_MORNING
                + MEDICAL_CONDITION_DESC_ARTHRITIS + FEED_TIME_DESC_EVENING;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE)
                .withFeedTimes(VALID_FEED_TIME_MORNING, VALID_FEED_TIME_EVENING).build();
        AppendCommand expectedCommand = new AppendCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // medicalConditions
        Id targetId = COCO.getId();
        String userInput = targetId.value + MEDICAL_CONDITION_DESC_ARTHRITIS;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();
        AppendCommand expectedCommand = new AppendCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // feedTimes
        userInput = targetId.value + FEED_TIME_DESC_MORNING;
        descriptor = new EditAnimalDescriptorBuilder().withFeedTimes(VALID_FEED_TIME_MORNING).build();
        expectedCommand = new AppendCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // empty feedTimes, followed by valid medicalConditions
        Id targetId = AHMENG.getId();
        String userInput = targetId.value + FEED_TIME_EMPTY
                + MEDICAL_CONDITION_DESC_ARTHRITIS + MEDICAL_CONDITION_DESC_OBESE;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE)
                .withFeedTimes().build();
        AppendCommand expectedCommand = new AppendCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty medicalConditions, followed by valid feedTimes
        userInput = targetId.value + MEDICAL_CONDITION_EMPTY + FEED_TIME_DESC_MORNING
                + FEED_TIME_DESC_EVENING;
        descriptor = new EditAnimalDescriptorBuilder()
                .withMedicalConditions()
                .withFeedTimes(VALID_FEED_TIME_MORNING, VALID_FEED_TIME_EVENING).build();
        expectedCommand = new AppendCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
