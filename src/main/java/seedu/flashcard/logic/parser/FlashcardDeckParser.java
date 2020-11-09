package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flashcard.logic.commands.AddCommand;
import seedu.flashcard.logic.commands.ClearCommand;
import seedu.flashcard.logic.commands.Command;
import seedu.flashcard.logic.commands.DeleteCommand;
import seedu.flashcard.logic.commands.EditCommand;
import seedu.flashcard.logic.commands.ExitCommand;
import seedu.flashcard.logic.commands.FavCommand;
import seedu.flashcard.logic.commands.FilterCommand;
import seedu.flashcard.logic.commands.FindCommand;
import seedu.flashcard.logic.commands.HelpCommand;
import seedu.flashcard.logic.commands.ListCommand;
import seedu.flashcard.logic.commands.QuizCommand;
import seedu.flashcard.logic.commands.ReviewCommand;
import seedu.flashcard.logic.commands.SortCommand;
import seedu.flashcard.logic.commands.StatsCommand;
import seedu.flashcard.logic.commands.UnfavCommand;
import seedu.flashcard.logic.commands.ViewCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FlashcardDeckParser {

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

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommand();

        case FavCommand.COMMAND_WORD:
            return new FavCommandParser().parse(arguments);

        case UnfavCommand.COMMAND_WORD:
            return new UnfavCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case QuizCommand.COMMAND_WORD:
            return new QuizCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
