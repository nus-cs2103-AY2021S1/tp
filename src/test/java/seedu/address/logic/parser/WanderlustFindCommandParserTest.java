package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertFindParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertFindParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.command.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

public class WanderlustFindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz"); //find activity

        assertFindParseSuccess(parser, "find -activity foo bar baz",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), ParserUtil.ACTIVITY_INDEX));
    }

    @Test
    public void parse_findFriend_success() {

        List<String> keywords = Arrays.asList("tom");

        assertFindParseSuccess(parser, "find -friend tom",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), ParserUtil.FRIEND_INDEX));

    }

    @Test
    public void parse_findAccommodation_success() {

        List<String> keywords = Arrays.asList("inn");

        assertFindParseSuccess(parser, "find -accommodation inn",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), ParserUtil.ACCOMMODATION_INDEX));

    }

    @Test
    public void parse_findActivity_success() {

        List<String> keywords = Arrays.asList("skating");

        assertFindParseSuccess(parser, "find -activity skating",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), ParserUtil.ACTIVITY_INDEX));

    }

    @Test
    public void parse_findMultipleKeyword_success() {

        List<String> keywords = Arrays.asList("skating", "eating");

        assertFindParseSuccess(parser, "find -activity skating eating",
                new FindCommand(new NameContainsKeywordsPredicate(keywords), ParserUtil.ACTIVITY_INDEX));

    }

    @Test
    public void parse_findActivityMissingKeyword_failure() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand validCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords),
                ParserUtil.ACTIVITY_INDEX);
        try {
            Assertions.assertFalse(validCommand.equals(new FindCommandParser().parse("find -activity")));
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }

    }

    @Test
    public void parse_findMissingType_failure() {
        String missingTypeInput = "find ice";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        assertFindParseFailure(parser, missingTypeInput, expectedErrorMessage);
    }

    @Test
    public void parse_findMissingDash_failure() {
        String invalidInput = "find activity foo bar baz"; //missing dash
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        assertFindParseFailure(parser, invalidInput, expectedErrorMessage);

    }

    @Test
    public void parse_findInvalidInputFormat_failure() {
        String invalidInput = "find";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MISSING_KEYWORDS);

        assertFindParseFailure(parser, invalidInput, expectedErrorMessage);

    }

}
