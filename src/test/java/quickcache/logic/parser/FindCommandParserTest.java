package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.FindCommand;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new FlashcardContainsTagPredicate(Arrays.asList("TagOne", "TagTwo")));
        assertParseSuccess(parser, "TagOne TagTwo", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n TagOne \n \t TagTwo  \t", expectedFindCommand);
    }

}
