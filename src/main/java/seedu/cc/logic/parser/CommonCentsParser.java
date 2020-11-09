package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.ExitCommand;
import seedu.cc.logic.commands.HelpCommand;
import seedu.cc.logic.commands.accountlevel.AddAccountCommand;
import seedu.cc.logic.commands.accountlevel.DeleteAccountCommand;
import seedu.cc.logic.commands.accountlevel.EditAccountNameCommand;
import seedu.cc.logic.commands.accountlevel.ListAccountCommand;
import seedu.cc.logic.commands.accountlevel.SwitchAccountCommand;
import seedu.cc.logic.commands.entrylevel.AddCommand;
import seedu.cc.logic.commands.entrylevel.ClearCommand;
import seedu.cc.logic.commands.entrylevel.DeleteCommand;
import seedu.cc.logic.commands.entrylevel.EditCommand;
import seedu.cc.logic.commands.entrylevel.FindCommand;
import seedu.cc.logic.commands.entrylevel.GetProfitCommand;
import seedu.cc.logic.commands.entrylevel.ListCommand;
import seedu.cc.logic.commands.entrylevel.UndoCommand;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CommonCentsParser {

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
        // Entry-level Commands
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GetProfitCommand.COMMAND_WORD:
            return new GetProfitCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        // Account-level Commands
        case AddAccountCommand.COMMAND_WORD:
            return new AddAccountCommandParser().parse(arguments);

        case EditAccountNameCommand.COMMAND_WORD:
            return new EditAccountNameCommandParser().parse(arguments);

        case DeleteAccountCommand.COMMAND_WORD:
            return new DeleteAccountCommandParser().parse(arguments);

        case SwitchAccountCommand.COMMAND_WORD:
            return new SwitchAccountCommandParser().parse(arguments);

        case ListAccountCommand.COMMAND_WORD:
            return new ListAccountCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
