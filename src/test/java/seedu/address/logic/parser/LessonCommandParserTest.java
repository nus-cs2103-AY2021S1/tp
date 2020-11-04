package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.DateUtil.DATE_CONSTRAINTS;
import static seedu.address.commons.util.DateUtil.TIME_CONSTRAINTS;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.DAY_MESSAGE_CONSTRAINTS;

public class LessonCommandParserTest {
    private LessonCommandParser parser = new LessonCommandParser();
    Lesson expectedLesson = new Lesson(new Title(VALID_TITLE_CS2103T), new Tag(VALID_TAG_CS2103T),
            new Description(VALID_DESC_CS2103T), DayOfWeek.MONDAY, LocalTime.of(12,0),
            LocalTime.of(14,0), LocalDate.of(2020,1,1),
            LocalDate.of(2020,11,1));
    LessonCommand expectedLessonCommand = new LessonCommand(expectedLesson);
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
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T,
                        PREFIX_END_DATE, VALID_END_DATE_CS2103T
                ),
                Time.RANGE_CONSTRAINTS);
    }
    @Test
    public void parse_invalidTime_returnsFalse() {
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
    public void parse_validArgs_returnsLessonCommand() {
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
        Lesson expectedLesson2 = new Lesson(new Title(VALID_TITLE_CS2103T), new Tag(VALID_TAG_CS2103T),
                Description.defaultDescription(), DayOfWeek.MONDAY, LocalTime.of(12,0),
                LocalTime.of(14,0), LocalDate.of(2020,1,1),
                LocalDate.of(2020,11,1));
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
                DAY_MESSAGE_CONSTRAINTS);
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
                TIME_CONSTRAINTS);
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
                TIME_CONSTRAINTS);
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
                DATE_CONSTRAINTS);
        //empty start date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2100,
                        PREFIX_END_DATE, ""
                ),
                DATE_CONSTRAINTS);
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
                        PREFIX_TAG, VALID_TAG_CS2103T,
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
                        PREFIX_TAG, VALID_TAG_CS2103T,
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
                        PREFIX_TAG, VALID_TAG_CS2103T,
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
                        PREFIX_TAG, VALID_TAG_CS2103T,
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
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_TAG, VALID_TAG_CS2103T,
                        PREFIX_DAY, VALID_DAY_CS2103T,
                        PREFIX_START_TIME, VALID_START_TIME_CS2103T,
                        PREFIX_END_TIME, VALID_END_TIME_CS2103T,
                        PREFIX_START_DATE, VALID_START_DATE_CS2103T
                ),
                expectedErrorMessage);
    }
}
