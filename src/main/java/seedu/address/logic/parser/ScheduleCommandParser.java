package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_MODE;
import static seedu.address.logic.parser.CliSyntax.SCHEDULE_ADD_COMPULSORY_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.SCHEDULE_COMMAND_PREFIXES;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.schedule.ScheduleAddCommand;
import seedu.address.logic.commands.schedule.ScheduleCommand;
import seedu.address.logic.commands.schedule.ScheduleDeleteCommand;
import seedu.address.logic.commands.schedule.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.DeleteEvent;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;
import seedu.address.model.event.ScheduleViewMode;
import seedu.address.model.event.UniqueIdentifierGenerator;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code userInputDate} to a LocalDate object
     * and returns a ScheduleCommand with the LocalDate object for execution.
     * @throws ParseException if the user input does not conform the expected date format
     * and the input is an empty string
     */
    @Override
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, SCHEDULE_COMMAND_PREFIXES);

        if (argMultimap.getValue(PREFIX_VIEW).isPresent()) {
            return handleScheduleViewCommand(argMultimap);
        }

        if (argMultimap.getValue(PREFIX_DELETE_EVENT).isPresent()) {
            return handleScheduleDeleteCommand(argMultimap);
        }


        if (argMultimap.getValue(PREFIX_ADD_EVENT).isPresent()) {
            return handleScheduleAddCommand(argMultimap);
        }

        throw new AssertionError("Should not reach here");
    }

    private ScheduleViewCommand handleScheduleViewCommand(ArgumentMultimap argumentMultimap) throws ParseException {

        ScheduleViewMode viewMode = null;
        LocalDateTime viewDateTime = null;

        if (argumentMultimap.getValue(PREFIX_VIEW_MODE).isPresent()) {
            String viewModeString = argumentMultimap.getValue(PREFIX_VIEW_MODE).get();
            viewMode = (ParserUtil.parseViewMode(viewModeString));
        }

        if (argumentMultimap.getValue(PREFIX_VIEW_DATE).isPresent()) {
            String viewDateTimeString = argumentMultimap.getValue(PREFIX_VIEW_DATE).get();
            viewDateTime = (ParserUtil.parseViewDate(viewDateTimeString));
        }

        return new ScheduleViewCommand(viewMode, viewDateTime);
    }

    private ScheduleAddCommand handleScheduleAddCommand(ArgumentMultimap argumentMultimap) throws ParseException {

        if (!arePrefixesPresent(argumentMultimap, SCHEDULE_ADD_COMPULSORY_PREFIXES)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleAddCommand.MESSAGE_USAGE));
        }

        String eventName = argumentMultimap.getValue(PREFIX_EVENT_NAME).get();
        LocalDateTime eventStartDateTime =
                ParserUtil.parseDateTime(argumentMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).get());
        LocalDateTime eventEndDateTime =
                ParserUtil.parseDateTime(argumentMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).get());

        if (!Event.isValidEventStartAndEndTime(eventStartDateTime, eventEndDateTime)) {
            throw new ParseException(Event.INVALID_EVENT_START_END_TIME_MSG);
        }

        EventRecurrence eventRecurrence =
                EventRecurrence.checkWhichRecurrence(argumentMultimap.getValue(PREFIX_RECURRENCE).get());
        String uniqueIdentifier = UniqueIdentifierGenerator.generateUid(eventName, eventStartDateTime.toString(),
                eventEndDateTime.toString());

        Event eventToAdd = new Event(eventName, eventStartDateTime, eventEndDateTime, uniqueIdentifier,
                eventRecurrence);

        return new ScheduleAddCommand(eventToAdd);

    }

    private ScheduleDeleteCommand handleScheduleDeleteCommand(ArgumentMultimap argumentMultimap) throws ParseException {

        if (!arePrefixesPresent(argumentMultimap, PREFIX_EVENT_NAME, PREFIX_EVENT_START_DATE_TIME,
                PREFIX_EVENT_END_DATE_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleDeleteCommand.MESSAGE_USAGE));
        }


        String eventName = argumentMultimap.getValue(PREFIX_EVENT_NAME).get();
        LocalDateTime eventStartDateTime =
                ParserUtil.parseDateTime(argumentMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).get());
        LocalDateTime eventEndDateTime =
                ParserUtil.parseDateTime(argumentMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).get());

        DeleteEvent deleteEvent = new DeleteEvent(eventName, eventStartDateTime, eventEndDateTime);

        if (!Event.isValidEventStartAndEndTime(eventStartDateTime, eventEndDateTime)) {
            throw new ParseException(Event.INVALID_EVENT_START_END_TIME_MSG);
        }

        return new ScheduleDeleteCommand(deleteEvent);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
