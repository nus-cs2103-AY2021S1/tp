package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.model.tag.Tag;


public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "1 tasty", new TagCommand(Index.fromOneBased(1),
                new Tag("tasty")));

        // test multiple word tag
        assertParseSuccess(parser, "2 less sugar", new TagCommand(Index.fromOneBased(2),
                new Tag("less sugar")));
    }

    @Test
    public void parse_invalidValues_failure() {
        // empty String
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_INSUFFICIENT_ARGUMENTS, TagCommand.COMMAND_WORD, 2, TagCommand.MESSAGE_USAGE)));

        // Index passed is not a non-zero integer
        assertParseFailure(parser, "a tasty", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));
        assertParseFailure(parser, "-1 tasty", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));

        // 0 is a boundary value
        assertParseFailure(parser, "0 tasty", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));
        assertParseFailure(parser, "1.4 tasty", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));


    }
}
