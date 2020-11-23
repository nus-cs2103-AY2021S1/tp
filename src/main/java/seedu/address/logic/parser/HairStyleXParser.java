package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListShortcutCommand;
import seedu.address.logic.commands.PrintCommand;
import seedu.address.logic.commands.appointment.ListAppointmentCommand;
import seedu.address.logic.commands.client.ListClientCommand;
import seedu.address.logic.commands.hairdresser.ListHairdresserCommand;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.logic.commandshortcut.CommandWord;
import seedu.address.logic.commandshortcut.exceptions.CommandWordException;
import seedu.address.logic.parser.appointment.AddAppointmentCommandParser;
import seedu.address.logic.parser.appointment.DeleteAppointmentCommandParser;
import seedu.address.logic.parser.appointment.EditAppointmentCommandParser;
import seedu.address.logic.parser.appointment.FilterAppointmentCommandParser;
import seedu.address.logic.parser.client.AddClientCommandParser;
import seedu.address.logic.parser.client.DeleteClientCommandParser;
import seedu.address.logic.parser.client.EditClientCommandParser;
import seedu.address.logic.parser.client.FilterClientCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.hairdresser.AddHairdresserCommandParser;
import seedu.address.logic.parser.hairdresser.DeleteHairdresserCommandParser;
import seedu.address.logic.parser.hairdresser.EditHairdresserCommandParser;
import seedu.address.logic.parser.hairdresser.FilterHairdresserCommandParser;

/**
 * Parses user input.
 */
public class HairStyleXParser {

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

        final String commandAlias = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        CommandWord commandWord;
        try {
            commandWord = CommandShortcutSet.getInstance().getCommandWord(commandAlias);
        } catch (CommandWordException ex) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        switch (commandWord) {


        case ADD_CLIENT:
            return new AddClientCommandParser().parse(arguments);

        case ADD_HAIRDRESSER:
            return new AddHairdresserCommandParser().parse(arguments);

        case ADD_APPOINTMENT:
            return new AddAppointmentCommandParser().parse(arguments);

        case EDIT_CLIENT:
            return new EditClientCommandParser().parse(arguments);

        case EDIT_HAIRDRESSER:
            return new EditHairdresserCommandParser().parse(arguments);

        case EDIT_APPOINTMENT:
            return new EditAppointmentCommandParser().parse(arguments);

        case DELETE_CLIENT:
            return new DeleteClientCommandParser().parse(arguments);

        case DELETE_HAIRDRESSER:
            return new DeleteHairdresserCommandParser().parse(arguments);

        case DELETE_APPOINTMENT:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case FILTER_HAIRDRESSER:
            return new FilterHairdresserCommandParser().parse(arguments);

        case FILTER_CLIENT:
            return new FilterClientCommandParser().parse(arguments);

        case FILTER_APPT:
            return new FilterAppointmentCommandParser().parse(arguments);

        case CLEAR:
            return new ClearCommand();

        case LIST_CLIENT:
            return new ListClientCommand();

        case LIST_HAIRDRESSER:
            return new ListHairdresserCommand();

        case LIST_APPOINTMENT:
            return new ListAppointmentCommand();

        case EXIT:
            return new ExitCommand();

        case HELP:
            return new HelpCommand();

        case ADD_SHORTCUT:
            return new AddShortcutCommandParser().parse(arguments);

        case PRINT:
            return new PrintCommand();

        case DELETE_SHORTCUT:
            return new DeleteShortcutCommand(arguments.trim());

        case LIST_SHORTCUT:
            return new ListShortcutCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
