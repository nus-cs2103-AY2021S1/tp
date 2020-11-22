package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.commands.ClearCommand;
import seedu.forgetfulnus.logic.commands.Command;
import seedu.forgetfulnus.logic.commands.DeleteCommand;
import seedu.forgetfulnus.logic.commands.EditCommand;
import seedu.forgetfulnus.logic.commands.EndQuizCommand;
import seedu.forgetfulnus.logic.commands.ExitCommand;
import seedu.forgetfulnus.logic.commands.FindCommand;
import seedu.forgetfulnus.logic.commands.HelpCommand;
import seedu.forgetfulnus.logic.commands.ListCommand;
import seedu.forgetfulnus.logic.commands.NextCommand;
import seedu.forgetfulnus.logic.commands.QuizCommand;
import seedu.forgetfulnus.logic.commands.RandomQuizCommand;
import seedu.forgetfulnus.logic.commands.ResetScoreCommand;
import seedu.forgetfulnus.logic.commands.ScoreCommand;
import seedu.forgetfulnus.logic.commands.SortCommand;
import seedu.forgetfulnus.logic.commands.TryCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;

/**
 * Parses user input.
 */
public class GlossaryParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseZeroException if the user input is '0'.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException, ParseZeroException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new ClearCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new ListCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case ExitCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new ExitCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case HelpCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new HelpCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case QuizCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new QuizCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case EndQuizCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new EndQuizCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case NextCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new NextCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case RandomQuizCommand.COMMAND_WORD:
            return new RandomQuizCommandParser().parse(arguments);

        case TryCommand.COMMAND_WORD:
            return new TryCommand(arguments);

        case ScoreCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                return new ScoreCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case ResetScoreCommand.COMMAND_WORD:
            if (arguments.substring(1).equals(ResetScoreCommand.COMMAND_ELAB)) {
                return new ResetScoreCommand();
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
