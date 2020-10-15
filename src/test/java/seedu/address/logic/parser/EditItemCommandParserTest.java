package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.commands.EditItemCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.APPLE;

public class EditItemCommandParserTest {

    private final EditItemCommandParser parser = new EditItemCommandParser();

    /**
     * Test creation of edit item command which changes the name field.
     */
    @Test
    public void parse_validArgs_returnsEditItemCommand() {
        String userInput = INDEX_FIRST_ITEM.getOneBased() + " -n edited";
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName("edited");
        EditItemCommand expectedCommand = new EditItemCommand(INDEX_FIRST_ITEM, descriptor);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditItemCommand.MESSAGE_USAGE));
    }
}