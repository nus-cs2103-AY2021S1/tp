package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2103T_NO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
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

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Title;

public class LessonCommandParserTest {

    private final LessonCommandParser parser = new LessonCommandParser();

    @Test
    public void parse_validArgs_returnsLessonCommand() {
        Lesson expectedLesson = VALID_LESSON_CS2103T;
        LessonCommand expectedLessonCommand = new LessonCommand(expectedLesson);
        // no leading and trailing whitespaces
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedLessonCommand
        );

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                String.format(" %s %s %s %s %s    %s %s   %s  %s   %s %s   %s %s   %s %s  %s  ",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedLessonCommand
        );
        // omit optional description
        Lesson expectedLesson2 = VALID_LESSON_CS2103T_NO_DESC;
        LessonCommand expectedLessonCommand2 = new LessonCommand(expectedLesson2);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedLessonCommand2
        );
    }

    @Test
    public void parse_overlappingLesson_returnsFalse() {
        // start time same as end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Time.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTimeRange_returnsFalse() {
        // start time same as end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Time.RANGE_CONSTRAINTS);
        // end time before start time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Time.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDateRange_returnsFalse() {
        // start date same as end date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_START_DATE_CS2103T
                ),
                DateTimeUtil.RANGE_CONSTRAINTS);
        // end date before start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_END_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_START_DATE_CS2103T
                ),
                DateTimeUtil.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTime_returnsFalse() {
        //invalid start time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, INVALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.TIME_CONSTRAINTS);

        //invalid end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, INVALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.TIME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_returnsFalse() {
        //invalid start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, INVALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.DATE_CONSTRAINTS);

        //invalid end date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, INVALID_END_DATE_CS2103T
                ),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDay_returnsFalse() {
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, INVALID_DAY_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.DAY_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        //empty title
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, "",
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Title.MESSAGE_CONSTRAINTS);
        //empty tag
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, "",
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Tag.MESSAGE_CONSTRAINTS);
        //empty day
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, "",
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.DAY_MESSAGE_CONSTRAINTS);
        //empty start time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, "",
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.TIME_CONSTRAINTS);
        //empty end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, "",
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.TIME_CONSTRAINTS);
        //empty start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, "",
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                DateTimeUtil.DATE_CONSTRAINTS);
        //empty start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, ""
                ),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_missingArgs_returnsFalse() {
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", LessonCommand.MESSAGE_USAGE);
        //missing title
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing tag
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing day
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing start time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //missing end date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T
                ),
                expectedErrorMessage);
    }

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;
        //multiple titles
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        // multiple descriptions
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_DESCRIPTION, VALID_DESC_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);
        //multiple days
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        //multiple start times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        //multiple end times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        //multiple start dates
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);

        //multiple end dates
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                expectedErrorMessage);


    }
}
