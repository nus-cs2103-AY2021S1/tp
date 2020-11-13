package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.bagel.logic.commands.AddCommand;
import seedu.bagel.logic.commands.ClearCommand;
import seedu.bagel.logic.commands.Command;
import seedu.bagel.logic.commands.DeleteCommand;
import seedu.bagel.logic.commands.EditCommand;
import seedu.bagel.logic.commands.ExitCommand;
import seedu.bagel.logic.commands.FlipCommand;
import seedu.bagel.logic.commands.HelpCommand;
import seedu.bagel.logic.commands.ListCommand;
import seedu.bagel.logic.commands.SearchCommand;
import seedu.bagel.logic.commands.ViewCommand;
import seedu.bagel.logic.commands.sort.SortCommand;
import seedu.bagel.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class BagelParser {

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
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FlipCommand.COMMAND_WORD:
            return new FlipCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
