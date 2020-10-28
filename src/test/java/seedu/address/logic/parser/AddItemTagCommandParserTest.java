package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DELIMITER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ABC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BERT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.TAG_ABC;
import static seedu.address.testutil.TypicalTags.TAG_BERT;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddItemTagCommand;

public class AddItemTagCommandParserTest {

    private final AddItemTagCommandParser parser = new AddItemTagCommandParser();

    /**
     * Test creation of add tag command.
     */
    @Test
    public void parse_validArgs_returnsAddTagToItemCommand() {
        String userInput = "addt -n " + VALID_ITEM_NAME_APPLE + " -t " + VALID_TAG_ABC + DELIMITER_1 + VALID_TAG_BERT;
        AddItemTagCommand expectedCommand = new AddItemTagCommand(VALID_ITEM_NAME_APPLE,
                Set.of(TAG_BERT, TAG_ABC));

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidDelim_throwsParseException() {
        String userInput = "addt -n " + VALID_ITEM_NAME_APPLE + " -t " + VALID_TAG_ABC + "." + VALID_TAG_BERT;
        //expected user input constructs successful edit item command
        assertThrows(IllegalArgumentException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddItemTagCommand.MESSAGE_USAGE));
    }
}
