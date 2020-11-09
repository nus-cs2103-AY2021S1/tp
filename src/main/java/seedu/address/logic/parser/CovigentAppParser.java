package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.DeletePatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.logic.commands.patient.ListPatientCommand;
import seedu.address.logic.commands.patient.SearchPatientCommand;
import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.logic.commands.room.FindEmptyRoomCommand;
import seedu.address.logic.commands.room.InitRoomCommand;
import seedu.address.logic.commands.room.ListRoomCommand;
import seedu.address.logic.commands.room.SearchRoomCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.SearchTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.patient.AddPatientCommandParser;
import seedu.address.logic.parser.patient.DeletePatientCommandParser;
import seedu.address.logic.parser.patient.EditPatientCommandParser;
import seedu.address.logic.parser.patient.SearchPatientCommandParser;
import seedu.address.logic.parser.room.AllocateRoomCommandParser;
import seedu.address.logic.parser.room.InitRoomCommandParser;
import seedu.address.logic.parser.room.SearchRoomCommandParser;
import seedu.address.logic.parser.task.AddTaskCommandParser;
import seedu.address.logic.parser.task.DeleteTaskCommandParser;
import seedu.address.logic.parser.task.EditTaskCommandParser;
import seedu.address.logic.parser.task.SearchTaskCommandParser;

/**
 * Parses user input.
 */
public class CovigentAppParser {

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        // create

        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case InitRoomCommand.COMMAND_WORD:
            return new InitRoomCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        // update

        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case AllocateRoomCommand.COMMAND_WORD:
            return new AllocateRoomCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        // read

        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        // delete

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        // others

        case SearchPatientCommand.COMMAND_WORD:
            return new SearchPatientCommandParser().parse(arguments);

        case SearchRoomCommand.COMMAND_WORD:
            return new SearchRoomCommandParser().parse(arguments);

        case SearchTaskCommand.COMMAND_WORD:
            return new SearchTaskCommandParser().parse(arguments);

        case FindEmptyRoomCommand.COMMAND_WORD:
            return new FindEmptyRoomCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListRoomCommand.COMMAND_WORD:
            return new ListRoomCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
