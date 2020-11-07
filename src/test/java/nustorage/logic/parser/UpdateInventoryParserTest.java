package nustorage.logic.parser;

import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;

import nustorage.logic.commands.UpdateInventoryCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateInventoryParserTest {
    private UpdateInventoryCommandParser parser;

    @BeforeEach
    public void beforeEachTest() {
        parser = new UpdateInventoryCommandParser();
    }

    @Test
    public void parse_missingChangeInQuantity_failure() {
        String missingChangeInQuantity1 = "update_inventory 1";
        String missingChangeInQuantity2 = "update_inventory 1 ";
        String missingChangeInQuantity3 = "update_inventory 1 30";
        String missingChangeInQuantity4 = "update_inventory 1 q/30";

        assertParseFailure(parser, missingChangeInQuantity4, UpdateInventoryCommand.MESSAGE_MISSING_QUANTITY);
    }
}
