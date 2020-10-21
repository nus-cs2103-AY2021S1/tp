package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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
        String userInput = " -o " + VALID_ITEM_NAME_APPLE + " -n edited";
        EditItemDescriptor descriptor = new EditItemDescriptor();
        descriptor.setName("edited");
        System.out.println(descriptor);
        EditItemCommand expectedCommand = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditItemCommand.MESSAGE_USAGE));
    }
}
