package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTodos.CHORES;
import static seedu.address.testutil.TypicalTodos.HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.model.task.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandParserTest {
    private static final String HOMEWORK_DESC = "desc/homework ";
    private static final String HOMEWORK_DATE = "date/12-12-2020 ";
    private static final String HOMEWORK_TIME = "time/2359";

    private static final String CHORES_DESC = "desc/do chores ";
    private static final String CHORES_DATE = "date/01-01-2020 ";
    private static final String CHORES_TIME = "time/1800 ";
    private static final String CHORES_RECURRENCE = "recurring/1 week";

    private static final String INVALID_DATE = "date/1-15-2020 ";
    private static final String INVALID_TIME = "time/2500";
    private static final String INVALID_RECURRENCE_NUMBER = "recurring/0 day";
    private static final String INVALID_RECURRENCE_VALUE = "recurring/xxx";
    private static final String INVALID_RECURRENCE_UNIT = "recurring/1 sleep";

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Todo expectedTodo = new TodoBuilder(HOMEWORK).build();

        assertParseSuccess(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE + HOMEWORK_TIME,
                new AddTodoCommand(expectedTodo));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, "todo " + HOMEWORK_DATE + HOMEWORK_TIME, expectedMessage);

        // missing date
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_TIME, expectedMessage);

        // missing time
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE, expectedMessage);

        // no command details
        assertParseFailure(parser, "todo ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedDateMessage = Messages.MESSAGE_INVALID_DATE_FORMAT;
        String expectedTimeMessage = Messages.MESSAGE_INVALID_TIME_FORMAT;

        // invalid date
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + INVALID_DATE + HOMEWORK_TIME,
                expectedDateMessage);

        // invalid time
        assertParseFailure(parser, "todo " + HOMEWORK_DESC + HOMEWORK_DATE + INVALID_TIME,
                expectedTimeMessage);
    }

    @Test
    public void parse_allFieldsPresentWithRecurrence() {
        Todo expectedTodo = new TodoBuilder(CHORES).build();
        assertParseSuccess(parser, "todo " + CHORES_DESC + CHORES_DATE + CHORES_TIME + CHORES_RECURRENCE,
                new AddTodoCommand(expectedTodo));
    }

    @Test
    public void parse_allFieldsPresentWithWrongRecurrenceInput() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // invalid recurrence number
        assertParseFailure(parser, "todo " + CHORES_DESC + CHORES_DATE + CHORES_TIME
                + INVALID_RECURRENCE_NUMBER, expectedMessage);

        // invalid recurrence unit
        assertParseFailure(parser, "todo " + CHORES_DESC + CHORES_DATE + CHORES_TIME
                + INVALID_RECURRENCE_UNIT, expectedMessage);

        // invalid recurrence value
        assertParseFailure(parser, "todo " + CHORES_DESC + CHORES_DATE + CHORES_TIME
            + INVALID_RECURRENCE_VALUE, expectedMessage);
    }

}
