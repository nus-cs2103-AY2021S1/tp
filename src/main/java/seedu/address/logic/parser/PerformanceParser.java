package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAttemptsCommand;
import seedu.address.logic.commands.PerformanceCommand;
import seedu.address.logic.commands.ViewAttemptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */

public class PerformanceParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public PerformanceCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ViewAttemptCommand.COMMAND_WORD:
            return new ViewAttemptCommandParser().parse(arguments);
        case ListAttemptsCommand.COMMAND_WORD:
            return new ListAttemptsCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND + "\nMaybe you have used commands from Flashcard or "
                + "Quiz interfaces, which are not allowed in Performance interface.\n"
                + "Type 'help' to see the list of supported command lines from user guide.");
        }
    }
}
