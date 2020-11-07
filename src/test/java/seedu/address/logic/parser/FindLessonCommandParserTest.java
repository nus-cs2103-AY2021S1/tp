package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.model.lesson.LessonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;


public class FindLessonCommandParserTest {

    private FindLessonCommandParser parser = new FindLessonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", FindLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "project");
        predicate.setKeyword(PREFIX_DESCRIPTION, "sem 1");
        predicate.setKeyword(PREFIX_DATE, "10-10-2020");
        predicate.setKeyword(PREFIX_TIME, "12:00");
        predicate.setKeyword(PREFIX_DATE_TIME, "10-10-2020 12:00");
        predicate.setKeyword(PREFIX_TAG, "CS2103T");
        FindLessonCommand expectedFindLessonCommand = new FindLessonCommand(predicate);
        assertParseSuccess(parser,
                " title:project desc:sem 1 date:10-10-2020 time:12:00 datetime:10-10-2020 12:00 tag:CS2103T",
                expectedFindLessonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n title:project  \t desc:sem 1 \t\t\t date: \t 10-10-2020 "
                        + "\n time: 12:00 \t datetime: 10-10-2020 12:00 \t tag:  CS2103T",
                expectedFindLessonCommand);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        // throw error if argument is empty
        assertParseFailure(parser, " title:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " desc:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " date:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " time:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " tag:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " datetime:", MESSAGE_EMPTY_SEARCH_PHRASE);

        // one of the attribute is empty
        assertParseFailure(parser, " title:abc date: desc:edf", MESSAGE_EMPTY_SEARCH_PHRASE);

        // space is trimmed. empty spaces also considered empty
        assertParseFailure(parser, " desc:abc tag:  ", MESSAGE_EMPTY_SEARCH_PHRASE);
    }

    @Test
    public void parse_argsWrongFormat_returnsFalse() {
        // throw error if argument is invalid
        assertParseFailure(parser, " title:@@", Title.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " desc:@@", Description.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " date:01-01-202", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:29-02-2019", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:13", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " tag:##", Tag.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " time:12312:123", DateTimeUtil.SEARCH_TIME_CONSTRAINTS);

        // one of the attribute is invalid
        assertParseFailure(parser, " title:abc# date:01-01-2020 desc:edf", Title.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:@@", Description.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:valid tag:%%", Tag.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:valid time:compl",
                DateTimeUtil.SEARCH_TIME_CONSTRAINTS);
    }
}
