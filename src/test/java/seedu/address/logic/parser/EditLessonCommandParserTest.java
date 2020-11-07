package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;


public class EditLessonCommandParserTest {

    private final EditLessonCommandParser parser = new EditLessonCommandParser();

    @Test
    public void parse_validArgs_returnsEditLessonCommand() {
        Index index = INDEX_FIRST_MODEL;
        //edit title
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor.setTitle(new Title(VALID_TITLE_CS2000));
        EditLessonCommand expectedEditLessonCommand = new EditLessonCommand(index, editLessonDescriptor);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TITLE, VALID_TITLE_CS2000),
                expectedEditLessonCommand);

        //edit tag
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor1 = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor1.setTag(new Tag(VALID_TAG_CS2000));
        EditLessonCommand expectedEditLessonCommand1 = new EditLessonCommand(index, editLessonDescriptor1);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_TAG, VALID_TAG_CS2000),
                expectedEditLessonCommand1);

        //edit day
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor2 = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor2.setDayOfWeek(DayOfWeek.MONDAY);
        EditLessonCommand expectedEditLessonCommand2 = new EditLessonCommand(index, editLessonDescriptor2);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DAY, VALID_DAY_CS2000),
                expectedEditLessonCommand2);

        //edit description
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor3 = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor3.setDescription(new Description(VALID_DESC_CS2000));
        EditLessonCommand expectedEditLessonCommand3 = new EditLessonCommand(index, editLessonDescriptor3);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, VALID_DESC_CS2000),
                expectedEditLessonCommand3);

        //edit start time
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor4 = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor4.setStartTime(LocalTime.parse(VALID_START_TIME_CS2000));
        EditLessonCommand expectedEditLessonCommand4 = new EditLessonCommand(index, editLessonDescriptor4);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_START_TIME_CS2000),
                expectedEditLessonCommand4);

        //edit end time
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor5 = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor5.setEndTime(LocalTime.parse(VALID_END_TIME_CS2000));
        EditLessonCommand expectedEditLessonCommand5 = new EditLessonCommand(index, editLessonDescriptor5);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_TIME, VALID_END_TIME_CS2000),
                expectedEditLessonCommand5);

        //edit start date
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor6 = new EditLessonCommand.EditLessonDescriptor();
        try {
            editLessonDescriptor6.setStartDate(ParserUtil.parseDate(VALID_START_DATE_CS2000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EditLessonCommand expectedEditLessonCommand6 = new EditLessonCommand(index, editLessonDescriptor6);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_DATE, VALID_START_DATE_CS2000),
                expectedEditLessonCommand6);

        //edit end date
        EditLessonCommand.EditLessonDescriptor editLessonDescriptor7 = new EditLessonCommand.EditLessonDescriptor();
        try {
            editLessonDescriptor7.setEndDate(ParserUtil.parseDate(VALID_END_DATE_CS2000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EditLessonCommand expectedEditLessonCommand7 = new EditLessonCommand(index, editLessonDescriptor7);
        assertParseSuccess(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_DATE, VALID_END_DATE_CS2000),
                expectedEditLessonCommand7);

    }

    @Test
    public void parse_invalidTimeRange_returnsFalse() {
        //start time same as end time
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_END_TIME_CS2100,
                        PREFIX_END_TIME, VALID_END_TIME_CS2100),
                Time.RANGE_CONSTRAINTS);

        //end time before start time
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_END_TIME_CS2100,
                        PREFIX_END_TIME, VALID_START_TIME_CS2100),
                Time.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDateRange_returnsFalse() {
        //start date same as end date
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_DATE, VALID_END_DATE_CS2100,
                        PREFIX_END_DATE, VALID_END_DATE_CS2100),
                DateTimeUtil.RANGE_CONSTRAINTS);

        //end date before start date
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_DATE, VALID_END_DATE_CS2100,
                        PREFIX_END_DATE, VALID_START_DATE_CS2100),
                DateTimeUtil.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_returnsFalse() {
        //invalid start date
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_DATE, INVALID_START_DATE_CS2100),
                DateTimeUtil.DATE_CONSTRAINTS);

        //invalid end date
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_DATE, INVALID_END_DATE_CS2100),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTime_returnsFalse() {
        //invalid start time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_TIME, INVALID_START_TIME_CS2100),
                DateTimeUtil.TIME_CONSTRAINTS);

        //invalid end time
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_TIME, INVALID_END_TIME_CS2100),
                DateTimeUtil.TIME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDay_returnsFalse() {
        //invalid day
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DAY, INVALID_DAY_CS2100),
                DateTimeUtil.DAY_MESSAGE_CONSTRAINTS);
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

        //empty day
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DAY, ""),
                DateTimeUtil.DAY_MESSAGE_CONSTRAINTS);

        //empty description
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, ""),
                Description.MESSAGE_CONSTRAINTS);

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

        //empty start date
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_START_DATE, ""),
                DateTimeUtil.DATE_CONSTRAINTS);

        //empty end date
        assertParseFailure(parser,
                String.format(" %s %s%s",
                        "1",
                        PREFIX_END_DATE, ""),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;

        //multiple titles
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TITLE, VALID_TITLE_CS2100
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2100
                ),
                expectedErrorMessage);

        //multiple days
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2100
                ),
                expectedErrorMessage);

        //multiple description
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2100
                ),
                expectedErrorMessage);

        //multiple start times
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2100
                ),
                expectedErrorMessage);

        //multiple end times
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2100
                ),
                expectedErrorMessage);

        //multiple start dates
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2100
                ),
                expectedErrorMessage);

        //multiple end dates
        assertParseFailure(parser,
                String.format(" %s %s%s %s%s",
                        "1",
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2100
                ),
                expectedErrorMessage);
    }
}
