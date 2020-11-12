package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.commands.ListLessonCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.MultipleAttributesException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PlanusParser {

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
    public Command parseCommand(String userInput) throws ParseException, MultipleAttributesException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        // general commands
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case CalendarCommand.COMMAND_WORD:
            return new CalendarCommand();

        // commands related to tasks
        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);
        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);
        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);
        case LessonCommand.COMMAND_WORD:
            return new LessonCommandParser().parse(arguments);
        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();
        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        // commands related to lessons
        case ListLessonCommand.COMMAND_WORD:
            return new ListLessonCommand();
        case EditLessonCommand.COMMAND_WORD:
            return new EditLessonCommandParser().parse(arguments);
        case FindLessonCommand.COMMAND_WORD:
            return new FindLessonCommandParser().parse(arguments);
        case DeleteLessonCommand.COMMAND_WORD:
            return new DeleteLessonCommandParser().parse(arguments);

        // fallback
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
