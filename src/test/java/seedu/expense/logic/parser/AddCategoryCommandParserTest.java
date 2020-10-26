package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.expense.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.AddCategoryCommand;
import seedu.expense.model.tag.Tag;


public class AddCategoryCommandParserTest {
    private AddCategoryCommandParser parser = new AddCategoryCommandParser();

    @Test
    public void parse_validTagFieldPresent_success() {
        Tag expectedTag = new Tag(VALID_TAG_FOOD);

        assertParseSuccess(parser, TAG_DESC_FOOD, new AddCategoryCommand(expectedTag));
    }

    @Test
    public void parse_tagFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_TAG_FOOD, expectedMessage);
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_DESC_FOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE));
    }
}
