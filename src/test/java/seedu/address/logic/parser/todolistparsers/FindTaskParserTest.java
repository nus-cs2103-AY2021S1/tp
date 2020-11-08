package seedu.address.logic.parser.todolistparsers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SEARCH_KEYWORD;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB05;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;
import seedu.address.model.task.TaskMatchesStatusPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;


public class FindTaskParserTest {

    private FindTaskParser parser = new FindTaskParser();

    @Test
    public void parse_noFieldSpecified_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgumentSingleInput_throwsParseException() {

        // missing task name
        assertParseFailure(parser, " " + PREFIX_NAME,
                String.format(MESSAGE_INVALID_SEARCH_KEYWORD));

        // missing date
        assertParseFailure(parser, " " + PREFIX_DATE,
                String.format(Date.MESSAGE_CONSTRAINTS));

        // missing priority
        assertParseFailure(parser, " " + PREFIX_PRIORITY,
                String.format(Priority.MESSAGE_CONSTRAINTS));

        // missing tag
        assertParseFailure(parser, " " + PREFIX_TAG,
                String.format(MESSAGE_INVALID_SEARCH_KEYWORD));

        // missing status
        assertParseFailure(parser, " " + PREFIX_STATUS,
                String.format(Status.MESSAGE_CONSTRAINTS));

        // invalid priority
        assertParseFailure(parser, " " + PREFIX_PRIORITY + INVALID_TASK_PRIORITY_DESC,
                String.format(Priority.MESSAGE_CONSTRAINTS));

        // invalid date
        assertParseFailure(parser, " " + PREFIX_PRIORITY + INVALID_TASK_PRIORITY_DESC,
                String.format(Priority.MESSAGE_CONSTRAINTS));

        // invalid status
        assertParseFailure(parser, " " + PREFIX_DATE + INVALID_TASK_DATE_DESC,
                String.format(Date.MESSAGE_CONSTRAINTS));

        // invalid tag keyword
        assertParseFailure(parser, " " + PREFIX_TAG + "abc.",
                String.format(Tag.MESSAGE_CONSTRAINTS));

    }

    @Test
    public void parse_invalidArgumentMultipleInputs_throwsParseException() {

        // missing task name with valid date
        assertParseFailure(parser, " " + PREFIX_NAME + " " + DATE_DESC_LAB05,
                String.format(MESSAGE_INVALID_SEARCH_KEYWORD));

        // valid task name followed with invalid date
        // message constraints for date should be displayed
        assertParseFailure(parser, NAME_DESC_LAB05 + INVALID_TASK_DATE_DESC,
                String.format(Date.MESSAGE_CONSTRAINTS));

        // invalid input in the middle
        // message constraints for date should be displayed
        assertParseFailure(parser, NAME_DESC_LAB05 + TAG_DESC_LAB05
                + INVALID_TASK_DATE_DESC + PRIORITY_DESC_LAB05,
                String.format(Date.MESSAGE_CONSTRAINTS));

        // invalid input as the last input
        // message constraints for priority should be displayed
        assertParseFailure(parser, NAME_DESC_LAB05 + TAG_DESC_LAB05
                + DATE_DESC_LAB05 + INVALID_TASK_PRIORITY_DESC,
                String.format(Priority.MESSAGE_CONSTRAINTS));

        // multiples priority
        assertParseFailure(parser, " " + PREFIX_PRIORITY + PRIORITY_DESC_LAB07
                + " " + VALID_PRIORITY_LAB05,
                String.format(Priority.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgumentMultipleInputs_noException() {

        // invalid input followed with valid input (has been tested in other classes)
        assertDoesNotThrow(() -> parser.parse(INVALID_TASK_NAME_DESC + NAME_DESC_LAB05));

        // multiple same input
        assertDoesNotThrow(() -> parser.parse(NAME_DESC_LAB05 + NAME_DESC_LAB05));
    }

    @Test
    public void parse_validArgsSpecifiedSingleTypeInput_returnsFindCommand() {

        // test with whitespaces
        // multiple task names, multiple prefixes
        FindTaskCriteria criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskNameContainsKeywordsPredicate(Arrays.asList("Lab")));
        FindTaskCommand expectedFindCommand = new FindTaskCommand(criteria);

        String userInput = " " + PREFIX_NAME + "Assignment" + "  " + PREFIX_NAME + "Lab";

        assertParseSuccess(parser, " " + PREFIX_NAME + "Lab", expectedFindCommand);

        // only last input is considered
        assertParseSuccess(parser, " " + PREFIX_NAME + "Finish" + " "
            + PREFIX_NAME + "Lab", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " " + "Finish" + " "
            + PREFIX_NAME + "Lab", expectedFindCommand);

        // multiple task names, one prefix
        criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskNameContainsKeywordsPredicate(Arrays.asList(
                "Finish", "LAB", "Tutorial")));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput = " " + PREFIX_NAME + "Finish" + " " + "LAB" + " " + "Tutorial";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple tags, one prefix
        criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskContainsTagsPredicate(new HashSet<>(Arrays.asList(
                new Tag("LAB"), new Tag("Assignment")))));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput = " " + PREFIX_TAG + "Assignment" + " " + "LAB";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple priorities, multiple prefixes
        criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskMatchesPriorityPredicate(Priority.HIGH));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput = " " + PREFIX_PRIORITY + "low" + " " + PREFIX_PRIORITY + "High";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple status, multiple prefixes
        criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskMatchesStatusPredicate(Status.NOT_COMPLETED));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput = " " + PREFIX_STATUS + "complete" + " " + PREFIX_STATUS + "incomplete";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple dates, multiple prefixes
        criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskMatchesDatePredicate(new Date("2020-10-10")));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput = " " + PREFIX_DATE + "2020-10-15" + " " + PREFIX_DATE + "2020-10-10";

        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_validArgsSpecifiedMultipleTypeInput_returnsFindCommand() throws Exception {

        // order must be the same as in the FindTaskParser class

        // with task name
        FindTaskCriteria criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskNameContainsKeywordsPredicate(Arrays.asList(
                "Finish", "LAB", "Tutorial")));
        FindTaskCommand expectedFindCommand = new FindTaskCommand(criteria);

        String userInput = " " + PREFIX_NAME + "Finish" + " " + "LAB" + " " + "Tutorial";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with task name and date
        criteria.addPredicate(new TaskMatchesDatePredicate(new Date("2020-10-10")));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput += " " + PREFIX_DATE + "2020-10-15" + " " + PREFIX_DATE + "2020-10-10";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with task name, date, and priority
        criteria.addPredicate(new TaskMatchesPriorityPredicate(Priority.HIGH));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput += " " + PREFIX_PRIORITY + "low" + " " + PREFIX_PRIORITY + "High";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with task name, date, priority, and status
        criteria.addPredicate(new TaskMatchesStatusPredicate(Status.NOT_COMPLETED));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput += " " + PREFIX_STATUS + "complete" + " " + PREFIX_STATUS + "incomplete";

        assertParseSuccess(parser, userInput, expectedFindCommand);

        // with task name, date, priority, status, and tags (all)
        criteria.addPredicate(new TaskContainsTagsPredicate(new HashSet<>(Arrays.asList(
                new Tag("Assignment"), new Tag("LAB")))));
        expectedFindCommand = new FindTaskCommand(criteria);

        userInput += " " + PREFIX_TAG + "Assignment" + " " + "LAB";
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }
}
