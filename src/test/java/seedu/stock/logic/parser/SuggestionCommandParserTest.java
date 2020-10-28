package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.stock.logic.parser.SuggestionCommandParser.MESSAGE_SUGGESTION;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.CommandWords;
import seedu.stock.logic.commands.SuggestionCommand;

public class SuggestionCommandParserTest {
    @Test
    public void parse_exitCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = "";
        SuggestionCommandParser parser = new SuggestionCommandParser("exi");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.EXIT_COMMAND_WORD;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("exit", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.EXIT_COMMAND_WORD;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
