package com.eva.logic.parser.leave;

import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;
import static com.eva.logic.parser.ParserUtil.parseIndex;
import static com.eva.logic.parser.ParserUtil.parseLeaveArgs;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddLeaveCommand;
import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;
import com.eva.logic.parser.Parser;
import com.eva.logic.parser.exceptions.IndexParseException;
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
        ArgumentMultimap leaveArgMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LEAVE);

        if (!arePrefixesPresent(leaveArgMultimap, PREFIX_LEAVE)
                || leaveArgMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }

        try {
            Index index = parseIndex(leaveArgMultimap.getPreamble()); // errors if index is invalid.

            List<String> leaveArgsList = leaveArgMultimap.getAllValues(PREFIX_LEAVE);
            List<Leave> leaves = parseLeaveArgs(leaveArgsList);

            return new AddLeaveCommand(index, leaves);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        } catch (IndexParseException | IllegalArgumentException e) {
            throw new ParseException(String.format("%s\n%s", e.getMessage(), AddLeaveCommand.MESSAGE_USAGE));
        }
    }
}
