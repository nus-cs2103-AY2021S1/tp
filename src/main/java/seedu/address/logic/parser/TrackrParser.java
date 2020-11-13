package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.logic.commands.AttendanceBelowCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTutorialGroupCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditParticipationCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.FindTutorialGroupCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListModuleCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTutorialGroupCommand;
import seedu.address.logic.commands.ParticipationBelowCommand;
import seedu.address.logic.commands.PreviousViewCommand;
import seedu.address.logic.commands.ViewAttendanceCommand;
import seedu.address.logic.commands.ViewStudentCommand;
import seedu.address.logic.commands.ViewTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TrackrParser {

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
        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleCommandParser().parse(arguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleCommandParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListTutorialGroupCommand.COMMAND_WORD:
            return new ListTutorialGroupCommand();

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case FindModuleCommand.COMMAND_WORD:
            return new FindModuleCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddTutorialGroupCommand.COMMAND_WORD:
            return new AddTutorialGroupCommandParser().parse(arguments);

        case ViewTutorialGroupCommand.COMMAND_WORD:
            return new ViewTutorialGroupCommandParser().parse(arguments);

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case ViewStudentCommand.COMMAND_WORD:
            return new ViewStudentCommandParser().parse(arguments);

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case DeleteTutorialGroupCommand.COMMAND_WORD:
            return new DeleteTutorialGroupCommandParser().parse(arguments);

        case EditTutorialGroupCommand.COMMAND_WORD:
            return new EditTutorialGroupCommandParser().parse(arguments);

        case FindTutorialGroupCommand.COMMAND_WORD:
            return new FindTutorialGroupCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case PreviousViewCommand.COMMAND_WORD:
            return new PreviousViewCommand();

        case AddAttendanceCommand.COMMAND_WORD:
            return new AddAttendanceCommandParser().parse(arguments);

        case DeleteAttendanceCommand.COMMAND_WORD:
            return new DeleteAttendanceCommandParser().parse(arguments);

        case EditParticipationCommand.COMMAND_WORD:
            return new EditParticipationCommandParser().parse(arguments);

        case ViewAttendanceCommand.COMMAND_WORD:
            return new ViewAttendanceCommandParser().parse(arguments);

        case AttendanceBelowCommand.COMMAND_WORD:
            return new AttendanceBelowCommandParser().parse(arguments);

        case ParticipationBelowCommand.COMMAND_WORD:
            return new ParticipationBelowCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
