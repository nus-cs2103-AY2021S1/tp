package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ProgressCommand;
import seedu.address.logic.commands.RecommendSuCommand;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.StartCommand;
import seedu.address.logic.commands.SuCommand;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class GradeBookParser {

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        final String argumentsInLowerCase = arguments.toLowerCase();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(argumentsInLowerCase);

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(argumentsInLowerCase);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(argumentsInLowerCase);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(argumentsInLowerCase);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(argumentsInLowerCase);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(argumentsInLowerCase);

        case StartCommand.COMMAND_WORD:
            return new StartCommandParser().parse(argumentsInLowerCase);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(argumentsInLowerCase);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(argumentsInLowerCase);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(argumentsInLowerCase);

        case SetCommand.COMMAND_WORD:
            return new SetCommandParser().parse(argumentsInLowerCase);

        case RecommendSuCommand.COMMAND_WORD:
            return new RecommendSuCommandParser().parse(argumentsInLowerCase);

        case ProgressCommand.COMMAND_WORD:
            return new ProgressCommandParser().parse(argumentsInLowerCase);

        case SuCommand.COMMAND_WORD:
            return new SuCommandParser().parse(argumentsInLowerCase);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
