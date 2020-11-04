package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB;
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

        //omit optional fields
        Deadline expectedDeadline2 = Deadline.createDeadline(new Title(VALID_TITLE_LAB),
                new DeadlineDateTime(VALID_DATETIME_LAB),
                Description.defaultDescription(), Tag.defaultTag());
        DeadlineCommand expectedDeadlineCommand2 = new DeadlineCommand(expectedDeadline2);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_LAB,
                        PREFIX_DATE_TIME, VALID_DATETIME_LAB),
                expectedDeadlineCommand2);
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
