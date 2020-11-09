package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddJobCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearJobCommand;
import seedu.address.logic.commands.ClearPersonCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteJobCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditJobCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindJobCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListJobCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortJobCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.ViewJobCommand;
import seedu.address.logic.commands.ViewPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern COMPLEX_COMMAND_FORMAT =
            Pattern.compile("(?<firstCommandWord>\\S+) (?<secondCommandWord>\\S+)(?<arguments>.*)");
    private static final Pattern SIMPLE_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {

        final Matcher simpleMatcher = SIMPLE_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher complexMatcher = COMPLEX_COMMAND_FORMAT.matcher(userInput.trim());

        if (simpleMatcher.matches()) {
            // Simple command
            return parseSimpleCommand(simpleMatcher);
        } else if (complexMatcher.matches()) {
            // Complex command
            return parseComplexCommand(complexMatcher);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses user input into complex command for execution.
     * This is to be used for commands with more than 1 command word and possibly arguments.
     *
     * @param complexCommandMatcher Matcher that stores the complex command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseComplexCommand(Matcher complexCommandMatcher) throws ParseException {

        final String firstCommandWord = complexCommandMatcher.group("firstCommandWord");
        final String secondCommandWord = complexCommandMatcher.group("secondCommandWord");
        final String fullCommandWord = firstCommandWord + " " + secondCommandWord;
        final String arguments = complexCommandMatcher.group("arguments");
        switch (fullCommandWord) {

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case AddJobCommand.COMMAND_WORD:
            return new AddJobCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditJobCommand.COMMAND_WORD:
            return new EditJobCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteJobCommand.COMMAND_WORD:
            return new DeleteJobCommandParser().parse(arguments);

        case ClearPersonCommand.COMMAND_WORD:
            return new ClearPersonCommand();

        case ClearJobCommand.COMMAND_WORD:
            return new ClearJobCommand();

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindJobCommand.COMMAND_WORD:
            return new FindJobCommandParser().parse(arguments);

        case SortPersonCommand.COMMAND_WORD:
            return new SortPersonCommandParser().parse(arguments);

        case SortJobCommand.COMMAND_WORD:
            return new SortJobCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ListJobCommand.COMMAND_WORD:
            return new ListJobCommand();

        case ViewPersonCommand.COMMAND_WORD:
            return new ViewPersonCommandParser().parse(arguments);

        case ViewJobCommand.COMMAND_WORD:
            return new ViewJobCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into simple command for execution.
     * This is to be used for commands with only 1 command word and no arguments.
     *
     * @param simpleCommandMatcher Matcher that stores the complex command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseSimpleCommand(Matcher simpleCommandMatcher) throws ParseException {
        final String commandWord = simpleCommandMatcher.group("commandWord");
        switch (commandWord) {

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
