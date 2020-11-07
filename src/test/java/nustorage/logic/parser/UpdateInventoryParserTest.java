package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_QUANTITY;
import static nustorage.testutil.TypicalIndexes.INDEX_FIRST;
import static nustorage.testutil.TypicalIndexes.INDEX_SECOND;
import static nustorage.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nustorage.logic.commands.UpdateInventoryCommand;
import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;
import nustorage.testutil.UpdateInventoryDescriptorBuilder;

public class UpdateInventoryParserTest {
    private UpdateInventoryCommandParser parser;

    @BeforeEach
    public void beforeEachTest() {
        parser = new UpdateInventoryCommandParser();
    }

    @Test
    public void parse_missingChangeInQuantity_failure() {
        String missingChangeInQuantity1 = "1";
        String missingChangeInQuantity2 = "1 ";
        String missingChangeInQuantity3 = "1 30";
        String missingChangeInQuantity4 = "1 q/";

        assertParseFailure(parser, missingChangeInQuantity1, UpdateInventoryCommand.MESSAGE_MISSING_QUANTITY);
        assertParseFailure(parser, missingChangeInQuantity2, UpdateInventoryCommand.MESSAGE_MISSING_QUANTITY);
        assertParseFailure(parser, missingChangeInQuantity3,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, missingChangeInQuantity4, MESSAGE_INVALID_QUANTITY);
    }

    @Test
    public void parse_missingIndex_failure() {
        String missingIndex1 = "";
        String missingIndex2 = "q/";
        String missingIndex3 = "q/30";

        assertParseFailure(parser, missingIndex1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, missingIndex2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, missingIndex3,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        String invalidIndex1 = "a";
        String invalidIndex2 = "b q/";
        String invalidIndex3 = "# q/30";

        assertParseFailure(parser, invalidIndex1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));

        assertParseFailure(parser, invalidIndex2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));

        assertParseFailure(parser, invalidIndex3,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidQuantitySymbol_failure() {
        String invalidSymbol1 = "1 a/30";
        String invalidSymbol2 = "1 /30";
        String invalidSymbol3 = "1q/30";

        assertParseFailure(parser, invalidSymbol1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));

        assertParseFailure(parser, invalidSymbol2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));

        assertParseFailure(parser, invalidSymbol3,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateInventoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidQuantity_failure() {
        String invalidQuantity1 = "1 q/hotdog";
        String invalidQuantity2 = "1 q/--30";
        String invalidQuantity3 = "1 q/*30";

        assertParseFailure(parser, invalidQuantity1, MESSAGE_INVALID_QUANTITY);
        assertParseFailure(parser, invalidQuantity2, MESSAGE_INVALID_QUANTITY);
        assertParseFailure(parser, invalidQuantity3, MESSAGE_INVALID_QUANTITY);
    }

    @Test
    public void parse_validUpdateCommands_success() {
        String validCommand1 = "1 q/30";
        UpdateInventoryDescriptor updateInventoryDescriptor1 = new UpdateInventoryDescriptorBuilder(30).build();
        UpdateInventoryCommand expectedCommand1 = new UpdateInventoryCommand(INDEX_FIRST, updateInventoryDescriptor1);
        assertParseSuccess(parser, validCommand1, expectedCommand1);

        String validCommand2 = "2 q/-50";
        UpdateInventoryDescriptor updateInventoryDescriptor2 = new UpdateInventoryDescriptorBuilder(-50).build();
        UpdateInventoryCommand expectedCommand2 = new UpdateInventoryCommand(INDEX_SECOND, updateInventoryDescriptor2);
        assertParseSuccess(parser, validCommand2, expectedCommand2);

        String validCommand3 = "3 q/+123456789";
        UpdateInventoryDescriptor updateInventoryDescriptor3 = new UpdateInventoryDescriptorBuilder(123456789).build();
        UpdateInventoryCommand expectedCommand3 = new UpdateInventoryCommand(INDEX_THIRD, updateInventoryDescriptor3);
        assertParseSuccess(parser, validCommand3, expectedCommand3);
    }
}
