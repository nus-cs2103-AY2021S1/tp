package com.eva.logic.parser.leave;

import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.commons.util.DateUtil;
import com.eva.logic.commands.AddLeaveCommand;
import com.eva.logic.commands.DeleteLeaveCommand;
import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;
import com.eva.logic.parser.Parser;
import com.eva.logic.parser.ParserUtil;
import com.eva.logic.parser.exceptions.IndexParseException;
import com.eva.logic.parser.exceptions.ParseException;

/**
 * Parses input and creates a new DeleteLeaveCommand object.
 */
public class DeleteLeaveCommandParser implements Parser<DeleteLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLeaveCommand
     * and returns an DeleteLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble()); // errors if index is invalid.

            LocalDate delDate = DateUtil.dateParsed(argMultimap.getValue(PREFIX_DATE).get(),
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));

            return new DeleteLeaveCommand(index, delDate);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE), pe);
        } catch (IndexParseException pe) {
            throw new ParseException(String.format("%s\n%s", pe.getMessage(), DeleteLeaveCommand.MESSAGE_USAGE));
        }
    }
}
