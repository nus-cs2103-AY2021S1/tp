package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMostPopularCommand;
import seedu.address.model.category.Category;

class FindMostPopularCommandParserTest {

    private FindMostPopularCommandParser parser = new FindMostPopularCommandParser();

    @Test
    void parse_validArgs_returnsFindMostPopularCommand() {
        assertParseSuccess(parser, VALID_CATEGORY_SCIENCE,
                new FindMostPopularCommand(new Category(VALID_CATEGORY_SCIENCE)));
    }


    @Test
    public void parse_emptyArgs_returnsFindMostPopularCommand() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMostPopularCommand.MESSAGE_USAGE));
    }
}
