package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_EMPTY;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.Command;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.commands.DailyCommand;
import seedu.tr4cker.logic.commands.DeleteCommand;
import seedu.tr4cker.logic.commands.DoneCommand;
import seedu.tr4cker.logic.commands.EditCommand;
import seedu.tr4cker.logic.commands.ExitCommand;
import seedu.tr4cker.logic.commands.FindCommand;
import seedu.tr4cker.logic.commands.HelpCommand;
import seedu.tr4cker.logic.commands.HomeCommand;
import seedu.tr4cker.logic.commands.ListCommand;
import seedu.tr4cker.logic.commands.ModuleCommand;
import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.logic.commands.ResetCommand;
import seedu.tr4cker.logic.commands.TagCommand;
import seedu.tr4cker.logic.commands.TodoCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class Tr4ckerParser {

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
            throw new ParseException(String.format(MESSAGE_EMPTY, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case ResetCommand.COMMAND_WORD:
            return new ResetCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TodoCommand.COMMAND_WORD:
            return new TodoCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ModuleCommand.COMMAND_WORD:
            return new ModuleCommandParser().parse(arguments);

        case PlannerCommand.COMMAND_WORD:
            return new PlannerCommandParser().parse(arguments);

        case CountdownCommand.COMMAND_WORD:
            return new CountdownCommandParser().parse(arguments);

        case DailyCommand.COMMAND_WORD:
            return new DailyCommandParser().parse(arguments);

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
