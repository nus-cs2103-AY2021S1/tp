package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.TaskSorterCommand;

class TaskSorterCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskSorterCommand.MESSAGE_USAGE);
    private static final String VALID_TASK_NAME_PREFIX = " " + PREFIX_TASK_NAME;
    private static final String VALID_TASK_DEADLINE_PREFIX = " " + PREFIX_TASK_DEADLINE;
    private static final String VALID_TASK_PROGRESS_PREFIX = " " + PREFIX_TASK_PROGRESS;
    private static final String VALID_TASK_IS_DONE_PREFIX = " " + PREFIX_TASK_IS_DONE;
    private static final String VALID_ASCENDING_SORT_PREFIX = " " + PREFIX_ASCENDING_SORT;
    private static final String VALID_DESCENDING_SORT_PREFIX = " " + PREFIX_DESCENDING_SORT;
    private TaskSorterCommandParser parser = new TaskSorterCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sorting order specified
        assertParseFailure(parser, VALID_TASK_NAME_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_TASK_DEADLINE_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_TASK_PROGRESS_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_TASK_IS_DONE_PREFIX, MESSAGE_INVALID_FORMAT);

        // no task attribute specified
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_DESCENDING_SORT_PREFIX, MESSAGE_INVALID_FORMAT);

        // no sorting order and no task attribute specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPrefix_failure() {

        //invalid prefix for sorting order
        assertParseFailure(parser, " ascending/" + VALID_TASK_NAME_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " descending/" + VALID_TASK_PROGRESS_PREFIX, MESSAGE_INVALID_FORMAT);

        //invalid prefix for task attribute
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX + " "
            + PREFIX_PROJECT_NAME, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX + " "
            + PREFIX_DEADLINE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_DESCENDING_SORT_PREFIX
            + " progress/", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_DESCENDING_SORT_PREFIX
            + " isDone/", MESSAGE_INVALID_FORMAT);

        // no space between two prefixes
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX + PREFIX_TASK_NAME, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_valuesAfterPrefix_failure() {
        assertParseFailure(parser, VALID_DESCENDING_SORT_PREFIX
            + "random text" + VALID_TASK_PROGRESS_PREFIX, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_DESCENDING_SORT_PREFIX
            + VALID_TASK_PROGRESS_PREFIX + "@+", MESSAGE_INVALID_FORMAT);
    }
    @Test
    // TaskSorterCommand does not have equal() method
    // as one cannot compare two comparators unless they are the same object
    // Therefore, one can only check that whether the result of parsing is an instance of TaskSorterCommand (not null)
    public void parse_validArgs_success() throws Exception {

        // leading white space
        assertNotNull(parser.parse(PREAMBLE_WHITESPACE
            + VALID_DESCENDING_SORT_PREFIX + VALID_TASK_DEADLINE_PREFIX));

        // other valid inputs
        assertNotNull(parser.parse(VALID_ASCENDING_SORT_PREFIX + VALID_TASK_PROGRESS_PREFIX));
        assertNotNull(parser.parse(VALID_DESCENDING_SORT_PREFIX + VALID_TASK_IS_DONE_PREFIX));
    }

    @Test
    public void parse_moreThanTwoPrefixes_failure() {
        // more than one sorting prefix specified
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX + VALID_DESCENDING_SORT_PREFIX
            + VALID_TASK_PROGRESS_PREFIX, MESSAGE_INVALID_FORMAT);
        // more than one task attribute specified
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX
            + VALID_TASK_PROGRESS_PREFIX + VALID_TASK_DEADLINE_PREFIX, MESSAGE_INVALID_FORMAT);
        // other than a sorting prefix and a task attribute, there is another prefix
        assertParseFailure(parser, VALID_ASCENDING_SORT_PREFIX
            + VALID_TASK_PROGRESS_PREFIX + " " + PREFIX_START_DATE, MESSAGE_INVALID_FORMAT);
    }

}
