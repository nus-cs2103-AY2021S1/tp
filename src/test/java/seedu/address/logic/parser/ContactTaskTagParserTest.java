package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactTaskTagCommand;
import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonTags;
import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskTags;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditPersonTagsBuilder;
import seedu.address.testutil.EditTaskTagsBuilder;

public class ContactTaskTagParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactTaskTagCommand.MESSAGE_USAGE);

    private ContactTaskTagParser parser = new ContactTaskTagParser();

    @Test
    public void parse_invalidArgs_failure() {
        // invalid index
        assertParseFailure(parser, "t/test contactIndex/1 taskIndex/a",
            Messages.MESSAGE_INVALID_DISPLAYED_INDEX);

        // no args
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingParts_failure() {
        // no contactIndex
        assertParseFailure(parser, "t/test taskIndex/1", MESSAGE_INVALID_FORMAT);

        // no taskIndex
        assertParseFailure(parser, "t/test contactIndex/1", MESSAGE_INVALID_FORMAT);

        // no Tag
        assertParseFailure(parser, "contactIndex/1 taskIndex/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        String userInput = "t/test contactIndex/1 taskIndex/1";
        Index contactIndex = ParserUtil.parseIndex("1");
        Index taskIndex = ParserUtil.parseIndex("1");

        List<String> tag = Arrays.asList("test");
        EditPersonTags editPersonTags = new EditPersonTagsBuilder().withTags("test").build();

        EditTaskTags editTaskTags = new EditTaskTagsBuilder().withTags("test").build();

        ContactTaskTagCommand expectedCommand = new ContactTaskTagCommand(contactIndex, taskIndex,
                editPersonTags, editTaskTags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
