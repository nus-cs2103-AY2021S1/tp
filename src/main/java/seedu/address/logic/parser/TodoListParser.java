package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.todolistcommands.AddTaskCommand;
import seedu.address.logic.commands.todolistcommands.ClearTaskCommand;
import seedu.address.logic.commands.todolistcommands.CompleteTaskCommand;
import seedu.address.logic.commands.todolistcommands.DeleteTaskCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskCommand;
import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.logic.commands.todolistcommands.HelpTaskCommand;
import seedu.address.logic.commands.todolistcommands.ListTaskCommand;
import seedu.address.logic.commands.todolistcommands.ResetTaskCommand;
import seedu.address.logic.commands.todolistcommands.SortTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.todolistparsers.AddTaskParser;
import seedu.address.logic.parser.todolistparsers.CompleteTaskParser;
import seedu.address.logic.parser.todolistparsers.DeleteTaskParser;
import seedu.address.logic.parser.todolistparsers.EditTaskParser;
import seedu.address.logic.parser.todolistparsers.FindTaskParser;
import seedu.address.logic.parser.todolistparsers.ResetTaskParser;
import seedu.address.logic.parser.todolistparsers.SortTaskParser;

public class TodoListParser implements FeatureParser {
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
        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskParser().parse(arguments);

        case ClearTaskCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ClearTaskCommand.COMMAND_WORD, arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ListTaskCommand.COMMAND_WORD, arguments);

        case SortTaskCommand.COMMAND_WORD:
            return new SortTaskParser().parse(arguments);

        case ResetTaskCommand.COMMAND_WORD:
            return new ResetTaskParser().parse(arguments);

        case CompleteTaskCommand.COMMAND_WORD:
            return new CompleteTaskParser().parse(arguments);

        case HelpTaskCommand.COMMAND_WORD:
            return singleWordCommandsChecker(HelpTaskCommand.COMMAND_WORD, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command singleWordCommandsChecker(String commandWord, String argument) throws ParseException {
        if (!argument.equals("")) {
            throw new ParseException("Invalid input format, extra string after the command word.");
        }

        switch (commandWord) {
        case ClearTaskCommand.COMMAND_WORD:
            return new ClearTaskCommand();

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case HelpTaskCommand.COMMAND_WORD:
            return new HelpTaskCommand();

        default:
            throw new ParseException("Invalid command");
        }
    }
}
