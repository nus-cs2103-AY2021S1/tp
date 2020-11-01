package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_MODE;
import static seedu.address.logic.parser.CliSyntax.SCHEDULE_COMMAND_PREFIXES;

import java.time.LocalDateTime;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.ScheduleViewMode;


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
}
