package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;

public class ShowAttendanceCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowAttendanceCommand
     * and returns a ShowAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;
        String preamble = argMultimap.getPreamble();
        Optional<String> date = argMultimap.getValue(PREFIX_DATE);

        if (preamble.isEmpty() || date.isEmpty()) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            ShowAttendanceCommand.MESSAGE_USAGE
                    )
            );
        }

        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        }

        Attendance attendance = ParserUtil.parseAttendance(date.get());

        return new ShowAttendanceCommand(index, attendance);
    }
}
