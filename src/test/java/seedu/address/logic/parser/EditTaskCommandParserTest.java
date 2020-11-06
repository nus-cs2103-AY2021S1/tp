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

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

public class EditTaskCommandParserTest {

    private final EditTaskCommandParser parser = new EditTaskCommandParser();

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
                        PREFIX_START_TIME, VALID_START_TIME_MEETING
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
