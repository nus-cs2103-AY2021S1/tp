package tp.acecs2103.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.AddCommand;
import tp.acecs2103.logic.commands.Command;
import tp.acecs2103.logic.commands.DeadlineCommand;
import tp.acecs2103.logic.commands.DeleteCommand;
import tp.acecs2103.logic.commands.DoneCommand;
import tp.acecs2103.logic.commands.EditCommand;
import tp.acecs2103.logic.commands.ExitCommand;
import tp.acecs2103.logic.commands.FilterCommand;
import tp.acecs2103.logic.commands.FindCommand;
import tp.acecs2103.logic.commands.HelpCommand;
import tp.acecs2103.logic.commands.HomeCommand;
import tp.acecs2103.logic.commands.ListCommand;
import tp.acecs2103.logic.commands.UndoneCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class TaskListParser {

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
    public Command parseCommand(String userInput) throws ParseException, CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case UndoneCommand.COMMAND_WORD:
            return new UndoneCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
