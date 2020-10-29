package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_QUANTITY_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TAG_BERT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TAG_MULTIPARSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_APPLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.TAG_BERT;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.address.model.item.Quantity;

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
        EditItemCommand expectedCommand = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Test creation of edit item command which changes the quantity field.
     */
    @Test
    public void parse_optionalArgs_returnsEditItemCommand() {
        String userInput = " -o " + VALID_ITEM_NAME_APPLE + ITEM_QUANTITY_DESC_APPLE;
        EditItemDescriptor descriptor = new EditItemDescriptor();

        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_APPLE));
        EditItemCommand expectedCommand = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);

        //expected user input constructs successful edit item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parseTags_singleAndMultiDelimited_returnEditItemCommand() {
        //single tag parsing
        EditItemDescriptor descriptor = new EditItemDescriptor();
        String userInput = " -o " + VALID_ITEM_NAME_APPLE + ITEM_TAG_BERT;
        descriptor.setTags(Set.of(TAG_BERT));

        EditItemCommand expectedCommand = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //delimiter multi tag parsing
        descriptor = new EditItemDescriptor();
        userInput = " -o " + VALID_ITEM_NAME_APPLE + ITEM_TAG_MULTIPARSE;
        descriptor.setTags(getTypicalTagSet());

        expectedCommand = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noItemProvided_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditItemCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryArgsMissing_throwsParseException() {
        assertParseFailure(parser, " -n " + VALID_ITEM_NAME_APPLE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditItemCommand.MESSAGE_USAGE));
    }
}
