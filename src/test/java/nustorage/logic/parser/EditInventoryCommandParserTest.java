package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.commands.CommandTestUtil.COST_1;
import static nustorage.logic.commands.CommandTestUtil.COST_DESC_1;
import static nustorage.logic.commands.CommandTestUtil.DOUBLE_QUANTITY_DESC;
import static nustorage.logic.commands.CommandTestUtil.INDEX_NEGATIVE_VALUE_DESC;
import static nustorage.logic.commands.CommandTestUtil.INDEX_ONE;
import static nustorage.logic.commands.CommandTestUtil.INDEX_ONE_DESC;
import static nustorage.logic.commands.CommandTestUtil.INDEX_ZERO_DESC;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_DESC_1;
import static nustorage.logic.commands.CommandTestUtil.NEGATIVE_COST_DESC;
import static nustorage.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_DESC_1;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_ITEM_COST;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_QUANTITY;

import org.junit.jupiter.api.Test;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.EditInventoryCommand;

public class EditInventoryCommandParserTest {
    private final EditInventoryCommandParser parser = new EditInventoryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor =
                new EditInventoryCommand.EditInventoryDescriptor();
        editInventoryDescriptor.setQuantity(QUANTITY_1);
        editInventoryDescriptor.setDescription(ITEM_NAME_1);
        editInventoryDescriptor.setUnitCost(COST_1);
        Index index = Index.fromOneBased(INDEX_ONE);

        // Basic check
        assertParseSuccess(parser, INDEX_ONE_DESC
                        + ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptor));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + INDEX_ONE_DESC + ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptor));

        // tags given in different positions
        assertParseSuccess(parser, INDEX_ONE_DESC
                        + COST_DESC_1 + ITEM_NAME_DESC_1 + QUANTITY_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptor));

        // whitespace and tags in different positions
        assertParseSuccess(parser, INDEX_ONE_DESC
                        + PREAMBLE_WHITESPACE + COST_DESC_1 + ITEM_NAME_DESC_1
                        + PREAMBLE_WHITESPACE + QUANTITY_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Index index = Index.fromOneBased(INDEX_ONE);
        // missing item name
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptorMissingItem =
                new EditInventoryCommand.EditInventoryDescriptor();
        editInventoryDescriptorMissingItem.setUnitCost(COST_1);
        editInventoryDescriptorMissingItem.setQuantity(QUANTITY_1);

        assertParseSuccess(parser, INDEX_ONE_DESC + QUANTITY_DESC_1 + COST_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptorMissingItem));

        // missing unit cost
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptorMissingUnitCost =
                new EditInventoryCommand.EditInventoryDescriptor();
        editInventoryDescriptorMissingUnitCost.setDescription(ITEM_NAME_1);
        editInventoryDescriptorMissingUnitCost.setQuantity(QUANTITY_1);

        assertParseSuccess(parser, INDEX_ONE_DESC + ITEM_NAME_DESC_1 + QUANTITY_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptorMissingUnitCost));

        // missing quantity
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptorMissingQuantity =
                new EditInventoryCommand.EditInventoryDescriptor();
        editInventoryDescriptorMissingQuantity.setDescription(ITEM_NAME_1);
        editInventoryDescriptorMissingQuantity.setUnitCost(COST_1);

        assertParseSuccess(parser, INDEX_ONE_DESC + ITEM_NAME_DESC_1 + COST_DESC_1,
                new EditInventoryCommand(index, editInventoryDescriptorMissingQuantity));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditInventoryCommand.MESSAGE_USAGE);
        // missing Index
        assertParseFailure(parser, ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1, expectedMessage);
    }

    @Test
    public void parse_invalidValues_failure() {

        // Index zero and below
        assertParseFailure(parser, INDEX_ZERO_DESC
                + ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, INDEX_NEGATIVE_VALUE_DESC
                + ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1, MESSAGE_INVALID_INDEX);

        // quantity not an integer
        assertParseFailure(parser, INDEX_ONE_DESC
                + ITEM_NAME_DESC_1 + DOUBLE_QUANTITY_DESC, MESSAGE_INVALID_QUANTITY);

        // cost below 0
        assertParseFailure(parser, INDEX_ONE_DESC
                + ITEM_NAME_DESC_1 + QUANTITY_DESC_1
                + NEGATIVE_COST_DESC, MESSAGE_INVALID_ITEM_COST);
    }

}
