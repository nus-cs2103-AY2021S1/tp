package seedu.address.logic.parser.itemparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DUCK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itemcommand.ItemFindCommand;
import seedu.address.model.item.predicate.NameContainsKeywordsPredicate;

public class ItemFindCommandParserTest {

    private ItemFindCommandParser parser = new ItemFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ItemFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ItemFindCommand expectedFindCommand =
                new ItemFindCommand(new NameContainsKeywordsPredicate(Arrays.asList("CHICKEN", "DUCK")));
        assertParseSuccess(parser, NAME_DESC_CHICKEN + " " + VALID_NAME_DUCK, expectedFindCommand);
    }

}
