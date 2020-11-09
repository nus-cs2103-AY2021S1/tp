package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.CclearCommand;
import seedu.address.logic.commands.ClistCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DelModCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindModCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MclearCommand;
import seedu.address.logic.commands.MlistCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.commands.UnassignallCommand;
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

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case AddModCommand.COMMAND_WORD:
            return new AddModCommandParser().parse(arguments);

        case DelModCommand.COMMAND_WORD:
            return new DelModCommandParser().parse(arguments);

        case FindModCommand.COMMAND_WORD:
            return new FindModCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);

        case SwitchCommand.COMMAND_WORD:

        case ResetCommand.COMMAND_WORD:

        case HelpCommand.COMMAND_WORD:

        case ExitCommand.COMMAND_WORD:

        case UnassignallCommand.COMMAND_WORD:

        case MlistCommand.COMMAND_WORD:

        case ClistCommand.COMMAND_WORD:

        case MclearCommand.COMMAND_WORD:

        case CclearCommand.COMMAND_WORD:

        case ListCommand.COMMAND_WORD:
            return new OneWordCommandParser().parse(userInput);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }


}
