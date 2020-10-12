package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.CategoryEqualsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Revision History"));
        categoryList.add(new Category("SDLC"));
        FilterCommand expectedFilterCommand =
                new FilterCommand(new CategoryEqualsKeywordsPredicate(categoryList));
        assertParseSuccess(parser, " c/SDLC c/Revision History", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n c/SDLC \n \t c/Revision History  \t", expectedFilterCommand);
    }
}
