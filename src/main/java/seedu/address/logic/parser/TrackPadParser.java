package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.attraction.AddAttractionCommand;
import seedu.address.logic.commands.attraction.ClearAttractionCommand;
import seedu.address.logic.commands.attraction.DeleteAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand;
import seedu.address.logic.commands.attraction.FindAttractionCommand;
import seedu.address.logic.commands.attraction.ListAttractionCommand;
import seedu.address.logic.commands.attraction.MarkVisitedAttractionCommand;
import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.logic.commands.itinerary.ClearItineraryCommand;
import seedu.address.logic.commands.itinerary.DeleteItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand;
import seedu.address.logic.commands.itinerary.FindItineraryCommand;
import seedu.address.logic.commands.itinerary.ListItineraryCommand;
import seedu.address.logic.commands.itinerary.SelectItineraryCommand;
import seedu.address.logic.commands.itineraryattraction.AddItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.DeleteItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand;
import seedu.address.logic.parser.attraction.AddAttractionCommandParser;
import seedu.address.logic.parser.attraction.DeleteAttractionCommandParser;
import seedu.address.logic.parser.attraction.EditAttractionCommandParser;
import seedu.address.logic.parser.attraction.FindAttractionCommandParser;
import seedu.address.logic.parser.attraction.MarkVisitedAttractionCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.itinerary.AddItineraryCommandParser;
import seedu.address.logic.parser.itinerary.DeleteItineraryCommandParser;
import seedu.address.logic.parser.itinerary.EditItineraryCommandParser;
import seedu.address.logic.parser.itinerary.FindItineraryCommandParser;
import seedu.address.logic.parser.itinerary.SelectItineraryCommandParser;
import seedu.address.logic.parser.itineraryattraction.AddItineraryAttractionCommandParser;
import seedu.address.logic.parser.itineraryattraction.DeleteItineraryAttractionCommandParser;
import seedu.address.logic.parser.itineraryattraction.EditItineraryAttractionCommandParser;

/**
 * Parses user input.
 */
public class TrackPadParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    // The first group, commandWord is words without / and numbers, but not working
    // private static final Pattern BASIC_COMMAND_FORMAT =
    //     Pattern.compile("(?<commandWord>^[^/||[^\\D]]*)(?<arguments>.*)");

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

        case AddAttractionCommand.COMMAND_WORD:
            return new AddAttractionCommandParser().parse(arguments);

        case EditAttractionCommand.COMMAND_WORD:
            return new EditAttractionCommandParser().parse(arguments);

        case DeleteAttractionCommand.COMMAND_WORD:
            return new DeleteAttractionCommandParser().parse(arguments);

        case MarkVisitedAttractionCommand.COMMAND_WORD:
            return new MarkVisitedAttractionCommandParser().parse(arguments);

        case ClearAttractionCommand.COMMAND_WORD:
            return new ClearAttractionCommand();

        case FindAttractionCommand.COMMAND_WORD:
            return new FindAttractionCommandParser().parse(arguments);

        case ListAttractionCommand.COMMAND_WORD:
            return new ListAttractionCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddItineraryCommand.COMMAND_WORD:
            return new AddItineraryCommandParser().parse(arguments);

        case EditItineraryCommand.COMMAND_WORD:
            return new EditItineraryCommandParser().parse(arguments);

        case DeleteItineraryCommand.COMMAND_WORD:
            return new DeleteItineraryCommandParser().parse(arguments);

        case ClearItineraryCommand.COMMAND_WORD:
            return new ClearItineraryCommand();

        case FindItineraryCommand.COMMAND_WORD:
            return new FindItineraryCommandParser().parse(arguments);

        case ListItineraryCommand.COMMAND_WORD:
            return new ListItineraryCommand();

        case SelectItineraryCommand.COMMAND_WORD:
            return new SelectItineraryCommandParser().parse(arguments);

        case AddItineraryAttractionCommand.COMMAND_WORD:
            return new AddItineraryAttractionCommandParser().parse(arguments);

        case EditItineraryAttractionCommand.COMMAND_WORD:
            return new EditItineraryAttractionCommandParser().parse(arguments);

        case DeleteItineraryAttractionCommand.COMMAND_WORD:
            return new DeleteItineraryAttractionCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
