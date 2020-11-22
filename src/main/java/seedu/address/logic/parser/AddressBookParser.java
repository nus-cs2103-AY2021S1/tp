
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EXTRA_PARAMETER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearEventAttendeesCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.EditBlockCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditFloorCommand;
import seedu.address.logic.commands.EditRoomCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListEventAttendeesCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        case ClearCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EXTRA_PARAMETER, ClearCommand.MESSAGE_USAGE));
            }
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EXTRA_PARAMETER, ListCommand.MESSAGE_USAGE));
            }
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EXTRA_PARAMETER, ExitCommand.MESSAGE_USAGE));
            }
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EXTRA_PARAMETER, HelpCommand.MESSAGE_USAGE));
            }
            return new HelpCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case ClearEventAttendeesCommand.COMMAND_WORD:
            return new ClearEventAttendeesCommandParser().parse(arguments);

        case ListGroupCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EXTRA_PARAMETER, ListGroupCommand.MESSAGE_USAGE));
            }
            return new ListGroupCommand();

        case ListEventAttendeesCommand.COMMAND_WORD:
            return new ListEventAttendeesCommandParser().parse(arguments);

        case EditRoomCommand.COMMAND_WORD:
            return new EditRoomCommandParser().parse(arguments);

        case EditFloorCommand.COMMAND_WORD:
            return new EditFloorCommandParser().parse(arguments);

        case EditBlockCommand.COMMAND_WORD:
            return new EditBlockCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
