package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_ESSAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ESSAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ESSAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ESSAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeadlineCommandParserTest {

    private final DeadlineCommandParser parser = new DeadlineCommandParser();

    @Test
    public void parse_validArgs_returnsDeadlineCommand() {
        Deadline expectedDeadline = Deadline.createDeadline(new Title(VALID_TITLE_LAB),
                new DeadlineDateTime(VALID_DATETIME_LAB),
                new Description(VALID_DESC_LAB), new Tag(VALID_TAG_LAB));

        DeadlineCommand expectedDeadlineCommand = new DeadlineCommand(expectedDeadline);

        //no leading and trailing whitespaces
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB,
                        PREFIX_TAG, VALID_TAG_LAB),
                expectedDeadlineCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser,
                String.format(" %s %s %s %s %s    %s %s   %s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB,
                        PREFIX_TAG, VALID_TAG_LAB),
                expectedDeadlineCommand);

        //omit optional description
        Deadline expectedDeadline2 = Deadline.createDeadline(new Title(VALID_TITLE_ESSAY),
                new DeadlineDateTime(VALID_DATETIME_ESSAY),
                Description.defaultDescription(), new Tag(VALID_TAG_ESSAY));
        DeadlineCommand expectedDeadlineCommand2 = new DeadlineCommand(expectedDeadline2);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_ESSAY,
                        PREFIX_DATE_TIME, VALID_DATETIME_ESSAY,
                        PREFIX_TAG, VALID_TAG_ESSAY),
                expectedDeadlineCommand2);

        //omit optional tag
        Deadline expectedDeadline3 = Deadline.createDeadline(new Title(VALID_TITLE_ESSAY),
                new DeadlineDateTime(VALID_DATETIME_ESSAY),
                new Description(VALID_DESC_ESSAY), Tag.defaultTag());
        DeadlineCommand expectedDeadlineCommand3 = new DeadlineCommand(expectedDeadline3);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_ESSAY,
                        PREFIX_DATE_TIME, VALID_DATETIME_ESSAY,
                        PREFIX_DESCRIPTION, VALID_DESC_ESSAY),
                expectedDeadlineCommand3);

        //omit optional tag and description
        Deadline expectedDeadline4 = Deadline.createDeadline(new Title(VALID_TITLE_ESSAY),
                new DeadlineDateTime(VALID_DATETIME_ESSAY),
                Description.defaultDescription(), Tag.defaultTag());
        DeadlineCommand expectedDeadlineCommand4 = new DeadlineCommand(expectedDeadline4);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_ESSAY,
                        PREFIX_DATE_TIME, VALID_DATETIME_ESSAY),
                expectedDeadlineCommand4);
    }

    @Test
    public void parse_invalidDateTime_returnsFalse() {
        //invalid date time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, INVALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                DateTimeUtil.DATE_TIME_CONSTRAINTS);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        //empty title
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, "",
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                Title.MESSAGE_CONSTRAINTS);

        //empty tag
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, "",
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                Tag.MESSAGE_CONSTRAINTS);

        //empty date and time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, "",
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                DateTimeUtil.DATE_TIME_CONSTRAINTS);
    }

    @Test
    public void parse_missingArgs_returnsFalse() {
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", DeadlineCommand.MESSAGE_USAGE);
        //missing title
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s",
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);
        //missing date and time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);
    }

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;

        //multiple titles
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);

        //multiple date and time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);

        //multiple descriptions
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_TAG, VALID_TAG_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB,
                        PREFIX_DESCRIPTION, VALID_DESC_LAB
                ),
                expectedErrorMessage);
    }
}
