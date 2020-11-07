package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_ESSAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.DeadlineDateTime;


public class EditTaskCommandParserTest {

    private final EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_validArgs_returnsEditTaskCommand() {
        Index index = INDEX_FIRST_TASK;
        //edit title
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor.setTitle(new Title(VALID_TITLE_MEETING));
        EditTaskCommand expectedEditTaskCommand = new EditTaskCommand(index, editTaskDescriptor);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TITLE, VALID_TITLE_MEETING),
                expectedEditTaskCommand);

        //edit tag
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor1 = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor1.setTag(new Tag(VALID_TAG_MEETING));
        EditTaskCommand expectedEditTaskCommand1 = new EditTaskCommand(index, editTaskDescriptor1);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TAG, VALID_TAG_MEETING),
                expectedEditTaskCommand1);

        //edit description
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor2 = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor2.setDescription(new Description(VALID_DESC_MEETING));
        EditTaskCommand expectedEditTaskCommand2 = new EditTaskCommand(index, editTaskDescriptor2);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING),
                expectedEditTaskCommand2);

        //edit date
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor3 = new EditTaskCommand.EditTaskDescriptor();
        try {
            editTaskDescriptor3.setEventDate(ParserUtil.parseDate(VALID_DATE_MEETING));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EditTaskCommand expectedEditTaskCommand3 = new EditTaskCommand(index, editTaskDescriptor3);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE, VALID_DATE_MEETING),
                expectedEditTaskCommand3);

        //edit start time
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor4 = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor4.setStartTime(LocalTime.parse(VALID_START_TIME_MEETING));
        EditTaskCommand expectedEditTaskCommand4 = new EditTaskCommand(index, editTaskDescriptor4);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_START_TIME_MEETING),
                expectedEditTaskCommand4);

        //edit end time
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor5 = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor5.setEndTime(LocalTime.parse(VALID_END_TIME_MEETING));
        EditTaskCommand expectedEditTaskCommand5 = new EditTaskCommand(index, editTaskDescriptor5);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_TIME, VALID_END_TIME_MEETING),
                expectedEditTaskCommand5);

        //edit date and time
        EditTaskCommand.EditTaskDescriptor editTaskDescriptor6 = new EditTaskCommand.EditTaskDescriptor();
        editTaskDescriptor4.setDeadlineDateTime(new DeadlineDateTime(VALID_DATETIME_LAB));
        EditTaskCommand expectedEditTaskCommand6 = new EditTaskCommand(index, editTaskDescriptor6);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB),
                expectedEditTaskCommand6);
    }

    @Test
    public void parse_inValidTimeRange_returnsFalse() {
        //start time same as end time for event
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_START_TIME_MEETING),
                Time.RANGE_CONSTRAINTS);

        //end time before start time for event
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_END_TIME_MEETING,
                        PREFIX_END_TIME, VALID_START_TIME_MEETING),
                Time.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_returnsFalse() {
        //invalid day of month
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE, INVALID_DATE_MEETING),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTime_returnsFalse() {
        //invalid start time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_TIME, INVALID_START_TIME_MEETING),
                DateTimeUtil.TIME_CONSTRAINTS);

        //invalid end time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_TIME, INVALID_END_TIME_MEETING),
                DateTimeUtil.TIME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDateTime_returnsFalse() {
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE_TIME, INVALID_DATETIME_LAB),
                DateTimeUtil.DATE_TIME_CONSTRAINTS);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        //empty title
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TITLE, ""),
                Title.MESSAGE_CONSTRAINTS);

        //empty tag
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TAG, ""),
                Tag.MESSAGE_CONSTRAINTS);

        //empty description
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, ""),
                Description.MESSAGE_CONSTRAINTS);

        //empty date
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE, ""),
                DateTimeUtil.DATE_CONSTRAINTS);

        //empty date and time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DATE_TIME, ""),
                DateTimeUtil.DATE_TIME_CONSTRAINTS);

        //empty start time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_TIME, ""),
                DateTimeUtil.TIME_CONSTRAINTS);

        //empty end time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_TIME, ""),
                DateTimeUtil.TIME_CONSTRAINTS);

    }

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;

        //multiple titles
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_TITLE, VALID_TITLE_MEETING
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_TAG, VALID_TAG_EXPERIMENT,
                        PREFIX_TAG, VALID_TAG_MEETING
                ),
                expectedErrorMessage);

        //multiple description
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, VALID_DESC_EXPERIMENT,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING
                ),
                expectedErrorMessage);

        //multiple dates
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_MEETING
                ),
                expectedErrorMessage);

        //multiple date and time
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_DATE_TIME, VALID_DATETIME_ESSAY,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB
                ),
                expectedErrorMessage);

        //multiple start times
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_START_DATETIME_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_DATETIME_MEETING
                ),
                expectedErrorMessage);

        //multiple end times
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_END_TIME, VALID_END_DATETIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);
    }
}
