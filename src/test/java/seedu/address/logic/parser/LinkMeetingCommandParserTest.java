package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.EXTRA_ARGUMENT_MESSAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.link.LinkMeetingCommand;
import seedu.address.model.task.MeetingLink;

public class LinkMeetingCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LinkMeetingCommand.MESSAGE_USAGE);

    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_invalidCommand_failure() {
        assertParseFailure(parser, "idontknow ", LinkCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "meeting ", MESSAGE_INVALID_FORMAT);

        // no description specified
        assertParseFailure(parser, "meeting i/1 url/https://www.google.com", MESSAGE_INVALID_FORMAT);

        // no url specified
        assertParseFailure(parser, "meeting i/1 desc/test", MESSAGE_INVALID_FORMAT);

        // no date specified
        assertParseFailure(parser,
                "meeting i/1 desc/test url/https://www.google.com time/2359",
                MESSAGE_INVALID_FORMAT);

        // no time specified
        assertParseFailure(parser,
                "meeting i/1 desc/test url/https://www.google.com date/29-10-2020",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "meeting i/-5 "
                + PREFIX_DESCRIPTION + VALID_DESCRIPTION + " "
                + PREFIX_URL + VALID_URL + " " + PREFIX_DATE + "20-01-2020 "
                + PREFIX_TIME + "2359", MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "meeting i/0"
                + PREFIX_DESCRIPTION + VALID_DESCRIPTION + " "
                + PREFIX_URL + VALID_URL + " " + PREFIX_DATE + "20-01-2020 "
                + PREFIX_TIME + "2359", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "meeting i/1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix
        assertParseFailure(parser, "meeting i/1 s/ string", String.format(EXTRA_ARGUMENT_MESSAGE, "s/"));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        MeetingLink link = new MeetingLink(VALID_DESCRIPTION,
                VALID_URL, VALID_DATE_TIME_TUTOR_MEETING);
        String userInput = "meeting i/" + targetIndex.getOneBased() + " "
                + PREFIX_DESCRIPTION + VALID_DESCRIPTION + " " + PREFIX_URL
                + VALID_URL + " " + PREFIX_DATE + "10-12-2017 " + PREFIX_TIME + "1445";

        LinkMeetingCommand expectedCommand = new LinkMeetingCommand(targetIndex, link);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
