package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_ORDER;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calendar.MeetingTimeComparator;


public class SortMeetingCommandParser implements Parser<SortMeetingCommand> {

    @Override
    public SortMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CALENDAR_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_CALENDAR_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
        }

        Boolean isAscending = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_CALENDAR_ORDER).orElse(null));
        MeetingTimeComparator comparator = new MeetingTimeComparator();
        return new SortMeetingCommand(comparator, isAscending);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
