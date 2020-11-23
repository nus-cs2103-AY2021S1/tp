package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.BookmarkCommand;
import seedu.stock.logic.commands.ClearCommand;
import seedu.stock.logic.commands.Command;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.commands.ExitCommand;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.FindExactCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.commands.ListCommand;
import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.logic.commands.PrintCommand;
import seedu.stock.logic.commands.SortCommand;
import seedu.stock.logic.commands.StatisticsCommand;
import seedu.stock.logic.commands.StockViewCommand;
import seedu.stock.logic.commands.TabCommand;
import seedu.stock.logic.commands.UnbookmarkCommand;
import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class StockBookParser {

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
        String trimmedUserInput = userInput.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedUserInput);
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        final String argumentsToLower = arguments.toLowerCase();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            try {
                return new AddCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case HelpCommand.COMMAND_WORD:
            try {
                return new HelpCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case UpdateCommand.COMMAND_WORD:
            try {
                return new UpdateCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case SortCommand.COMMAND_WORD:
            try {
                return new SortCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case ListCommand.COMMAND_WORD:
            try {
                return new ListCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case ClearCommand.COMMAND_WORD:
            try {
                return new ClearCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case DeleteCommand.COMMAND_WORD:
            try {
                return new DeleteCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case StatisticsCommand.COMMAND_WORD:
            try {
                return new StatisticsCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case TabCommand.COMMAND_WORD:
            try {
                return new TabCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case FindCommand.COMMAND_WORD:
            try {
                return new FindCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case FindExactCommand.COMMAND_WORD:
            try {
                return new FindExactCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case NoteCommand.COMMAND_WORD:
            try {
                return new NoteCommandParser().parse(arguments);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(arguments);
            }

        case NoteDeleteCommand.COMMAND_WORD:
            try {
                return new NoteDeleteCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case StockViewCommand.COMMAND_WORD:
            try {
                return new StockViewCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case PrintCommand.COMMAND_WORD:
            try {
                return new PrintCommandParser().parse(argumentsToLower);

            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case BookmarkCommand.COMMAND_WORD:
            try {
                return new BookmarkCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }
        case UnbookmarkCommand.COMMAND_WORD:
            try {
                return new UnbookmarkCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }

        case ExitCommand.COMMAND_WORD:
            try {
                return new ExitCommandParser().parse(argumentsToLower);
            } catch (ParseException ex) {
                return new SuggestionCommandParser(commandWord, ex.getMessage()).parse(argumentsToLower);
            }
        default:
            return new SuggestionCommandParser(commandWord).parse(argumentsToLower);
        }

    }

}
