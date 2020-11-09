package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.EXTRA_ARGUMENT_MESSAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.model.task.CollaborativeLink;

public class LinkCollaborativeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LinkCollaborativeCommand.MESSAGE_USAGE);

    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_invalidCommand_failure() {
        assertParseFailure(parser, "idontknow ", LinkCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "doc ", MESSAGE_INVALID_FORMAT);

        // no description specified
        assertParseFailure(parser, "doc i/1 url/https://www.google.com", MESSAGE_INVALID_FORMAT);

        // no url specified
        assertParseFailure(parser, "doc i/1 desc/test", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "doc i/-5 " + PREFIX_DESCRIPTION
                + VALID_DESCRIPTION + " " + PREFIX_URL + VALID_URL, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "doc i/0" + PREFIX_DESCRIPTION
                + VALID_DESCRIPTION + " " + PREFIX_URL + VALID_URL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "doc i/1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix
        assertParseFailure(parser, "doc i/1 s/ string", String.format(EXTRA_ARGUMENT_MESSAGE, "s/"));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        CollaborativeLink link = new CollaborativeLink(VALID_DESCRIPTION,
                VALID_URL);
        String userInput = "doc i/" + targetIndex.getOneBased() + " "
                + PREFIX_DESCRIPTION + VALID_DESCRIPTION + " " + PREFIX_URL + VALID_URL;

        LinkCollaborativeCommand expectedCommand = new LinkCollaborativeCommand(targetIndex, link);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
