package com.eva.logic.parser;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.ClearCommand;
import com.eva.logic.commands.FindApplicantCommand;
import com.eva.logic.commands.FindCommand;
import com.eva.logic.commands.FindStaffCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF, PREFIX_APPLICANT);
        if (argMultimap.isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_STAFF).isPresent()) {
            return new ClearCommand(PREFIX_STAFF);
        } else if (argMultimap.getValue(PREFIX_APPLICANT).isPresent()) {
            return new ClearCommand(PREFIX_APPLICANT);
        } else {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
