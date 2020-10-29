package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.fma.commons.core.LogsCenter;
import seedu.fma.logic.commands.AddCommand;
import seedu.fma.logic.commands.AddExCommand;
import seedu.fma.logic.commands.ClearCommand;
import seedu.fma.logic.commands.Command;
import seedu.fma.logic.commands.DeleteCommand;
import seedu.fma.logic.commands.DeleteExCommand;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.EditExCommand;
import seedu.fma.logic.commands.ExitCommand;
import seedu.fma.logic.commands.FindCommand;
import seedu.fma.logic.commands.HelpCommand;
import seedu.fma.logic.commands.ListCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.storage.StorageManager;

/**
 * Parses user input.
 */
public class FixMyAbsParser {
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

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
    public Command parseCommand(String userInput, ReadOnlyLogBook logBook) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            logger.info(String.format("User command doesn't match format: %s", userInput));
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments, logBook);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments, logBook);

        case EditExCommand.COMMAND_WORD:
            return new EditExCommandParser().parse(arguments, logBook);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments, logBook);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments, logBook);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddExCommand.COMMAND_WORD:
            return new AddExCommandParser().parse(arguments, logBook);

        case DeleteExCommand.COMMAND_WORD:
            return new DeleteExCommandParser().parse(arguments, logBook);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
