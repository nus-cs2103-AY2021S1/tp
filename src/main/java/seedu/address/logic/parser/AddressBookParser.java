package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_AMBIGUOUS_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MenuCommand;
import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SubmitCommand;
import seedu.address.logic.commands.TotalCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.VendorCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        final String commandPrefix = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        String[] commands = {
            AddCommand.COMMAND_WORD,
            RemoveCommand.COMMAND_WORD,
            SortCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            PriceCommand.COMMAND_WORD,
            MenuCommand.COMMAND_WORD,
            TotalCommand.COMMAND_WORD,
            SubmitCommand.COMMAND_WORD,
            UndoCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            VendorCommand.COMMAND_WORD,
            PresetCommand.COMMAND_WORD,
        };

        ArrayList<String> matchingCommands = new ArrayList<>(Arrays.asList(commands));

        matchingCommands.removeIf(s -> !s.startsWith(commandPrefix));

        String commandWord = "";
        if (matchingCommands.size() == 1) {
            commandWord = matchingCommands.get(0);
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case PriceCommand.COMMAND_WORD:
            return new PriceCommandParser().parse(arguments);

        case MenuCommand.COMMAND_WORD:
            return new MenuCommand();

        case TotalCommand.COMMAND_WORD:
            return new TotalCommand();

        case SubmitCommand.COMMAND_WORD:
            return new SubmitCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case VendorCommand.COMMAND_WORD:
            return new VendorCommandParser().parse(arguments);

        case PresetCommand.COMMAND_WORD:
            return new PresetCommandParser().parse(arguments);

        default:
            throw matchingCommands.size() == 0
                    ? new ParseException(MESSAGE_UNKNOWN_COMMAND)
                    : new ParseException(String.format(MESSAGE_AMBIGUOUS_COMMAND, matchingCommands));
        }
    }

}
