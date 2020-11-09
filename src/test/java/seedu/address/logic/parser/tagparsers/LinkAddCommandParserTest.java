package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_LINK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_ZOOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LINK_ZOOM;
import static seedu.address.logic.commands.tagcommands.LinkAddCommand.MESSAGE_LINK_NOT_PROVIDED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.tag.MeetingLink.MESSAGE_CONSTRAINTS;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.LinkAddCommand;
import seedu.address.model.tag.MeetingLink;

public class LinkAddCommandParserTest {
    private LinkAddCommandParser parser = new LinkAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws MalformedURLException {
        // whitespace only preamble
        assertParseSuccess(parser, "1 " + LINK_DESC_ZOOM,
                new LinkAddCommand(Index.fromOneBased(1), new MeetingLink(VALID_TAG_LINK_ZOOM)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingOneArg_throwsParseException() {
        // missing tag index
        assertParseFailure(parser, LINK_DESC_ZOOM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkAddCommand.MESSAGE_USAGE));

        // missing link
        assertParseFailure(parser, "1 ",
                String.format(MESSAGE_LINK_NOT_PROVIDED, LinkAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid tagIndex
        assertParseFailure(parser, "-1 " + LINK_DESC_ZOOM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkAddCommand.MESSAGE_USAGE));

        // invalid link
        assertParseFailure(parser, "1 " + INVALID_TAG_LINK_DESC,
                String.format(MESSAGE_CONSTRAINTS));

    }
}
