package seedu.address.logic.parser.mainpageparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.main.ClearBudgetsCommand;
import seedu.address.logic.commands.main.CreateBudgetCommand;
import seedu.address.logic.commands.main.DeleteBudgetCommand;
import seedu.address.logic.commands.main.EditBudgetCommand;
import seedu.address.logic.commands.main.FindBudgetCommand;
import seedu.address.logic.commands.main.HelpBudgetCommand;
import seedu.address.logic.commands.main.ListBudgetCommand;
import seedu.address.logic.commands.main.OpenBudgetCommand;
import seedu.address.logic.commands.main.SortBudgetCommand;
import seedu.address.logic.parser.PageParser;
import seedu.address.logic.parser.exceptions.ParseException;


public class MainPageParser implements PageParser {
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
    @Override
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpBudgetCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case OpenBudgetCommand.COMMAND_WORD: {
            return new OpenBudgetCommandParser().parse(arguments);
        }
        case ClearBudgetsCommand.COMMAND_WORD: {
            return new ClearBudgetsCommand();
        }
        case DeleteBudgetCommand.COMMAND_WORD: {
            return new DeleteBudgetCommandParser().parse(arguments);
        }
        case CreateBudgetCommand.COMMAND_WORD: {
            return new CreateBudgetCommandParser().parse(arguments);
        }
        case EditBudgetCommand.COMMAND_WORD: {
            return new EditBudgetCommandParser().parse(arguments);
        }
        case FindBudgetCommand.COMMAND_WORD: {
            return new FindBudgetCommandParser().parse(arguments);
        }
        case ListBudgetCommand.COMMAND_WORD: {
            return new ListBudgetCommand();
        }
        case SortBudgetCommand.COMMAND_WORD: {
            return new SortBudgetCommandParser().parse(arguments);
        }
        case HelpBudgetCommand.COMMAND_WORD: {
            return new HelpBudgetCommand();
        }
        case UndoCommand.COMMAND_WORD: {
            return new UndoCommand();
        }
        case RedoCommand.COMMAND_WORD: {
            return new RedoCommand();
        }
        case ExitCommand.COMMAND_WORD: {
            return new ExitCommand();
        }
        default: {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND, HelpBudgetCommand.MESSAGE_USAGE));
        }
        }
    }
}
