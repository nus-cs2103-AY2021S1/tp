package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LIST_TYPE_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_LOW_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_STATISTICS_TYPE_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_APPLE;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.stock.logic.parser.SuggestionCommandParser.MESSAGE_SUGGESTION;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.CommandWords;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.commands.ListCommand;
import seedu.stock.logic.commands.StatisticsCommand;
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

    @Test
    public void parse_helpCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = "";
        SuggestionCommandParser parser = new SuggestionCommandParser("hel");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.HELP_COMMAND_WORD + "\n" + HelpCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("help", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.HELP_COMMAND_WORD + "\n" + HelpCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_listCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = INVALID_LIST_TYPE_DESC;
        SuggestionCommandParser parser = new SuggestionCommandParser("lis");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.LIST_COMMAND_WORD + " lt/all"
                + "\n" + ListCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("list", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.LIST_COMMAND_WORD + " lt/all" + "\n" + ListCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = INVALID_NAME_DESC + SOURCE_DESC_APPLE + INVALID_QUANTITY_DESC + LOCATION_DESC_APPLE
                + INVALID_LOW_QUANTITY_DESC;
        SuggestionCommandParser parser = new SuggestionCommandParser("ad");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.ADD_COMMAND_WORD + userInput + "\n" + AddCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("add", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.ADD_COMMAND_WORD + userInput + "\n" + AddCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = SERIAL_NUMBER_DESC_APPLE;
        SuggestionCommandParser parser = new SuggestionCommandParser("delet");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.DELETE_COMMAND_WORD + userInput
                + "\n" + DeleteCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("delete", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.DELETE_COMMAND_WORD + userInput
                + "\n" + DeleteCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_statisticsCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = INVALID_STATISTICS_TYPE_DESC;
        SuggestionCommandParser parser = new SuggestionCommandParser("stat");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.STATISTICS_COMMAND_WORD + " st/source"
                + "\n" + StatisticsCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("stats", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.STATISTICS_COMMAND_WORD + " st/source"
                + "\n" + StatisticsCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_findCommandSuggestion_success() {
        // EP: incorrect command word
        String userInput = NAME_DESC_APPLE;
        SuggestionCommandParser parser = new SuggestionCommandParser("fin");
        String expectedSuggestionMessage = MESSAGE_UNKNOWN_COMMAND + "\n"
                + MESSAGE_SUGGESTION + CommandWords.FIND_COMMAND_WORD + userInput
                + "\n" + FindCommand.MESSAGE_USAGE;
        SuggestionCommand expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: correct command word
        parser = new SuggestionCommandParser("find", "error message");
        expectedSuggestionMessage = "error message" + "\n"
                + MESSAGE_SUGGESTION + CommandWords.FIND_COMMAND_WORD + userInput
                + "\n" + FindCommand.MESSAGE_USAGE;
        expectedCommand = new SuggestionCommand(expectedSuggestionMessage);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
