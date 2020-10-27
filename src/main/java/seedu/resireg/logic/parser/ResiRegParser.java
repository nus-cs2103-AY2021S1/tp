package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.commands.HelpCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;

/**
 * Parses user input in the format COMMAND_WORD ARGUMENTS, where COMMAND_WORD and ARGUMENTS are separated
 * by 1 or more spaces.
 */
public class ResiRegParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private Map<String, Parser<Command>> commandWordToParserMap;

    /**
     * Creates a new ResiRegParser which can parse the commands specified in the map given.
     *
     * @param commandWordToParserMap Mapping from command word to parser.
     */
    public ResiRegParser(Map<String, Parser<Command>> commandWordToParserMap) {
        this.commandWordToParserMap = commandWordToParserMap;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.HELP.getFullMessage()));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandWordToParserMap.containsKey(commandWord)) {
            return commandWordToParserMap.get(commandWord).parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
