package com.eva.logic.parser;

import static com.eva.logic.parser.CliSyntax.PREFIX_INDEX;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE_END;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE_START;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;
import static com.eva.logic.parser.ParserUtil.parseIndex;
import static com.eva.logic.parser.ParserUtil.parseLeave;

import java.util.ArrayList;
import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddLeaveCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.staff.leave.Leave;

/**
 * Parses input and creates a new AddLeaveCommand object.
 */
public class AddLeaveCommandParser implements Parser<AddLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLeaveCommand
     * and returns an AddLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_LEAVE_START, PREFIX_LEAVE_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_LEAVE_START)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }

        try {
            Index index = parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

            List<String> dates = new ArrayList<>();
            dates.add(argMultimap.getValue(PREFIX_LEAVE_START).get());

            if (argMultimap.getValue(PREFIX_LEAVE_END).isPresent()) {
                dates.add(argMultimap.getValue(PREFIX_LEAVE_END).get());
            }
            Leave leave = parseLeave(dates);
            return new AddLeaveCommand(index, leave);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
    }
}
