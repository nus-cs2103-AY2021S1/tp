package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.commands.CommandTestUtil.COMMENT_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.COMMENT_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_EXERCISE_DESC;
import static seedu.fma.logic.commands.CommandTestUtil.INVALID_REP_DESC;
import static seedu.fma.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.fma.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.fma.logic.commands.CommandTestUtil.REP_DESC_A;
import static seedu.fma.logic.commands.CommandTestUtil.REP_DESC_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_JUMPING_JACKS;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_LOG_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fma.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.AddCommand;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.LogBuilder;


public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();
    private final Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.of("GMT+8"));

    @Test
    public void parse_allFieldsPresent_success() {
        Log expectedLog = new LogBuilder(VALID_LOG_A).withDateTime(LocalDateTime.now(fixedClock)).build();
        Log.setUserClock(fixedClock);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A,
                new AddCommand(expectedLog));

        // multiple exercises - last exercise accepted
        assertParseSuccess(parser, EXERCISE_DESC_B + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A,
                new AddCommand(expectedLog));

        // multiple reps - last rep accepted
        assertParseSuccess(parser, EXERCISE_DESC_A + REP_DESC_B + REP_DESC_A + COMMENT_DESC_A,
                new AddCommand(expectedLog));

        // multiple comments - last comment accepted
        assertParseSuccess(parser, EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_B + COMMENT_DESC_A,
                new AddCommand(expectedLog));
    }

    // TODO Uncomment this test once we make comments optional.
    /*
    @Test
    public void parse_optionalFieldsMissing_success() {
        Log expectedLog = new LogBuilder(VALID_LOG_A).withDateTime(LocalDateTime.now(fixedClock)).build();
        Log.setUserClock(fixedClock);

        // no comments
        assertParseSuccess(parser, EXERCISE_DESC_A + REP_DESC_A,
                new AddCommand(expectedLog));
    }
    */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing exercise prefix
        assertParseFailure(parser, VALID_EXERCISE_JUMPING_JACKS + REP_DESC_A + COMMENT_DESC_A,
                expectedMessage);

        // missing rep prefix
        assertParseFailure(parser, EXERCISE_DESC_A + VALID_REP_A + COMMENT_DESC_A,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, EXERCISE_DESC_A + VALID_REP_A + VALID_COMMENT_A,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid exercise name
        assertParseFailure(parser, INVALID_EXERCISE_DESC + REP_DESC_A + COMMENT_DESC_A,
                Name.MESSAGE_CONSTRAINTS);

        // invalid rep
        assertParseFailure(parser, EXERCISE_DESC_A + INVALID_REP_DESC + COMMENT_DESC_A,
                Rep.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_EXERCISE_DESC + INVALID_REP_DESC + COMMENT_DESC_A,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EXERCISE_DESC_A + REP_DESC_A + COMMENT_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
