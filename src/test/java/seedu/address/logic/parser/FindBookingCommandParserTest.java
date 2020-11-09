package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.model.booking.BookingMatchesEndDatePredicate;
import seedu.address.model.booking.BookingMatchesIsActivePredicate;
import seedu.address.model.booking.BookingMatchesPersonIdPredicate;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.booking.BookingMatchesStartDatePredicate;


public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBookingCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " dd  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        // one parameter
        BookingMatchesRoomIdPredicate predicate1 = new BookingMatchesRoomIdPredicate(2103);
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(Collections.singletonList(predicate1), Optional.of(2103), Optional.empty());
        assertParseSuccess(parser, " rid/2103", expectedFindBookingCommand);

        // two parameters
        BookingMatchesPersonIdPredicate predicate2 = new BookingMatchesPersonIdPredicate(1);
        FindBookingCommand expectedFindBookingCommand2 =
                new FindBookingCommand(Arrays.asList(predicate1, predicate2), Optional.of(2103), Optional.of(1));
        assertParseSuccess(parser, " \n rid/2103 \n \t pid/1  \t", expectedFindBookingCommand2);

        // different order of parameters should give the same result
        assertParseSuccess(parser, " \n pid/1 \t \n rid/2103 \t", expectedFindBookingCommand2);

        // even more parameters
        BookingMatchesStartDatePredicate predicate3 = new BookingMatchesStartDatePredicate(LocalDate.of(2020, 10, 20));
        BookingMatchesEndDatePredicate predicate4 = new BookingMatchesEndDatePredicate(LocalDate.of(2020, 10, 25));
        BookingMatchesIsActivePredicate predicate5 = new BookingMatchesIsActivePredicate(true);
        FindBookingCommand expectedFindBookingCommand3 =
                new FindBookingCommand(Arrays.asList(predicate1, predicate2, predicate3, predicate4, predicate5),
                        Optional.of(2103), Optional.of(1));
        assertParseSuccess(parser, " \n pid/1 \t \n rid/2103 \t \n sd/2020-10-20 \t \n ed/2020-10-25 "
                        + "\t \n ac/false\t",
                expectedFindBookingCommand3);


    }

}
