package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.commands.CommandTestUtil.COST_DESC_1;
import static nustorage.logic.commands.CommandTestUtil.DOUBLE_QUANTITY_DESC;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_DESC_1;
import static nustorage.logic.commands.CommandTestUtil.NEGATIVE_COST_DESC;
import static nustorage.logic.commands.CommandTestUtil.NEGATIVE_QUANTITY_DESC;
import static nustorage.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_DESC_1;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_ITEM_COST;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_QUANTITY;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_WITH_INVENTORY_A;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.CreateInventoryRecordCommand;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.InventoryRecordBuilder;

public class CreateInventoryRecordCommandParserTest {
    private final CreateInventoryRecordCommandParser parser = new CreateInventoryRecordCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        InventoryRecord expectedInventoryRecord = new InventoryRecordBuilder(INVENTORY_RECORD_A).build();
        FinanceRecord expectedFinanceRecord = new FinanceRecordBuilder(RECORD_WITH_INVENTORY_A).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_NAME_DESC_1 + QUANTITY_DESC_1 + COST_DESC_1,
                new CreateInventoryRecordCommand(expectedInventoryRecord, expectedFinanceRecord));

        // tags given in different positions
        assertParseSuccess(parser, QUANTITY_DESC_1 + COST_DESC_1 + ITEM_NAME_DESC_1,
                new CreateInventoryRecordCommand(expectedInventoryRecord, expectedFinanceRecord));

        // whitespace and tags in different positions
        assertParseSuccess(parser, ITEM_NAME_DESC_1 + PREAMBLE_WHITESPACE + COST_DESC_1
                        + PREAMBLE_WHITESPACE + QUANTITY_DESC_1,
                new CreateInventoryRecordCommand(expectedInventoryRecord, expectedFinanceRecord));
    }

    @Test
    public void parse_costFieldsMissing_success() {
        InventoryRecord expectedInventoryRecord = new InventoryRecordBuilder(INVENTORY_RECORD_A).build();
        FinanceRecord financeRecordZeroAmt = new FinanceRecordBuilder(RECORD_WITH_INVENTORY_A).build();
        // no cost
        assertParseSuccess(parser, ITEM_NAME_DESC_1 + QUANTITY_DESC_1,
                new CreateInventoryRecordCommand(expectedInventoryRecord, financeRecordZeroAmt));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateInventoryRecordCommand.MESSAGE_USAGE);
        // missing Item name
        assertParseFailure(parser, QUANTITY_DESC_1, expectedMessage);

        // missing quantity
        assertParseFailure(parser, ITEM_NAME_DESC_1, expectedMessage);
    }

    @Test
    public void parse_invalidValues_failure() {

        // quantity below 0
        assertParseFailure(parser, ITEM_NAME_DESC_1 + NEGATIVE_QUANTITY_DESC, MESSAGE_INVALID_QUANTITY);

        // quantity not an integer
        assertParseFailure(parser, ITEM_NAME_DESC_1 + DOUBLE_QUANTITY_DESC, MESSAGE_INVALID_QUANTITY);

        // cost below 0
        assertParseFailure(parser, ITEM_NAME_DESC_1 + QUANTITY_DESC_1
                + NEGATIVE_COST_DESC, MESSAGE_INVALID_ITEM_COST);
    }
}
