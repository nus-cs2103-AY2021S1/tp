package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.todolistcommands.AddTodoCommand;
import seedu.address.logic.commands.todolistcommands.DeleteTodoCommand;
import seedu.address.logic.commands.todolistcommands.EditTodoCommand;
import seedu.address.logic.commands.todolistcommands.FindTodoCommand;
import seedu.address.logic.commands.todolistcommands.HelpTodoCommand;
import seedu.address.logic.commands.todolistcommands.ListTodoCommand;
import seedu.address.logic.commands.todolistcommands.ViewTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.todolistparsers.AddTodoParser;
import seedu.address.logic.parser.todolistparsers.DeleteTodoParser;
import seedu.address.logic.parser.todolistparsers.EditTodoParser;
import seedu.address.logic.parser.todolistparsers.FindTodoParser;
import seedu.address.logic.parser.todolistparsers.ViewTodoParser;

public class TodoListParser {
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
        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoParser().parse(arguments);

        case EditTodoCommand.COMMAND_WORD:
            return new EditTodoParser().parse(arguments);

        case DeleteTodoCommand.COMMAND_WORD:
            return new DeleteTodoParser().parse(arguments);

        //case ClearCommand.COMMAND_WORD:
        //return new ClearCommand();

        case FindTodoCommand.COMMAND_WORD:
            return new FindTodoParser().parse(arguments);

        case ListTodoCommand.COMMAND_WORD:
            return new ListTodoCommand();

        //case ExitCommand.COMMAND_WORD:
        //return new ExitCommand();

        case HelpTodoCommand.COMMAND_WORD:
            return new HelpTodoCommand();

        case ViewTodoCommand.COMMAND_WORD:
            return new ViewTodoParser().parse(arguments);

        //case AddAssignmentCommand.COMMAND_WORD:
        //return new AddAssignmentParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
