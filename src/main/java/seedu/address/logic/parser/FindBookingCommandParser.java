package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_ACTIVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingMatchesEndDatePredicate;
import seedu.address.model.booking.BookingMatchesIsActivePredicate;
import seedu.address.model.booking.BookingMatchesPersonIdPredicate;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.booking.BookingMatchesStartDatePredicate;

/**
 * Parses input arguments and creates a new FindBookingCommand object
 */
public class FindBookingCommandParser implements Parser<FindBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookingCommand
     * and returns a FindBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID, PREFIX_ROOM_ID,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_IS_ACTIVE);

        List<Predicate<Booking>> predicates = new ArrayList<Predicate<Booking>>();
        Optional<Integer> optionalRoomId = Optional.empty();
        Optional<Integer> optionalPersonId = Optional.empty();

        if (argMultimap.getValue(PREFIX_ROOM_ID).isPresent()) {
            Integer roomId = ParserUtil.parseRoomId(argMultimap.getValue(PREFIX_ROOM_ID).get());
            optionalRoomId = Optional.of(roomId);
            predicates.add(new BookingMatchesRoomIdPredicate(roomId));
        }

        if (argMultimap.getValue(PREFIX_PERSON_ID).isPresent()) {
            Integer personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_PERSON_ID).get());
            optionalPersonId = Optional.of(personId);
            predicates.add(new BookingMatchesPersonIdPredicate(personId));
        }

        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
            predicates.add(new BookingMatchesStartDatePredicate(startDate));
        }

        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
            predicates.add(new BookingMatchesEndDatePredicate(endDate));
        }

        if (argMultimap.getValue(PREFIX_IS_ACTIVE).isPresent()) {
            boolean isActive = ParserUtil.parseIsActive(argMultimap.getValue(PREFIX_IS_ACTIVE).get());
            predicates.add(new BookingMatchesIsActivePredicate(!isActive));
        }

        if (predicates.size() == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
        }

        return new FindBookingCommand(predicates, optionalRoomId, optionalPersonId);
    }

}
