package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.AttendanceType;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;

public class MarkCommandParser implements Parser<MarkCommand> {

    public MarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_TYPE);
        
        Index index;
        AttendanceType attendanceType;
        
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            attendanceType = ParserUtil.parseAttendanceType(argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
        
        return new MarkCommand(index, attendanceType);
    }
}
