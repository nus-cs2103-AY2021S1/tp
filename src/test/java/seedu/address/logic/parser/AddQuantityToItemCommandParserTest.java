package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddQuantityToItemCommand;

public class AddQuantityToItemCommandParserTest {

    private final AddQuantityToItemCommandParser parser = new AddQuantityToItemCommandParser();

    /**
     * Test creation of add quantity command.
     */
    @Test
    public void parse_validArgs_returnsAddQuantityToItemCommand() {
        String itemName = "Apple";
        int quantity = 10;
        String userInput = "addq -n " + itemName + " -q " + quantity;
        AddQuantityToItemCommand expectedCommand = new AddQuantityToItemCommand(itemName, quantity);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", AddQuantityToItemCommand.MESSAGE_ITEM_NOT_PROVIDED);
    }
}
