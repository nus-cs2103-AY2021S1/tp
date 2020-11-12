package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Status;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "Meet Alice");
        predicate.setKeyword(PREFIX_DESCRIPTION, "project");
        predicate.setKeyword(PREFIX_DATE, "01-01-2020");
        predicate.setKeyword(PREFIX_STATUS, "incomplete");
        predicate.setKeyword(PREFIX_TAG, "CS2103T");
        FindTaskCommand expectedFindTaskCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser,
                " title:Meet Alice desc:project date:01-01-2020 status:incomplete tag:CS2103T",
                expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n title:Meet Alice  \t desc:project \t\t\t date: \t 01-01-2020 "
                        + "\n status:incomplete \t \t tag:  CS2103T",
                expectedFindTaskCommand);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        // throw error if argument is empty
        assertParseFailure(parser, " title:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " desc:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " date:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " tag:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " status:", MESSAGE_EMPTY_SEARCH_PHRASE);

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
        assertParseFailure(parser, " status:comple", Status.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " date:01-01-202", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:29-02-2019", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:13", DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " tag:##", Tag.SEARCH_CONSTRAINTS);

        // one of the attribute is invalid
        assertParseFailure(parser, " title:abc# date:01-01-2020 desc:edf", Title.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:@@", Description.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:valid tag:%%", Tag.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " title:abc date:01-01-2020 desc:valid status:compl",
                Status.SEARCH_CONSTRAINTS);
    }
}
