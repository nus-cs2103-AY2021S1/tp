package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GOAL_TARGET;
import static seedu.address.logic.commands.CommandTestUtil.LIST_GOAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SET_GOAL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.SET_GOAL_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOAL_TARGET_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOAL_TARGET_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SET_GOAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetCommand;
import seedu.address.model.module.GoalTarget;

/**
 * Parses input arguments and creates a SetCommand object.
 */
public class SetCommandParserTest {

    private SetCommandParser parser = new SetCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        GoalTarget expectedGoal = new GoalTarget(VALID_GOAL_TARGET_A);
        GoalTarget expectedList = new GoalTarget();

        // multiple names - last name accepted
        assertParseSuccess(parser, SET_GOAL_DESC_B + SET_GOAL_DESC_A, new SetCommand(expectedGoal));

        // for list command
        assertParseSuccess(parser, LIST_GOAL_DESC, new SetCommand(expectedList));

        // multiple command types (both --list and --set) - list is ignored
        assertParseSuccess(parser, LIST_GOAL_DESC + SET_GOAL_DESC_A, new SetCommand(expectedGoal));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_GOAL_TARGET_INPUT, expectedMessage);
        assertParseFailure(parser, WHITESPACE, expectedMessage);
    }

    @Test
    public void parse_invalidGoalTarget_failure() {
        // out of range
        assertParseFailure(parser, WHITESPACE + PREFIX_SET_GOAL
                + INVALID_GOAL_TARGET, GoalTarget.MESSAGE_CONSTRAINTS);
    }
}
