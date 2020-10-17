package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;

public class EditItemCommandParserTest {

    private final EditItemCommandParser parser = new EditItemCommandParser();

    /**
     * Test creation of edit item command which changes the name field.
     */
    @Test
    public void parse_validArgs_returnsEditItemCommand() {
        String itemName = "Apple";
        String userInput = "edit -o " + itemName + " -n edited";
        EditItemDescriptor descriptor = new EditItemDescriptor();
        descriptor.setName("edited");
        EditItemCommand expectedCommand = new EditItemCommand(itemName, descriptor);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", EditItemCommand.MESSAGE_NO_ORIGINAL_ITEM);
    }
}
