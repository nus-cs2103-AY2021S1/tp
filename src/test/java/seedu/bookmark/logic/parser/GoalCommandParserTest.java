package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.GoalCommand;
import seedu.bookmark.model.book.Goal;

public class GoalCommandParserTest {
    private static final String VALID_PAGE = " p/10";
    private static final String VALID_DEADLINE = " d/20-12-2040";
    private static final String INVALID_PAGE = " p/10b";
    private static final String INVALID_DEADLINE_FORMAT = " d/20-12-20240";
    private static final String VALID_GOAL_COMMAND = " p/10 d/20-12-2040";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE);

    private GoalCommandParser parser = new GoalCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PAGE + VALID_DEADLINE, MESSAGE_INVALID_FORMAT);

        // no deadline specified
        assertParseFailure(parser, "1" + VALID_PAGE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1" + VALID_DEADLINE, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_GOAL_COMMAND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_GOAL_COMMAND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPageDeadline_failure() {
        assertParseFailure(parser, "1" + VALID_PAGE + INVALID_DEADLINE_FORMAT,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Goal.MESSAGE_CONSTRAINTS)); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_PAGE + VALID_DEADLINE,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Goal.MESSAGE_CONSTRAINTS)); // invalid page
        assertParseFailure(parser, "1" + INVALID_PAGE + INVALID_DEADLINE_FORMAT, // invalid page n deadline
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Goal.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validGoalCommand_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + VALID_GOAL_COMMAND;

        GoalCommand expectedCommand = new GoalCommand(targetIndex, new Goal("10 20-12-2040"));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
