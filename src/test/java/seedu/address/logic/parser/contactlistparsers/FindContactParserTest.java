package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SEARCH_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.address.model.contact.FindContactCriteria;
import seedu.address.model.tag.Tag;


public class FindContactParserTest {

    private FindContactParser parser = new FindContactParser();

    @Test
    public void parse_noFieldSpecified_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {

        // missing name keyword
        assertParseFailure(parser, " " + PREFIX_NAME, String.format(MESSAGE_INVALID_SEARCH_KEYWORD));

        // missing tag keyword
        assertParseFailure(parser, " " + PREFIX_TAG, String.format(MESSAGE_INVALID_SEARCH_KEYWORD));

        // invalid tag keyword
        assertParseFailure(parser, " " + PREFIX_TAG + "abc.", String.format(Tag.MESSAGE_CONSTRAINTS));

    }

    @Test
    public void parse_validArgsSpecified_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindContactCriteria criteria = new FindContactCriteria();
        criteria.addPredicate(new ContactNameContainsKeywordsPredicate(Arrays.asList("Bob")));
        FindContactCommand expectedFindCommand =
                new FindContactCommand(criteria);
        String userInput = " " + PREFIX_NAME + "Alice" + "  "
                + PREFIX_NAME + "Bob";
        assertParseSuccess(parser, " " + PREFIX_NAME + "Bob", expectedFindCommand);

        //last name used as predicate
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice" + " "
                + PREFIX_NAME + "Bob", expectedFindCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " " + "Alice" + " "
                + PREFIX_NAME + "Bob", expectedFindCommand);
    }
}
