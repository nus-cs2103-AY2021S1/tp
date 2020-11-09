package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_INT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddQuantityToItemCommand;
import seedu.address.model.item.Quantity;

public class AddQuantityToItemCommandParserTest {

    private final AddQuantityToItemCommandParser parser = new AddQuantityToItemCommandParser();
    private Quantity validQuantity = new Quantity(VALID_QUANTITY_INT + "");

    /**
     * Test creation of add quantity command.
     */
    @Test
    public void parse_validArgs_returnsAddQuantityToItemCommand() {
        String userInput = "addq -n " + VALID_ITEM_NAME_APPLE + " -q " + VALID_QUANTITY_INT;
        AddQuantityToItemCommand expectedCommand = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE,
                validQuantity);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", AddQuantityToItemCommand.MESSAGE_ITEM_NOT_PROVIDED);
    }
}
