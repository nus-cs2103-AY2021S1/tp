package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Status;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parseDay;

public class LessonCommandParserTest {
    private LessonCommandParser parser = new LessonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", LessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLessonCommand() {
        Lesson expectedLesson = new Lesson(new Title(VALID_TITLE_CS2103T), new Tag(VALID_TAG_CS2103T),
                new Description(VALID_DESC_CS2103T), DayOfWeek.MONDAY, LocalTime.of(12,0), 
                LocalTime.of(14,0), LocalDate.of(2020,1,1),
                LocalDate.of(2020,11,1));
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
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        // throw error if argument is empty
        assertParseFailure(parser, " title:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " desc:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " status:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " date:", MESSAGE_EMPTY_SEARCH_PHRASE);

        // one of the attribute is empty
        assertParseFailure(parser, " title:abc date: desc:edf", MESSAGE_EMPTY_SEARCH_PHRASE);

        // space is trimmed. empty spaces also considered empty
        assertParseFailure(parser, " desc:abc date:  ", MESSAGE_EMPTY_SEARCH_PHRASE);
    }

    @Test
    public void parse_argsWrongFormat_returnsFalse() {
        // throw error if argument is invalid
        assertParseFailure(parser, " title:@@", Title.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " desc:@@", Description.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " status:comple", Status.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " date:01-01-202", DateUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:13", DateUtil.SEARCH_DATE_CONSTRAINTS);

        // one of the attribute is invalid
        assertParseFailure(parser, " title:abc# date: desc:edf", Title.SEARCH_CONSTRAINTS);
    }
}
