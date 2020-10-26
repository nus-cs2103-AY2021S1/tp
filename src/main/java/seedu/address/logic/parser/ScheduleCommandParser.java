package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import seedu.address.logic.commands.schedule.ScheduleCommand;
import seedu.address.logic.commands.schedule.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;


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
/*
        if (argMultimap.getValue(PREFIX_DELETE_EVENT).isPresent()) {
            return handleScheduleDeleteCommand(argMultimap);
        }

        if (argMultimap.getValue(PREFIX_ADD_EVENT).isPresent()) {
            return handleScheduleAddCommand(argMultimap);
        }

 */
        throw new AssertionError("Should not reach here");
    }

    private ScheduleViewCommand handleScheduleViewCommand(ArgumentMultimap argumentMultimap) throws ParseException {
        ScheduleViewCommand scheduleViewCommand = new ScheduleViewCommand();

        if (argumentMultimap.getValue(PREFIX_VIEW_MODE).isPresent()) {
            String viewMode = argumentMultimap.getValue(PREFIX_VIEW_MODE).get();
            scheduleViewCommand.setDesiredViewMode(ParserUtil.parseViewMode(viewMode));
        }

        if (argumentMultimap.getValue(PREFIX_VIEW_DATE).isPresent()) {
            String dateToView = argumentMultimap.getValue(PREFIX_VIEW_DATE).get();
            scheduleViewCommand.setTargetViewDate(ParserUtil.parseViewDate(dateToView));
        }

        return scheduleViewCommand;

    }
}
